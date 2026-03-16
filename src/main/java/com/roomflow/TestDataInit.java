package com.roomflow;

import com.roomflow.domain.room.entity.Room;
import com.roomflow.domain.user.entity.User;
import com.roomflow.domain.room.repository.MemoryRoomRepository;
import com.roomflow.domain.user.repository.MemoryUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemoryUserRepository userRepository;
    private final MemoryRoomRepository roomRepository;

    @PostConstruct
    public void init() {

        userRepository.save(new User("abc1","abcd","kim",LocalDate.now(),"01012345678","email1.com"));
        userRepository.save(new User("abc2","efgh","lee",LocalDate.now(),"01023456789","email2.com"));

        roomRepository.save(new Room(null,"스터디룸 A",4,
                LocalTime.of(9,0),
                LocalTime.of(23,0),
                true));

        roomRepository.save(new Room(null,"스터디룸 B",6,
                LocalTime.of(9,0),
                LocalTime.of(23,0),
                true));

        roomRepository.save(new Room(null,"회의실 C",8,
                LocalTime.of(10,0),
                LocalTime.of(22,0),
                true));

        roomRepository.save(new Room(null,"프라이빗룸 D",2,
                LocalTime.of(9,0),
                LocalTime.of(23,0),
                true));

        log.info("init successes");
    }
}
