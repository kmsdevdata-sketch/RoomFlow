package com.roomflow.controller;

import com.roomflow.domain.User;
import com.roomflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

//    @GetMapping("/")
    public String home() {
        return "home/home.html";
    }

    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "userId", required = false) Long userId, Model model) {

        // 여기 아이디 검사는 쿠키가 없는 ( = 로그인을 안한) 사용자 거르기
        if (userId == null) {
            log.info("쿠키 미확인");
            return "home/home";
        }
        // 여기는 아이디를 입력했지만 (쿠키를 받았지만) 존재하지 않는 회원일떄
        User loginUser = userRepository.findById(userId);
        if (loginUser == null) {
            log.info("쿠키확인 & 아이디 미확인");
            return "home/home";
        }

        model.addAttribute("user", loginUser);
        log.info("쿠키확인 & 아이디 확인");
        return "home/loginHome";
    }
}
