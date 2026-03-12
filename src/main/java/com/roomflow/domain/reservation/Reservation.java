package com.roomflow.domain.reservation;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Reservation {
    private Long id;
    private Long userId;
    private Long roomId;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private Status status;
    private LocalTime createdTime;

    public Reservation(Long roomId,LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.roomId = roomId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
