package com.roomflow.domain.reservation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ReservationRepository {

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

}
