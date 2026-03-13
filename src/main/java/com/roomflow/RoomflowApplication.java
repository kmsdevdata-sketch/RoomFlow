package com.roomflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoomflowApplication {

    /* TODO
        ====================================================
        [1] Architecture / Structure
        --------------------------------
        - [x] UserCreateDto 도입
        - [x] Reservation 입력 DTO 도입 (Form 객체 분리)
        - [x] Domain별 Service Layer 구현
        - [x] Controller → Service → Repository 구조 정리
        [2] Authentication / Security
        --------------------------------
        - [ ] Session 기반 로그인 권한 검증 (Interceptor 적용)
        - [ ] 비밀번호 암호화 (BCrypt)
        [3] Reservation Domain
        --------------------------------
        - [x] 예약 시간 유효성 검증 (startTime < endTime)
        - [x] 예약 생성 로직 Service로 이동
        - [ ] 예약 중복 검증 로직 구현
        - [x] 예약 목록 조회 기능
        - [ ] 예약 취소 기능
        - [ ] 예약 취소 가능 시간 제한 로직
        [4] Concurrency / Validation
        --------------------------------
        - [x] 예약 목록 사용자별 구분
        - [ ] 동일 시간대 예약 동시성 문제 검증
        - [ ] 예약 시간 유효성 검증
        [5] Payment / Extension
        --------------------------------
        - [ ] 포인트 기반 결제 시스템
        - [ ] 결제 성공 후 예약 확정 처리
        ====================================================
     */

	public static void main(String[] args) {
		SpringApplication.run(RoomflowApplication.class, args);
	}

}
