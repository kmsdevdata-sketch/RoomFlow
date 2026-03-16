package com.roomflow.domain.user;

import com.roomflow.domain.common.BaseEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User extends BaseEntity {
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
