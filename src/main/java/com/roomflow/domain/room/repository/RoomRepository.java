package com.roomflow.domain.room.repository;

import com.roomflow.domain.room.entity.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room save(Room room);

    Optional<Room> findByRoomId(Long id);

    List<Room> findAll();
}
