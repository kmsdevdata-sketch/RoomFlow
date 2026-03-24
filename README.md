# Roomflow 
스터디룸 예약 관리 시스템 (학습용 프로젝트)

## 프로젝트 개요
공간 예약 서비스의 핵심 도메인 로직을 설계하고, Spring MVC의 계층별 책임 분리를 연습하기 위한 프로젝트입니다.
현재는 도메인 기반 패키지 구조 위에서 예약, 인증, 조회 흐름을 정리하고, MySQL + JDBC 기반 저장소 전환과 세션 인증, 에러 처리 학습 내용을 함께 반영하고 있습니다.

---

## 기술 스택
- **Language**: Java 21
- **Framework**: Spring Boot 3.4.3
- **Template Engine**: Thymeleaf
- **Library**: Lombok, Spring Validation, Spring JDBC
- **Database**: MySQL

---

## 아키텍처 및 패키지 구조
계층형 구조를 유지하되, 패키지는 도메인 기준으로 나누어 응집도를 높이는 방향으로 정리했습니다.

- `com.roomflow.domain`: 핵심 비즈니스 로직 (User, Room, Reservation)
  - `entity`, `repository`, `service`
- `com.roomflow.global`: 전역 설정 및 공통 웹 기술
  - `config`, `argumentresolver`, `interceptor`, `filter`, `exception`
- `com.roomflow.web`: 사용자 인터페이스 및 웹 관련 기술
  - `controller`, `session`
- `com.roomflow.exception.api`: API 예외 처리 학습용 컨트롤러

---

## 구현 현황 (Progress)

### 1. 사용자 인증 및 관리
- [x] 회원가입 (UserCreateDto 도입)
- [x] 로그인/로그아웃 (HttpSession 기반)
- [x] 사용자 권한 구분 (USER / ADMIN)
- [x] 세션 기반 로그인 사용자 관리
- [x] 인터셉터 기반 접근 제어 적용
- [x] `@Login` ArgumentResolver 도입
- [ ] 비밀번호 암호화 적용

### 2. 스터디룸 관리
- [x] Room 도메인 및 JDBC Repository 구성
- [x] 스터디룸 목록 조회
- [x] 예약 화면에서 날짜별 예약 시간 조회
- [x] 예약 시간대 비활성화 처리
- [ ] 스터디룸 상세 정보 보강
- [ ] 관리자 전용 스터디룸 등록/수정/삭제

### 3. 예약 시스템 (Core)
- [x] 타임슬롯 기반 예약 UI
- [x] 날짜별 예약 시간 비활성화 처리
- [x] 예약 생성 / 상세 / 목록 조회
- [x] 사용자별 예약 내역 조회
- [x] 예약 취소 기능
- [x] 예약 취소 30분 전 제한 로직
- [x] 예약 가능 시간 조회를 DB 기반 조회 메서드로 분리
- [ ] 예약 변경 기능
- [ ] 중복 예약 검증을 DB 조회 기반으로 정리

### 4. 데이터 저장소 전환
- [x] MySQL 연결 설정 분리 (`application.yml`, 환경변수 사용)
- [x] User / Room / Reservation JDBC Repository 구현
- [x] 테스트용 데이터 팩토리 및 초기 데이터 정리
- [x] 인터페이스 기반 Repository 주입 구조로 정리
- [ ] Repository 테스트 추가 보강
- [ ] 서비스 레이어 조회 로직 일부를 DB 쿼리 중심으로 정리

### 5. 에러 처리 및 예외 학습
- [x] 서블릿 예외 발생 흐름 테스트용 컨트롤러 추가
- [x] 에러 페이지용 컨트롤러 및 템플릿 구성
- [x] API 예외 응답 학습용 컨트롤러 추가
- [ ] 커스텀 에러 페이지 경로와 기본 `/error` 흐름 정리
- [ ] 예외 처리 정책을 운영용 구조로 정리

---

## 학습 포인트
1. 컨트롤러, 서비스, 레포지토리의 책임을 분리하면서 흐름을 단순하게 유지하는 방법
2. DTO를 통해 웹 요청 객체와 도메인 엔티티를 분리하는 방식
3. 메모리 저장소에서 JDBC 기반 저장소로 전환할 때 인터페이스 중심으로 구조를 바꾸는 과정
4. 예약 취소와 같은 도메인 규칙을 엔티티 내부 로직으로 다루는 방식
5. 서블릿 예외, 에러 페이지, 인터셉터/필터 로그 흐름을 직접 확인하는 과정

---

## To-Do & 확장 계획
- [ ] 비밀번호 암호화 적용
- [ ] 예약 변경 기능 추가
- [ ] 중복 예약 방지 및 동시성 처리 보강
- [ ] 예약 조회 로직 최적화
- [ ] 관리자용 방 관리 기능 추가
- [ ] 에러 처리 구조 정리 (`/error`, `/error-page`, API 예외 응답 기준 정리)
- [ ] 중복된 학습용 필터/인터셉터 사용 여부 최종 정리
- [ ] 테스트 코드 확장
