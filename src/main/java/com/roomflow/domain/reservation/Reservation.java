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

    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;

    private Status status;
    private LocalTime createdTime; // 예약 주문 시간? 예약을 생성한 시간

}
