package com.roomflow.domain.room.entity;

import com.roomflow.domain.common.BaseEntity;
import lombok.Data;

import java.time.LocalTime;

@Data
public class Room extends BaseEntity {
    private Long id;
    private String name;
    private int capacity; // 이용가능 인원 (2~4명이라고 가정시 4??)
    private LocalTime openTime;
    private LocalTime closeTime;
    private boolean isAvailable;

    public Room(Long id, String name, int capacity, LocalTime openTime, LocalTime closeTime, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.isAvailable = isAvailable;
    }
}
