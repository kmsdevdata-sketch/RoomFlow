package com.roomflow.web.controller.reservation;

import com.roomflow.domain.reservation.ReservationService;
import com.roomflow.web.controller.reservation.dto.ReservationCreateDto;
import com.roomflow.web.session.SessionConst;
import com.roomflow.domain.reservation.Reservation;
import com.roomflow.domain.user.User;
import com.roomflow.domain.reservation.ReservationRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequiredArgsConstructor
@RequestMapping("reservations")
@Controller
public class ReservationController {

    private final ReservationService reservationService;

    // 예약 목록
    @GetMapping()
    public String reservations(Model model) {

        return "reservation/reservations";
    }

    // 예약 생성
    @PostMapping()
    public String createReservation(@Valid ReservationCreateDto reservationCreateDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    @SessionAttribute(name = SessionConst.LOGIN_USER,required = false) User loginUser) {
        if (bindingResult.hasErrors()) {
            return "room/reserve";
        }
        // 바인딩단계에서 아마 실패해서 validation이 실행이 안되는거같음 그래서 추가 *DTO를 만들어야됌
        if (reservationCreateDto.getStartTime() == null || reservationCreateDto.getEndTime() == null) {
            bindingResult.reject("time", "예약 시간을 선택해주세요");
            return "room/reserve";
        }
        // 권한인증 구현후 삭제
        if (loginUser == null) {
            return "redirect:/login";
        }
        Long joinId = reservationService.join(reservationCreateDto, loginUser);

        redirectAttributes.addAttribute("reservationId", joinId);
        return "redirect:/reservations/{reservationId}";
    }

    // 예약 상세
    @GetMapping("/{reservationId}")
    public String reservation(@PathVariable Long reservationId,Model model) {
        Reservation findReservation = reservationService.findReservation(reservationId);
        model.addAttribute("reservation", findReservation);
        return "/reservation/reservation";
    }



}
