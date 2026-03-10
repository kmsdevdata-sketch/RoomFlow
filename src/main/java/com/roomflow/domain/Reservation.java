package com.roomflow.domain;

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
    private LocalTime createdTime; // 예약 주문 시간? 예약을 생성한 시간

    public Reservation(Long userId, Long roomId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.userId = userId;
        this.roomId = roomId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
