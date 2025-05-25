package com.park.ride.service;


import com.park.ride.model.ParkingSlot;
import com.park.ride.model.Reservation;
import com.park.ride.model.User;
import com.park.ride.repository.ParkingSlotRepository;
import com.park.ride.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    public List<Reservation> getReservationsByUser(User user) {
        return reservationRepository.findByUser(user);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(User user, Long slotId, LocalDateTime startTime, LocalDateTime endTime) {
        Optional<ParkingSlot> slotOpt = parkingSlotRepository.findById(slotId);
        if (slotOpt.isPresent() && slotOpt.get().isAvailable()) {
            ParkingSlot slot = slotOpt.get();
            slot.setAvailable(false);
            parkingSlotRepository.save(slot);

            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setSlot(slot);
            reservation.setStartTime(startTime);
            reservation.setEndTime(endTime);
            reservation.setStatus("ACTIVE");
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public Reservation cancelReservation(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setStatus("CANCELLED");
            ParkingSlot slot = reservation.getSlot();
            slot.setAvailable(true);
            parkingSlotRepository.save(slot);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public void autoCancelNoShowReservations() {
        List<Reservation> activeReservations = reservationRepository.findByStatus("ACTIVE");
        LocalDateTime now = LocalDateTime.now();
        for (Reservation reservation : activeReservations) {
            if (reservation.getStartTime().isBefore(now.minusMinutes(15))) {
                reservation.setStatus("CANCELLED");
                ParkingSlot slot = reservation.getSlot();
                slot.setAvailable(true);
                parkingSlotRepository.save(slot);
                reservationRepository.save(reservation);
            }
        }
    }
}
