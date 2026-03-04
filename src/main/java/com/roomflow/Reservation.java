package com.roomflow;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private Long id;
    private Long userId;
    private Long roomId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Status status;
    private LocalTime createdTime; // 예약 주문 시간? 예약을 생성한 시간
}
