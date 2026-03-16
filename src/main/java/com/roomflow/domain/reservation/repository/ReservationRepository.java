package com.roomflow.domain.reservation.repository;

import com.roomflow.domain.reservation.entity.Reservation;

import java.util.ArrayList;
import java.util.List;

public interface ReservationRepository {
    Reservation save(Reservation reservation);

    Reservation findById(Long id);

    List<Reservation> findAll();

    List<Reservation> findReservationListByUser(Long userId);
}
