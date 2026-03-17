package com.roomflow.domain.common;

import lombok.Data;
import lombok.Getter;

import java.time.Instant;

@Data
public abstract class BaseEntity {
    protected Instant createdAt;
    protected Instant updatedAt;
}
