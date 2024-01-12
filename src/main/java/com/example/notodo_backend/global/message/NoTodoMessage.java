package com.example.notodo_backend.global.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NoTodoMessage implements ResponseMessage {
    TODO_CREATE_SUCCESS("TODO 생성 성공", HttpStatus.OK),
    TODO_UPDATE_SUCCESS("TODO 수정 성공", HttpStatus.OK),

    TODO_NOT_FOUND("TODO를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),;
    private final String message;
    private final HttpStatus status;
}
