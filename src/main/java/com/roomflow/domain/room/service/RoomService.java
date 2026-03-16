package com.roomflow.domain.room.service;

import com.roomflow.domain.room.entity.Room;
import com.roomflow.domain.room.repository.MemoryRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final MemoryRoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findByRoomId(Long roomId) {
        return roomRepository.findByRoomId(roomId);
    }


}
