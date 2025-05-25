package com.park.ride.controller;


import com.park.ride.dto.RideRequest;
import com.park.ride.model.Ride;
import com.park.ride.model.User;
import com.park.ride.service.RideService;
import com.park.ride.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Ride> getUserRides(Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        return rideService.getRidesByUser(user);
    }

    @PostMapping
    public ResponseEntity<Ride> bookRide(Principal principal, @RequestBody RideRequest request) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        LocalDateTime scheduled = LocalDateTime.parse(request.getScheduledTime());
        Ride ride = rideService.bookRide(user, request.getSource(), request.getDestination(), request.getType(), scheduled);
        return ResponseEntity.ok(ride);
    }



    @PostMapping("/{id}/complete")
    public ResponseEntity<Ride> completeRide(@PathVariable Long id) {
        Ride ride = rideService.completeRide(id);
        if (ride != null) {
            return ResponseEntity.ok(ride);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
