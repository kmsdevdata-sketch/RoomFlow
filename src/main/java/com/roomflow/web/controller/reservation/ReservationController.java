package com.roomflow.web.controller.reservation;

import com.roomflow.web.session.SessionConst;
import com.roomflow.domain.reservation.Reservation;
import com.roomflow.domain.reservation.Status;
import com.roomflow.domain.user.User;
import com.roomflow.domain.reservation.ReservationRepository;
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
    // TODO form DTO가 필요할것 같음
    @PostMapping()
    public String createReservation(@Valid Reservation reservation,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    @SessionAttribute(name = SessionConst.LOGIN_USER,required = false) User loginUser) {
        if (bindingResult.hasErrors()) {
            return "room/reserve";
        }
        // 바인딩단계에서 아마 실패해서 validation이 실행이 안되는거같음 그래서 추가 *DTO를 만들어야됌
        if (reservation.getStartTime() == null || reservation.getEndTime() == null) {
            bindingResult.reject("time", "예약 시간을 선택해주세요");
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



}
