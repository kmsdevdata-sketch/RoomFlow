package com.roomflow.controller;

import com.roomflow.SessionConst;
import com.roomflow.domain.Reservation;
import com.roomflow.domain.Status;
import com.roomflow.domain.User;
import com.roomflow.repository.ReservationRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalTime;

@RequiredArgsConstructor
@RequestMapping("reservations")
@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;

    // 예약 목록
    @GetMapping()
    public String reservations() {
        return "reservation/reservations";
    }

    // 예약 생성
    @PostMapping()
    public String createReservation(@Valid Reservation reservation,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    @SessionAttribute(name = SessionConst.LOGIN_USER,required = false) User loginUser) {
        if (bindingResult.hasErrors()) {
            return "room/reserve";
        }
        // 권한인증 구현후 삭제
        if (loginUser == null) {
            return "redirect:/login";
        }
        reservation.setUserId(loginUser.getId());
        reservation.setStatus(Status.RESERVATION);
        reservation.setCreatedTime(LocalTime.now());

        Reservation saveReservation = reservationRepository.save(reservation);
        redirectAttributes.addAttribute("reservationId", saveReservation.getId());
        return "redirect:/reservations/{reservationId}";
    }

    // 예약 상세
    @GetMapping("/{reservationId}")
    public String reservation(@PathVariable Long reservationId,Model model) {
        Reservation findReservation = reservationRepository.findById(reservationId);
        model.addAttribute("reservation", findReservation);
        return "/reservation/reservation";
    }

    // 예약 취소


}
