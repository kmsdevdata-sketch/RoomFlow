package com.roomflow.web.controller.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// WebMvcTest가 임포트가 안됌

// 애플리케이션 동일로딩 밑에떄문에 필요
@SpringBootTest
// MockMvc Bean 생성
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    // Http요청 없이 SpringMvc테스트하는 도구
    MockMvc mockMvc;

    @Test
    void validation_실패() throws Exception {

        mockMvc.perform(post("/auth/signup") // HTTP 요청 실행 -> POST요청 생성
                        .param("loginId","") // HTTP Form에 파라미터 추가
                        .param("password","123")
                        .param("name","t")
                        .param("phoneNumber","123"))
                .andExpect(status().isOk()) // 상태코드 검증  return "auth/login"; 이여서
                .andExpect(model().hasErrors()); // model에 에러있는지 검증 : binding result가 모델에 집어넣으니

    }

    @Test
    void validation_성공() throws Exception {

        mockMvc.perform(post("/auth/signup")
                        .param("loginId","testtest")
                        .param("password","password123")
                        .param("name","tester")
                        .param("phoneNumber","01012341234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

    }

}