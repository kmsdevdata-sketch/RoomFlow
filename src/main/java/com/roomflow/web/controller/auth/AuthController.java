package com.roomflow.controller;

import com.roomflow.SessionConst;
import com.roomflow.domain.LoginForm;
import com.roomflow.domain.Role;
import com.roomflow.domain.User;
import com.roomflow.repository.UserRepository;
import com.roomflow.service.LoginService;
import com.roomflow.session.SessionMng;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    public String signup(User user) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String save(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }
        // TODO UserService필요
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
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        User loginUser = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "auth/login";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환 , 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);
        // TODO 로그인 관련 간단한정보만 fit하게 넣게 수정필요
        /*
        1.HttpSession 생성
        2.sessionId생성
        3.서버 세션 저장소에 세션생성
        4.클라이언트에게 sessionId쿠키 발급
        5.세션에 attribute(key,value)저장
        6.이후 요청에서 sessionId로 세션조회
         */
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("logout");
        return "redirect:/";
    }

}
