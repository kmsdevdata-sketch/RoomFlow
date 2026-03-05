package com.roomflow.controller;

import com.roomflow.domain.Role;
import com.roomflow.domain.User;
import com.roomflow.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @GetMapping("/signup")
    // User 넣어주는 이유는 ModelAttribute로 사용하여 타임리프 페이지에서 폼에 바인딩을 위해
    public String signup(User user) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String save(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }
        // 기존에 service를 만들지 않았지만 여기서 User생성시에 Role설정을 해줘야해서 service만들어봄
        // 만드는 와중에 아직 필요없지 않을까 싶어서 그냥 보류
        user.setRole(Role.USER);
        userRepository.save(user);
        log.info("save user={}",user);
        return "redirect:/";
    }
}
