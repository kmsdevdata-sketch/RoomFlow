package com.roomflow.domain.reservation.repository;

import com.roomflow.domain.reservation.entity.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class MemoryReservationRepository implements ReservationRepository{

    private final Map<Long, Reservation> store = new HashMap<>();
    private Long sequence = 0L;

    public Reservation save(Reservation reservation) {
        log.info("Reservation save start");
        reservation.setId(++sequence);
        store.put(reservation.getId(), reservation);
        log.info("Reservation save End");
        return reservation;
    }

    public  Reservation findById(Long id) {
        return store.get(id);
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