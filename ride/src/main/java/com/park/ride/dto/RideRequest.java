package com.park.ride.dto;

import lombok.Data;

@Data
public class RideRequest {
    private String source;
    private String destination;
    private String type;
    private String scheduledTime; // in ISO format: "2025-05-25T09:00"
}

