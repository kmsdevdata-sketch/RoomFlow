package com.roomflow.domain.room.service;

import com.roomflow.domain.room.entity.Room;
import com.roomflow.domain.room.repository.RoomRepository;
import com.roomflow.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findByRoomId(Long roomId) {
        return roomRepository.findByRoomId(roomId)
                .orElseThrow(()-> new NotFoundException("방이 존재하지 않습니다"));
    }


}
