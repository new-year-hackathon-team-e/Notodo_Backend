package com.example.notodo_backend.global.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

    @Getter
    @AllArgsConstructor
    public enum CategoryMessage implements ResponseMessage {
        CATEGORY_SHOW_ALL_SUCCESS("Category전체 조회 성공", HttpStatus.OK),

        ;

        private final String message;
        private final HttpStatus status;
    }
