package com.roomflow;

import java.time.LocalTime;

public class Room {
    private Long id;
    private String name;
    private int capacity; // 이용가능 인원 (2~4명이라고 가정시 4??)
    private LocalTime openTime;
    private LocalTime closeTime;
    private boolean isAvailable;

}
