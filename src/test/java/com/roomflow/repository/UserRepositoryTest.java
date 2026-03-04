package com.roomflow.repository;

import com.roomflow.domain.Role;
import com.roomflow.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    @AfterEach
    void afterEach() {
        userRepository.clearStore();
    }
    @Test
    void save() {
        // given
        User user = new User("123", "123", "kim", LocalDate.now(), "01012345678", "email.com", Role.USER);
        // when
        User savedUser = userRepository.save(user);
        // then
        User findUser = userRepository.findById(user.getId());
        assertThat(findUser).isEqualTo(savedUser);
    }

    @Test
    void findById() {
        //given
        User user = new User("123", "123", "kim", LocalDate.now(), "01012345678", "email.com", Role.USER);
        //when
        User savedUser = userRepository.save(user);
        User findUser = userRepository.findById(savedUser.getId());
        //then
        assertThat(savedUser).isEqualTo(findUser);
    }

    @Test
    void findAll() {
        //given
        User user1 = new User("123", "123", "kim", LocalDate.now(), "01012345678", "email.com", Role.USER);
        User user2 = new User("123", "123", "kim", LocalDate.now(), "01012345678", "email.com", Role.USER);

        userRepository.save(user1);
        userRepository.save(user2);
        //when

        List<User> result = userRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(user1, user2);
    }
}