package com.roomflow.controller;

import com.roomflow.domain.LoginForm;
import com.roomflow.domain.Role;
import com.roomflow.domain.User;
import com.roomflow.repository.UserRepository;
import com.roomflow.service.LoginService;
import com.roomflow.session.SessionMng;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    private final LoginService loginService;
    private final UserRepository userRepository;
    private final SessionMng sessionMng;

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
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(LoginForm loginForm) {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        User loginUser = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "auth/login";
        }

        // 로그인 성공하면

        //세션 관리자로 세션생성
        sessionMng.createSession(loginUser,response);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        sessionMng.expire(request);
        log.info("logout");
        return "redirect:/";
    }

    private static void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName,null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
