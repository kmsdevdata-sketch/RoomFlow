package com.roomflow.domain.reservation;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void cancel() {

        if (this.status != Status.RESERVATION) {
            throw new IllegalStateException("취소할 수 없는 예약 상태입니다.");
        }

        LocalDateTime reservationStart = LocalDateTime.of(date, startTime);
        LocalDateTime cancelDeadline = LocalDateTime.now().plusMinutes(30);

        if (reservationStart.isBefore(cancelDeadline)) {
            throw new IllegalStateException("예약 시작 30분 전까지만 취소 가능합니다.");
        }

        this.status = Status.CANCELED;
    }
}
