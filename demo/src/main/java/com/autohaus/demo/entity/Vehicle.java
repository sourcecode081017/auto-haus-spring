package com.autohaus.demo.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private String vehicleDescription; // eg "3.2L V6 Engine, Automatic Transmission"
    @Column(nullable = false)
    private String color; // e.g., Red, Blue, Black
    @Column(nullable = false)
    private String vehicleType; // e.g., SUV, Sedan
    @Column(nullable = false)
    private String fuelType; // e.g., Petrol, Diesel, Electric
    @Column(nullable = false)
    private int seatingCapacity; // e.g., 2, 4, 5, 7
    @Column(nullable = false)
    private String sizeCategory; // e.g., Compact, Midsize, Fullsize
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private int pricePerDay;
    @Column(nullable = false)
    private boolean isAvailableToRent;
    
}
