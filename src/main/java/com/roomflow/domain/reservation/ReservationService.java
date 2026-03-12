package com.roomflow.domain.reservation;

import com.roomflow.domain.user.User;
import com.roomflow.web.controller.reservation.dto.ReservationCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;


    public Long join(ReservationCreateDto reservationCreateDto, User loginUser) {
        Reservation reservation = reservationCreateDto.toEntity();
        reservation.setUserId(loginUser.getId());
        reservation.setStatus(Status.RESERVATION);
        reservation.setCreatedTime(LocalTime.now());

        return reservationRepository.save(reservation).getId();
    }

    public Reservation findReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }
}
