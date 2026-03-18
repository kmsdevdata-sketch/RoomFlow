package com.roomflow.domain.reservation.repository;

import com.roomflow.domain.reservation.entity.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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
}