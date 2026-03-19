package com.roomflow.domain.reservation.repository;

import com.roomflow.domain.reservation.entity.Reservation;
import com.roomflow.domain.reservation.entity.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation reservation);

     Optional<Reservation> findById(Long id);

    List<Reservation> findAll();

    List<Reservation> findReservationListByUser(Long userId);

    List<Reservation> findReservationsByRoomAndDate(Long roomId, LocalDate date);

    void updateStatus(Reservation reservation);
}
