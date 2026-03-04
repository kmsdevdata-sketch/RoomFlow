package com.roomflow.repository;

import com.roomflow.domain.Room;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomRepository {
    private final Map<Long, Room> store = new HashMap<>();
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
