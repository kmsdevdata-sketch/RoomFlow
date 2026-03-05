package com.roomflow;

import com.roomflow.domain.Role;
import com.roomflow.domain.User;
import com.roomflow.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user1 = new User("abc1", "abcd", "kim", LocalDate.now(), "01012345678", "email1.com");
        user1.setRole(Role.USER);
        User user2 = new User("abc2", "efgh", "lee", LocalDate.now(), "01023456789", "email2.com");
        user2.setRole(Role.USER);
        User user3 = new User("abc3", "ijkl", "park", LocalDate.now(), "01034567891", "email3.com");
        user3.setRole(Role.USER);
        User user4 = new User("abc4", "mnop", "choi", LocalDate.now(), "01045678912", "email4.com");
        user4.setRole(Role.USER);
        User user5 = new User("abc5", "qrst", "kang", LocalDate.now(), "01056789123", "email5.com");
        user5.setRole(Role.USER);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

    }
}
