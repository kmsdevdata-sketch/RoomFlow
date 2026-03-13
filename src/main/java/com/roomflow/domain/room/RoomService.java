package com.roomflow.domain.room;

import com.roomflow.domain.reservation.ReservationRepository;
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
        return roomRepository.findByRoomId(roomId);
    }


}
