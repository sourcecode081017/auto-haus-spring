package com.autohaus.demo.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {
    private UUID id;
    private String make;
    private String model;
    private int year;
    private String vehicleDescription;
    private String color;
    private String vehicleType;
    private String fuelType;
    private int seatingCapacity;
    private String sizeCategory;
    private String location;
    private int pricePerDay;
    private boolean isAvailableToRent;
}
