package com.roomflow.domain.reservation;

public enum Status {
    RESERVATION("예약"),
    CANCELED("예약 취소"),
    COMPLETED("이용완료");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
