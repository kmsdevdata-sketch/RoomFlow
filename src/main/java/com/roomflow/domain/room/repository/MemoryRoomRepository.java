package com.roomflow.domain.room.repository;

import com.roomflow.domain.room.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryRoomRepository implements RoomRepository{
    private final Map<Long, Room> store = new HashMap<>();
    private Long sequence = 0L;

    public Room save(Room room) {
        room.setId(++sequence);
        store.put(room.getId(), room);
        return room;
    }

    public Room findByRoomId(Long id) {
        return store.get(id);
    }

    public List<Room> findAll() {
        return new ArrayList<>(store.values());
    }
}
