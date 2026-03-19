package com.roomflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoomflowApplication {

    /* TODO
        - [x] 도메인 기준 패키지 구조로 정리
        - [x] Controller -> Service -> Repository 흐름 분리
        - [x] DTO 기반 회원가입 / 예약 생성 처리
        - [x] HttpSession 기반 로그인 / 로그아웃
        - [x] 사용자별 예약 목록 조회
        - [x] 예약 취소 및 30분 전 제한 로직
        - [x] MySQL + JDBC Repository 전환
        - [x] JDBC 기준 테스트 데이터 초기화 정리
        - [x] 예약 가능 시간 조회를 DB 쿼리 중심으로 개선

        - [ ] 서비스 / 컨트롤러 전반 Optional 처리 일관성 정리
        - [ ] 로그인 검증용 인터셉터 적용
        - [ ] 비밀번호 암호화 적용
        - [ ] 예약 변경 기능 추가
        - [ ] 중복 예약 및 동시성 처리 보강
        - [ ] 관리자용 방 관리 기능 추가
     */

	public static void main(String[] args) {
		SpringApplication.run(RoomflowApplication.class, args);
	}

}
