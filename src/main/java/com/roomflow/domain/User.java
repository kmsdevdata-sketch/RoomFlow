package com.roomflow.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data // @Data사용하면 설계상 안좋다고 하지만 직접체감하지 못해봐서 그냥 사용해보기
public class User {
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password; // 문자열 해싱 저장을 하기 떄문에 String 이용
    @NotEmpty
    private String name;
    private LocalDate birthDate; //나이는 매년 변하기 때문에 계산값으로
    @NotEmpty
    private String phoneNumber;
    private String email;
    private Role role;

    public User(String loginId, String password, String name, LocalDate birthDate, String phoneNumber, String email) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
