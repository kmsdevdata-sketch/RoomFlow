package com.roomflow.session;

import com.roomflow.domain.user.User;
import com.roomflow.web.session.SessionMng;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class SessionMngTest {

    SessionMng sessionMng = new SessionMng();

    @Test
    void sessionTest() {

        // 로그인 -> 서버가 세션 생성
        // 실제 서버없이 HttpServletResponse 역할을 대체
        MockHttpServletResponse response = new MockHttpServletResponse();
        User user = new User("123", "123", "kim", LocalDate.now(), "01012345678", "email.com");
        //서버 -> 클라이언트 쿠키 전달
        sessionMng.createSession(user,response);

        //요청에 응답 쿠키 저장
        // 클라이언트 -> 다음 요청에서 쿠키 전송
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //서버 -> 쿠키 기반 세션조회 
        Object result = sessionMng.getLoginUser(request);
        assertThat(result).isEqualTo(user);
        
        //세션 만료 
        sessionMng.expire(request);
        Object expire = sessionMng.getLoginUser(request);
        assertThat(expire).isNull();
    }
}