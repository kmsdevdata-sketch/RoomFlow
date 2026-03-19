package com.roomflow.web.controller.reservation;

import com.roomflow.domain.reservation.entity.Reservation;
import com.roomflow.domain.reservation.entity.Status;
import com.roomflow.domain.reservation.repository.ReservationRepository;
import com.roomflow.domain.reservation.service.ReservationService;
import com.roomflow.domain.room.repository.RoomRepository;
import com.roomflow.domain.user.repository.UserRepository;
import com.roomflow.support.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalTime.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class ReservationControllerTest {

    @Autowired
    ReservationService reservationService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @Test
    void cancel() {
        var users = TestDataFactory.createUsers(userRepository);
        var rooms = TestDataFactory.createRooms(roomRepository);

        Reservation reservation = new Reservation(
                rooms.get(0).getId(),
                LocalDate.now().plusDays(1),
                of(10, 0),
                of(12, 0)
        );
        reservation.setUserId(users.get(0).getId());
        reservation.setStatus(Status.RESERVATION);
        reservation.setCreatedTime(LocalDateTime.now());

        Reservation savedReservation = reservationRepository.save(reservation);

        reservationService.cancelReservation(savedReservation.getId());

        Reservation updated = reservationRepository.findById(savedReservation.getId())
                .orElseThrow();

        assertThat(updated.getStatus()).isEqualTo(Status.CANCELED);
    }
}