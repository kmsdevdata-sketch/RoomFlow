package com.roomflow.domain.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data // @Data사용하면 설계상 안좋다고 하지만 직접체감하지 못해봐서 그냥 사용해보기
public class User {
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private LocalDate birthDate;
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
