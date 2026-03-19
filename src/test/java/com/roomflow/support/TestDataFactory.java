package com.roomflow.support;

import com.roomflow.domain.reservation.entity.Reservation;
import com.roomflow.domain.reservation.entity.Status;
import com.roomflow.domain.reservation.repository.ReservationRepository;
import com.roomflow.domain.room.entity.Room;
import com.roomflow.domain.room.repository.RoomRepository;
import com.roomflow.domain.user.entity.Role;
import com.roomflow.domain.user.entity.User;
import com.roomflow.domain.user.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static List<User> createUsers(UserRepository userRepository) {
        User user1 = new User(
                "user01",
                "pass01",
                "kim",
                LocalDate.of(1998, 3, 10),
                "01011112222",
                "user01@test.com"
        );
        user1.setRole(Role.USER);

        User user2 = new User(
                "user02",
                "pass02",
                "lee",
                LocalDate.of(1997, 7, 21),
                "01022223333",
                "user02@test.com"
        );
        user2.setRole(Role.USER);

        User user3 = new User(
                "admin01",
                "adminpass",
                "park",
                LocalDate.of(1995, 12, 1),
                "01033334444",
                "admin01@test.com"
        );
        user3.setRole(Role.ADMIN);

        return List.of(
                userRepository.save(user1),
                userRepository.save(user2),
                userRepository.save(user3)
        );
    }

    public static List<Room> createRooms(RoomRepository roomRepository) {
        Room room1 = new Room(
                "Study Room A",
                4,
                LocalTime.of(9, 0),
                LocalTime.of(23, 0),
                true
        );

        Room room2 = new Room(
                "Study Room B",
                6,
                LocalTime.of(10, 0),
                LocalTime.of(22, 0),
                true
        );

        Room room3 = new Room(
                "Meeting Room C",
                8,
                LocalTime.of(8, 0),
                LocalTime.of(20, 0),
                true
        );

        return List.of(
                roomRepository.save(room1),
                roomRepository.save(room2),
                roomRepository.save(room3)
        );
    }

    public static List<Reservation> createReservations(
            ReservationRepository reservationRepository,
            List<User> users,
            List<Room> rooms
    ) {
        LocalDate baseDate = LocalDate.now().plusDays(2);
        LocalDateTime createdBase = LocalDateTime.now().minusDays(1);

        Reservation reservation1 = new Reservation(
                rooms.get(0).getId(),
                baseDate,
                LocalTime.of(10, 0),
                LocalTime.of(12, 0)
        );
        reservation1.setUserId(users.get(0).getId());
        reservation1.setStatus(Status.RESERVATION);
        reservation1.setCreatedTime(createdBase);

        Reservation reservation2 = new Reservation(
                rooms.get(1).getId(),
                baseDate.plusDays(1),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0)
        );
        reservation2.setUserId(users.get(1).getId());
        reservation2.setStatus(Status.RESERVATION);
        reservation2.setCreatedTime(createdBase.plusHours(1));

        Reservation reservation3 = new Reservation(
                rooms.get(2).getId(),
                baseDate.plusDays(2),
                LocalTime.of(9, 0),
                LocalTime.of(11, 0)
        );
        reservation3.setUserId(users.get(2).getId());
        reservation3.setStatus(Status.CANCELED);
        reservation3.setCreatedTime(createdBase.plusHours(2));

        Reservation reservation4 = new Reservation(
                rooms.get(0).getId(),
                baseDate.plusDays(3),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0)
        );
        reservation4.setUserId(users.get(0).getId());
        reservation4.setStatus(Status.COMPLETED);
        reservation4.setCreatedTime(createdBase.plusHours(3));

        Reservation reservation5 = new Reservation(
                rooms.get(1).getId(),
                baseDate.plusDays(4),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0)
        );
        reservation5.setUserId(users.get(1).getId());
        reservation5.setStatus(Status.RESERVATION);
        reservation5.setCreatedTime(createdBase.plusHours(4));

        return List.of(
                reservationRepository.save(reservation1),
                reservationRepository.save(reservation2),
                reservationRepository.save(reservation3),
                reservationRepository.save(reservation4),
                reservationRepository.save(reservation5)
        );
    }

    public static TestFixture createAll(
            UserRepository userRepository,
            RoomRepository roomRepository,
            ReservationRepository reservationRepository
    ) {
        List<User> users = createUsers(userRepository);
        List<Room> rooms = createRooms(roomRepository);
        List<Reservation> reservations = createReservations(reservationRepository, users, rooms);
        return new TestFixture(users, rooms, reservations);
    }

    public record TestFixture(
            List<User> users,
            List<Room> rooms,
            List<Reservation> reservations
    ) {
    }
}
