package com.roomflow.domain.reservation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationTest {
    @Test
    void cancel() {
        // given
        Reservation reservation = new Reservation(
                1L,
                LocalDate.now(), // 예약 날짜
                LocalTime.now().plusHours(2), // 예약 시작 시간
                LocalTime.now().plusHours(3) // 예약 종료 시간
        );

        // when
        reservation.cancel();

        // then
        assertThat(reservation.getStatus()).isEqualTo(Status.CANCELED);
    }

    @Test
    void cancel_실패() {
        // given
        Reservation reservation = new Reservation(
                1L,
                LocalDate.now(),
                LocalTime.now().plusMinutes(10),
                LocalTime.now().plusHours(1)
        );

        // when & then
        assertThatThrownBy(reservation::cancel)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("예약 시작 30분 전까지만 취소 가능합니다.");
    }

}