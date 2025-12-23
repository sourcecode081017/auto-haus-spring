package com.autohaus.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autohaus.demo.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

}
