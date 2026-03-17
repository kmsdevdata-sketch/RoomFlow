package com.roomflow.repository;

import com.roomflow.domain.user.entity.User;
import com.roomflow.domain.user.repository.MemoryUserRepository;
import com.roomflow.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UserRepositoryTest {

    MemoryUserRepository userRepository = new MemoryUserRepository();

    @AfterEach
    void afterEach() {
        userRepository.clearStore();
    }
    @Test
    void save() {
        // given
        User user = new User("123", "123", "kim", LocalDate.now(), "01012345678", "email.com");
        // when
        User savedUser = userRepository.save(user);
        // then
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(()-> new NotFoundException("유저가 존재하지 않습니다"));
        assertThat(findUser).isEqualTo(savedUser);
    }

    @Test
    void findById() {
        //given
        User user = new User("123", "123", "kim", LocalDate.now(), "01012345678", "email.com");
        //when
        User savedUser = userRepository.save(user);
        User findUser = userRepository.findById(savedUser.getId())
                .orElseThrow(()-> new NotFoundException("유저가 존재하지 않습니다"));
        //then
        assertThat(savedUser).isEqualTo(findUser);
    }

    @Test
    void findAll() {
        //given
        User user1 = new User("123", "123", "kim", LocalDate.now(), "01012345678", "email.com");
        User user2 = new User("123", "123", "kim", LocalDate.now(), "01012345678", "email.com");

        userRepository.save(user1);
        userRepository.save(user2);
        //when

        List<User> result = userRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(user1, user2);
    }
}