package com.roomflow.domain.reservation.service;

import com.roomflow.domain.reservation.entity.Reservation;
import com.roomflow.domain.reservation.entity.Status;
import com.roomflow.domain.reservation.repository.ReservationRepository;
import com.roomflow.domain.user.entity.User;
import com.roomflow.exception.NotFoundException;
import com.roomflow.web.controller.reservation.dto.ReservationCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
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

    public Reservation findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new NotFoundException("찾으시는 예약이 존재하지 않습니다 "));
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findByUserId(Long id) {
        return reservationRepository.findReservationListByUser(id);
    }

    public List<Integer> findReservedTimesByRoomAndDate(Long roomId, LocalDate date) {
        return reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getRoomId().equals(roomId)
                                                    && reservation.getDate().equals(date)
                                                    && reservation.getStatus().equals(Status.RESERVATION)) // 선택한방 & 선택한 날짜와 동일한
                .flatMap(reservation -> {
                    int start = reservation.getStartTime().getHour();
                    int end = reservation.getEndTime().getHour();
                    return IntStream.range(start, end).boxed(); // 예약 구간을 시간단위로 분해 ex.10~13 => 10,11,12
                })
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("찾으시는 예약이 존재하지 않습니다"));
        reservation.cancel();
    }
}
