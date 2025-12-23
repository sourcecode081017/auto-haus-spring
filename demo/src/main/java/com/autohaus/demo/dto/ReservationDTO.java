package com.autohaus.demo.dto;

import java.time.LocalDate;
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
public class ReservationDTO {
    private Long id;
    private Long userId;
    private UUID vehicleId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private int totalPrice;
    private String reservationStatus;
}
