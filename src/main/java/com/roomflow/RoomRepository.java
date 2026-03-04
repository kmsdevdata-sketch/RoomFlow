package com.roomflow;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class RoomRepository {
    private final Map<Long, Room> store;
    private Long sequence = 0L;

    public void save(Room room) {
        room.setId(++sequence);
        store.put(room.getId(), room);
    }

    public Room findById(Long id) {
        return store.get(id);
    }

    public List<Room> findAll() {
        return new ArrayList<>(store.values());
    }
}
