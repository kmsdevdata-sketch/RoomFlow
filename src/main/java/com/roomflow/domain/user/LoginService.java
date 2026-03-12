package com.roomflow.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    // 옵셔널로 받아와서 패스워드 일치하면 해당 객체리턴 아니면 null
    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId).filter(u ->
                        u.getPassword().equals(password))
                        .orElse(null);

    }
}
