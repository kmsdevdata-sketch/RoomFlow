package com.roomflow.web.controller.room;


import com.roomflow.domain.room.Room;
import com.roomflow.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("rooms")
@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;

    // 방 목록
    @GetMapping()
    public String rooms(Model model) {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "room/rooms";
    }

    // 방 상세
    @GetMapping("/{roomId}")
    public String roomDetail(@PathVariable Long roomId, Model model) {
        Room findRoom = roomRepository.findById(roomId);
        model.addAttribute("room", findRoom);
        return "room/room";
    }

    // 예약 페이지
    @GetMapping("/{roomId}/reserve")
    public String reserve(@PathVariable Long roomId, Model model) {
        Room findRoom = roomRepository.findById(roomId);
        model.addAttribute("room", findRoom);
        return "room/reserve";
    }
}
