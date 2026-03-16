package com.roomflow.domain.user.service;

import com.roomflow.domain.user.entity.Role;
import com.roomflow.domain.user.entity.User;
import com.roomflow.domain.user.repository.UserRepository;
import com.roomflow.web.controller.user.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void join(UserCreateDto userDto) {
        User user = userDto.toEntity();
        user.setRole(Role.USER);
        userRepository.save(user);
    }
}
