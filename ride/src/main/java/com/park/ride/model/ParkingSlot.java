package com.park.ride.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parking_slots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    private boolean available;

    private double hourlyRate;

    private String type; // REGULAR, ELECTRIC, HANDICAPPED
}
