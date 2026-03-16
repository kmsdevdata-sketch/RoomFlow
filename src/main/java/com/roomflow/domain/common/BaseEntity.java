package com.roomflow.domain.common;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class BaseEntity {
    protected Instant createdAt;
    protected Instant updatedAt;
}
