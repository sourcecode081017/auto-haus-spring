package com.autohaus.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.autohaus.demo.dto.ReservationDTO;
import com.autohaus.demo.entity.Reservation;
import com.autohaus.demo.entity.User;
import com.autohaus.demo.entity.Vehicle;
import com.autohaus.demo.repository.ReservationRepository;
import com.autohaus.demo.repository.UserRepository;
import com.autohaus.demo.repository.VehicleRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public ReservationService(ReservationRepository reservationRepository, 
                             UserRepository userRepository,
                             VehicleRepository vehicleRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        User user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(reservationDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        
        Reservation reservation = Reservation.builder()
                .user(user)
                .vehicle(vehicle)
                .reservationStartDate(reservationDTO.getReservationStartDate())
                .reservationEndDate(reservationDTO.getReservationEndDate())
                .totalPrice(reservationDTO.getTotalPrice())
                .reservationStatus(reservationDTO.getReservationStatus())
                .build();
        
        Reservation savedReservation = reservationRepository.save(reservation);
        return mapToDTO(savedReservation);
    }

    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return mapToDTO(reservation);
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<ReservationDTO> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<ReservationDTO> getReservationsByVehicleId(UUID vehicleId) {
        return reservationRepository.findByVehicleId(vehicleId).stream()
                .map(this::mapToDTO)
                .toList();
    }

    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        
        if (reservationDTO.getUserId() != null) {
            User user = userRepository.findById(reservationDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            reservation.setUser(user);
        }
        
        if (reservationDTO.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(reservationDTO.getVehicleId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            reservation.setVehicle(vehicle);
        }
        
        reservation.setReservationStartDate(reservationDTO.getReservationStartDate());
        reservation.setReservationEndDate(reservationDTO.getReservationEndDate());
        reservation.setTotalPrice(reservationDTO.getTotalPrice());
        reservation.setReservationStatus(reservationDTO.getReservationStatus());
        
        Reservation updatedReservation = reservationRepository.save(reservation);
        return mapToDTO(updatedReservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    private ReservationDTO mapToDTO(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .userId(reservation.getUser().getId())
                .vehicleId(reservation.getVehicle().getId())
                .reservationStartDate(reservation.getReservationStartDate())
                .reservationEndDate(reservation.getReservationEndDate())
                .totalPrice(reservation.getTotalPrice())
                .reservationStatus(reservation.getReservationStatus())
                .build();
    }
}
