package com.roomflow;

import java.time.LocalDate;

public class User {
    private Long id;
    private String loginId;
    private String password; // 문자열 해싱 저장을 하기 떄문에 String 이용
    private String name;
    private LocalDate birthDate; //나이는 매년 변하기 때문에 계산값으로
    private String phoneNumber;
    private String email;
    private Role role;
}
