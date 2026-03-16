package com.roomflow.domain.room.repository;

import com.roomflow.domain.room.entity.Room;

import java.util.ArrayList;
import java.util.List;

public interface RoomRepository {
    Room save(Room room);

    Room findByRoomId(Long id);

    List<Room> findAll();
}
