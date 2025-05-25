package com.park.ride.controller;

import com.park.ride.model.Reservation;
import com.park.ride.model.User;
import com.park.ride.service.ReservationService;
import com.park.ride.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Reservation> getUserReservations(Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        return reservationService.getReservationsByUser(user);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(Principal principal,
                                                         @RequestParam Long id,
                                                         @RequestParam String startTime,
                                                         @RequestParam String endTime) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);
        Reservation reservation = reservationService.createReservation(user, id, start, end);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.cancelReservation(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
