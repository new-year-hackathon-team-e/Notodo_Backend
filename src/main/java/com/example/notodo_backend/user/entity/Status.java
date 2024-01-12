package com.example.notodo_backend.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
    ACTIVE("활동중"),
    SUSPEND("탈퇴");
    private final String status;
}
