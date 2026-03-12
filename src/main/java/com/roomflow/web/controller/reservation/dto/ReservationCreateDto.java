package com.roomflow.web.controller.reservation.dto;

import com.roomflow.domain.reservation.Reservation;
import com.roomflow.domain.user.User;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationCreateDto {

    @NotNull
    @FutureOrPresent
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;


    public Reservation toEntity() {
        return new Reservation(
                date,
                startTime,
                endTime
        );
    }
}
