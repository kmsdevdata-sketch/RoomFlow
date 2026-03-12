package com.roomflow.domain.user;

import com.roomflow.web.controller.user.dto.UserCreateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserRepository userRepository = new UserRepository();
    UserService userService = new UserService(userRepository);

    @Test
    void join() {

        UserCreateDto dto = new UserCreateDto();
        dto.setLoginId("testtest");
        dto.setPassword("password");
        dto.setName("test");
        dto.setBirthDate(LocalDate.of(2000, 01, 01));
        dto.setPhoneNumber("01012341234");
        dto.setEmail("test@gamil.com");

        userService.join(dto);

        // 옵셔널 반환
        User saveUser = userRepository.findByLoginId("testtest").get();

        assertThat(saveUser.getLoginId()).isEqualTo("testtest");
        assertThat(saveUser.getRole()).isEqualTo(Role.USER);
    }
}