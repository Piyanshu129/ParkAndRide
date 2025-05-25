package com.park.ride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.park.ride.model.Reservation;
import com.park.ride.model.User;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user); 
    
    // Find all reservations by status (e.g. ACTIVE)
    List<Reservation> findByStatus(String status);
}
