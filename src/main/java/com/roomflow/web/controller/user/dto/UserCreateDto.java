package com.roomflow.web.controller.user.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UserCreateDto {

    @NotBlank
    @Size(min = 4, max = 20)
    private String loginId;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Size(min = 2, max = 10)
    private String name;

    @Past
    private LocalDate birthDate;

    @NotBlank
    @Pattern(regexp = "^01[0-9]-?\\d{3,4}-?\\d{4}$")
    private String phoneNumber;

    @Email
    private String email;

}
