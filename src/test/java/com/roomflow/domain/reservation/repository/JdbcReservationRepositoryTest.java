package com.roomflow.domain.reservation.repository;

import com.roomflow.domain.reservation.entity.Reservation;
import com.roomflow.domain.reservation.entity.Status;
import com.roomflow.domain.room.repository.RoomRepository;
import com.roomflow.domain.user.repository.UserRepository;
import com.roomflow.support.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JdbcReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void save_findById() {
        // given
        var users = TestDataFactory.createUsers(userRepository);
        var rooms = TestDataFactory.createRooms(roomRepository);

        Reservation reservation = new Reservation(
                rooms.get(0).getId(),
                LocalDate.now().plusDays(1),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0)
        );
        reservation.setUserId(users.get(0).getId());
        reservation.setStatus(Status.RESERVATION);
        reservation.setCreatedTime(LocalDateTime.now());

        // when
        Reservation savedReservation = reservationRepository.save(reservation);
        Optional<Reservation> found = reservationRepository.findById(savedReservation.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getRoomId()).isEqualTo(rooms.get(0).getId());
        assertThat(found.get().getUserId()).isEqualTo(users.get(0).getId());
    }
}
