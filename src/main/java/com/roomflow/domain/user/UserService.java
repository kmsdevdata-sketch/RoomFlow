package com.roomflow.domain.user;

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
