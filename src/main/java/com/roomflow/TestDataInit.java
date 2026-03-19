package com.roomflow;

import com.roomflow.domain.reservation.entity.Reservation;
import com.roomflow.domain.reservation.entity.Status;
import com.roomflow.domain.reservation.repository.ReservationRepository;
import com.roomflow.domain.room.entity.Room;
import com.roomflow.domain.room.repository.RoomRepository;
import com.roomflow.domain.user.entity.Role;
import com.roomflow.domain.user.entity.User;
import com.roomflow.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @PostConstruct
    public void init() {
        if (!userRepository.findAll().isEmpty() || !roomRepository.findAll().isEmpty() || !reservationRepository.findAll().isEmpty()) {
            log.info("test data already exists, skipping init");
            return;
        }

        User user1 = new User("abc1", "abcd", "kim", LocalDate.of(1998, 3, 10), "01012345678", "email1@test.com");
        user1.setRole(Role.USER);
        user1 = userRepository.save(user1);

        User user2 = new User("abc2", "efgh", "lee", LocalDate.of(1997, 7, 21), "01023456789", "email2@test.com");
        user2.setRole(Role.USER);
        user2 = userRepository.save(user2);

        User admin = new User("admin1", "admin", "park", LocalDate.of(1995, 12, 1), "01034567890", "admin@test.com");
        admin.setRole(Role.ADMIN);
        admin = userRepository.save(admin);

        Room room1 = roomRepository.save(new Room("스터디룸 A", 4,
                LocalTime.of(9,0),
                LocalTime.of(23,0),
                true));

        Room room2 = roomRepository.save(new Room("스터디룸 B", 6,
                LocalTime.of(9,0),
                LocalTime.of(23,0),
                true));

        Room room3 = roomRepository.save(new Room("회의실 C", 8,
                LocalTime.of(10,0),
                LocalTime.of(22,0),
                true));

        Room room4 = roomRepository.save(new Room("프라이빗룸 D", 2,
                LocalTime.of(9,0),
                LocalTime.of(23,0),
                true));

        Reservation reservation1 = new Reservation(
                room1.getId(),
                LocalDate.now().plusDays(1),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0)
        );
        reservation1.setUserId(user1.getId());
        reservation1.setStatus(Status.RESERVATION);
        reservation1.setCreatedTime(LocalDateTime.now().minusHours(2));
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation(
                room2.getId(),
                LocalDate.now().plusDays(2),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0)
        );
        reservation2.setUserId(user2.getId());
        reservation2.setStatus(Status.RESERVATION);
        reservation2.setCreatedTime(LocalDateTime.now().minusHours(1));
        reservationRepository.save(reservation2);

        Reservation reservation3 = new Reservation(
                room3.getId(),
                LocalDate.now().plusDays(3),
                LocalTime.of(9, 0),
                LocalTime.of(11, 0)
        );
        reservation3.setUserId(admin.getId());
        reservation3.setStatus(Status.COMPLETED);
        reservation3.setCreatedTime(LocalDateTime.now().minusDays(1));
        reservationRepository.save(reservation3);

        Reservation reservation4 = new Reservation(
                room4.getId(),
                LocalDate.now().plusDays(4),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0)
        );
        reservation4.setUserId(user1.getId());
        reservation4.setStatus(Status.CANCELED);
        reservation4.setCreatedTime(LocalDateTime.now().minusDays(2));
        reservationRepository.save(reservation4);

        log.info("test data init successes");
    }
}
