package com.roomflow.controller;

import com.roomflow.domain.User;
import com.roomflow.repository.UserRepository;
import com.roomflow.session.SessionMng;
import jakarta.servlet.http.HttpServletRequest;
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
    private final SessionMng sessionMng;

//    @GetMapping("/")
    public String home() {
        return "home/home.html";
    }

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {

        // 우선은 다운캐스팅 사용 제너릭변경 필요
        User loginUser = (User) sessionMng.getLoginUser(request);
        if (loginUser == null) {
            return "home/home";
        }
        model.addAttribute("user", loginUser);
        return "home/loginHome";
    }
}
