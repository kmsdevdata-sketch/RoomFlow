package com.roomflow.domain.reservation;

import com.roomflow.domain.user.User;
import com.roomflow.web.controller.reservation.dto.ReservationCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;


    public Long join(ReservationCreateDto reservationCreateDto, User loginUser) {
        log.info("ReservationService Join Start");
        Reservation reservation = reservationCreateDto.toEntity();
        reservation.setUserId(loginUser.getId());
        reservation.setStatus(Status.RESERVATION);
        reservation.setCreatedTime(LocalTime.now());
        log.info("ReservationService Join End");
        return reservationRepository.save(reservation).getId();
    }

    public Reservation findReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findByUserId(Long id) {
        return reservationRepository.findByUserId(id);
    }
}
