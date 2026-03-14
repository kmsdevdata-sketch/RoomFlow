package com.roomflow.web.controller.reservation;

import com.roomflow.domain.reservation.ReservationService;
import com.roomflow.web.controller.reservation.dto.ReservationCreateDto;
import com.roomflow.web.session.SessionConst;
import com.roomflow.domain.reservation.Reservation;
import com.roomflow.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("reservations")
@Controller
public class ReservationController {

    private final ReservationService reservationService;

    // 예약 목록
    @GetMapping()
    public String reservations(Model model,
                               @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        List<Reservation> reservationList = reservationService.findByUserId(loginUser.getId());
        model.addAttribute("reservations", reservationList);
        return "reservation/reservations";
    }

    // 예약 생성
    @PostMapping()
    public String createReservation(@Valid ReservationCreateDto reservationCreateDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        if (bindingResult.hasErrors()) {
            log.info("validation error = {}", bindingResult);
            return "room/reserve";
        }

        // 권한인증 구현후 삭제
        if (loginUser == null) {
            return "redirect:/login";
        }
        log.info("ReservationController createReservation join start");
        Long joinId = reservationService.join(reservationCreateDto, loginUser);
        log.info("ReservationController createReservation join end");
        redirectAttributes.addAttribute("reservationId", joinId);
        return "redirect:/reservations/{reservationId}";
    }

    // 예약 상세
    @GetMapping("/{reservationId}")
    public String reservation(@PathVariable Long reservationId, Model model) {
        Reservation findReservation = reservationService.findReservationById(reservationId);
        model.addAttribute("reservation", findReservation);
        return "/reservation/reservation";
    }

    @PostMapping("/{reservationId}/cancel")
    public String cancel(@PathVariable Long reservationId, Model model,
                         RedirectAttributes redirectAttributes) {

        try {
            reservationService.cancelReservation(reservationId);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage()); //View에서 alert처리
        }
        // 추후 취소 페이지도 고려해보기
        return "redirect:/reservation/reservations";
    }
}
