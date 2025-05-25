package com.park.ride.service;


import com.park.ride.model.Ride;
import com.park.ride.model.User;
import com.park.ride.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getRidesByUser(User user) {
        return rideRepository.findByUser(user);
    }

    public Ride bookRide(User user, String source, String destination, String type, LocalDateTime scheduledTime) {
        Ride ride = new Ride();
        ride.setUser(user);
        ride.setSource(source);
        ride.setDestination(destination);
        ride.setType(type);
        ride.setStatus("SCHEDULED");
        ride.setScheduledTime(scheduledTime);
        ride.setPrice(calculatePrice(type, source, destination));
        return rideRepository.save(ride);
    }

    private double calculatePrice(String type, String source, String destination) {
        // Dummy distance calculation based on string lengths (replace with actual distance logic)
        int distance = Math.abs(destination.length() - source.length()) + 5; // minimum 5 km

        double baseRate;
        switch (type.toLowerCase()) {
            case "premium":
                baseRate = 20.0;  // price per km for premium
                break;
            case "standard":
            default:
                baseRate = 10.0;  // price per km for standard
                break;
        }

        double price = distance * baseRate;

        // Optional: add surge pricing between 5 PM to 8 PM (just example)
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        if (hour >= 17 && hour <= 20) {
            price *= 1.5; // 50% surge
        }

        // Round to 2 decimal places
        return Math.round(price * 100.0) / 100.0;
    }


    public Ride completeRide(Long rideId) {
        return rideRepository.findById(rideId).map(ride -> {
            ride.setStatus("COMPLETED");
            return rideRepository.save(ride);
        }).orElse(null);
    }
}
