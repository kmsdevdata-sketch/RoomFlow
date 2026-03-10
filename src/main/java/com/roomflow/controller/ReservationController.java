package com.roomflow.controller;

import com.roomflow.domain.Reservation;
import com.roomflow.domain.Status;
import com.roomflow.repository.ReservationRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;

@RequiredArgsConstructor
@RequestMapping("/reservation")
@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;

    @GetMapping("/new")
    public String reservationForm() {
        return "reservation/reservationForm";
    }

    @GetMapping()
    public String reservations() {
        return "reservation/reservations";
    }

    @PostMapping()
    public String createReservation(@Valid Reservation reservation, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "reservation/reservationForm";
        }
        // TODO 추후 service에서 설정하는걸로 변경필요
        reservation.setCreatedTime(LocalTime.now());
        reservation.setStatus(Status.RESERVATION);

        reservationRepository.save(reservation);
        return "redirect:/reservation/" + reservation.getId();
    }

    @GetMapping("/{reservationId}")
    public String reservation(@PathVariable Long reservationId,
                              Model model) {

        Reservation findReservation = reservationRepository.findById(reservationId);

        model.addAttribute("reservation",findReservation);
        return "reservation/reservationDetail";
    }
}
