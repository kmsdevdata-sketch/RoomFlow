package com.roomflow.repository;

import com.roomflow.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReservationRepository {

    private final Map<Long, Reservation> store = new HashMap<>();
    private Long sequence = 0L;

    public void save(Reservation reservation) {
        reservation.setId(++sequence);
        store.put(reservation.getId(), reservation);
    }

    public Reservation findById(Long id) {
        return store.get(id);
    }

    public List<Reservation> findAll() {
        return new ArrayList<>(store.values());
    }

}
