package com.roomflow.web.controller.room;


import com.roomflow.domain.reservation.service.ReservationService;
import com.roomflow.domain.room.entity.Room;
import com.roomflow.domain.room.service.RoomService;
import com.roomflow.web.controller.reservation.dto.ReservationCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("rooms")
@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final ReservationService reservationService;

    // 방 목록
    @GetMapping()
    public String rooms(Model model) {
        List<Room> rooms = roomService.findAll();
        model.addAttribute("rooms", rooms);
        return "room/rooms";
    }

    // 방 상세
    @GetMapping("/{roomId}")
    public String roomDetail(@PathVariable Long roomId, Model model) {
        Room findRoom = roomService.findByRoomId(roomId);
        model.addAttribute("room", findRoom);
        return "room/room";
    }

    // 예약 페이지
    @GetMapping("/{roomId}/reserve")
    public String reserve(@PathVariable Long roomId,
                          @RequestParam(name = "date" , required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                          Model model) {
        if (date == null) {
            date = LocalDate.now();
        }


        Room findRoom = roomService.findByRoomId(roomId);
        List<Integer> reservedTimes = reservationService.getReservedTimeSlots(roomId, date);

        ReservationCreateDto dto = new ReservationCreateDto();
        dto.setDate(date);
        dto.setRoomId(roomId);

        model.addAttribute("room", findRoom);
        model.addAttribute("reservation", dto);
        model.addAttribute("reservationTimes", reservedTimes);

        return "room/reserve";
    }
}
