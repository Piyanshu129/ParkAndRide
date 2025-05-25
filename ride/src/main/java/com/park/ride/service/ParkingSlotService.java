package com.park.ride.service;



import com.park.ride.model.ParkingSlot;
import com.park.ride.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    public List<ParkingSlot> getAllSlots() {
        return parkingSlotRepository.findAll();
    }

    public List<ParkingSlot> getAvailableSlots() {
        return parkingSlotRepository.findByAvailableTrue();
    }

    public Optional<ParkingSlot> getSlotById(Long id) {
        return parkingSlotRepository.findById(id);
    }

    public ParkingSlot createSlot(ParkingSlot slot) {
        return parkingSlotRepository.save(slot);
    }

    public ParkingSlot updateSlot(Long id, ParkingSlot updatedSlot) {
        return parkingSlotRepository.findById(id).map(slot -> {
            slot.setLocation(updatedSlot.getLocation());
            slot.setAvailable(updatedSlot.isAvailable());
            slot.setHourlyRate(updatedSlot.getHourlyRate());
            slot.setType(updatedSlot.getType());
            return parkingSlotRepository.save(slot);
        }).orElse(null);
    }

    public void deleteSlot(Long id) {
        parkingSlotRepository.deleteById(id);
    }
}


