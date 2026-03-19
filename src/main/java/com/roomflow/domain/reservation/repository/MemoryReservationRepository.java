package com.roomflow.domain.reservation.repository;

import com.roomflow.domain.reservation.entity.Reservation;
import com.roomflow.domain.reservation.entity.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Repository
public class MemoryReservationRepository implements ReservationRepository{

    private final Map<Long, Reservation> store = new HashMap<>();
    private Long sequence = 0L;

    public Reservation save(Reservation reservation) {
        reservation.setId(++sequence);
        store.put(reservation.getId(), reservation);
        return reservation;
    }

    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Reservation> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<Reservation> findReservationListByUser(Long userId) {
        return store.values().stream()
                .filter(r -> userId.equals(r.getUserId())) // NPE발생하지 않도록 하는게 좋은습관
                .toList();
    }

    @Override
    public List<Reservation> findReservationsByRoomAndDate(Long roomId, LocalDate date) {
        return findAll().stream()
                .filter(reservation -> reservation.getRoomId().equals(roomId)
                        && reservation.getDate().equals(date)
                        && reservation.getStatus().equals(Status.RESERVATION)) // 선택한방 & 선택한 날짜와 동일한
                .sorted(Comparator.comparing(Reservation::getStartTime))
                .toList();
    }
}