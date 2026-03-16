package com.roomflow.web.controller.reservation.dto;

import com.roomflow.domain.reservation.entity.Reservation;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationCreateDto {

    @NotNull
    private Long roomId;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // 올바른 포멧 랜더링
    private LocalDate date;

    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;


    public Reservation toEntity() {
        return new Reservation(
                roomId,
                date,
                startTime,
                endTime
        );
    }
}
