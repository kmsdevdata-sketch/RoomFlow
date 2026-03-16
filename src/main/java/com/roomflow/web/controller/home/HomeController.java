package com.roomflow.web.controller.home;

import com.roomflow.web.session.SessionConst;
import com.roomflow.domain.user.entity.User;
import com.roomflow.domain.user.repository.UserRepository;
import com.roomflow.web.session.SessionMng;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final SessionMng sessionMng;

    @GetMapping("/")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_USER,required = false) User loginUser
            , Model model) {

        // 세션 회원데이터 없을때
        if (loginUser == null) {
            return "home/home";
        }
        // 세션 유지되면 로그인 이동
        model.addAttribute("user", loginUser);
        return "home/loginHome";
    }
}
