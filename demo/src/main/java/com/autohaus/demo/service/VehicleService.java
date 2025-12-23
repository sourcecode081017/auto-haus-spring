package com.autohaus.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.autohaus.demo.dto.VehicleDTO;
import com.autohaus.demo.entity.Vehicle;
import com.autohaus.demo.repository.VehicleRepository;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = Vehicle.builder()
                .make(vehicleDTO.getMake())
                .model(vehicleDTO.getModel())
                .year(vehicleDTO.getYear())
                .vehicleDescription(vehicleDTO.getVehicleDescription())
                .color(vehicleDTO.getColor())
                .vehicleType(vehicleDTO.getVehicleType())
                .fuelType(vehicleDTO.getFuelType())
                .seatingCapacity(vehicleDTO.getSeatingCapacity())
                .sizeCategory(vehicleDTO.getSizeCategory())
                .location(vehicleDTO.getLocation())
                .pricePerDay(vehicleDTO.getPricePerDay())
                .isAvailableToRent(vehicleDTO.isAvailableToRent())
                .build();
        
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return mapToDTO(savedVehicle);
    }

    public VehicleDTO getVehicleById(UUID id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return mapToDTO(vehicle);
    }

    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public VehicleDTO updateVehicle(UUID id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setVehicleDescription(vehicleDTO.getVehicleDescription());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setFuelType(vehicleDTO.getFuelType());
        vehicle.setSeatingCapacity(vehicleDTO.getSeatingCapacity());
        vehicle.setSizeCategory(vehicleDTO.getSizeCategory());
        vehicle.setLocation(vehicleDTO.getLocation());
        vehicle.setPricePerDay(vehicleDTO.getPricePerDay());
        vehicle.setAvailableToRent(vehicleDTO.isAvailableToRent());
        
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return mapToDTO(updatedVehicle);
    }

    public void deleteVehicle(UUID id) {
        vehicleRepository.deleteById(id);
    }

    private VehicleDTO mapToDTO(Vehicle vehicle) {
        return VehicleDTO.builder()
                .id(vehicle.getId())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .vehicleDescription(vehicle.getVehicleDescription())
                .color(vehicle.getColor())
                .vehicleType(vehicle.getVehicleType())
                .fuelType(vehicle.getFuelType())
                .seatingCapacity(vehicle.getSeatingCapacity())
                .sizeCategory(vehicle.getSizeCategory())
                .location(vehicle.getLocation())
                .pricePerDay(vehicle.getPricePerDay())
                .isAvailableToRent(vehicle.isAvailableToRent())
                .build();
    }
}
