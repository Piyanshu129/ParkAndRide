package com.park.ride.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String source;

    private String destination;

    private String type; // CAB, SHUTTLE, RICKSHAW

    private String status; // SCHEDULED, COMPLETED

    private double price;

    private LocalDateTime scheduledTime;
}
