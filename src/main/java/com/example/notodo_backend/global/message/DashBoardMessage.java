package com.example.notodo_backend.global.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum DashBoardMessage implements ResponseMessage {

    DASH_BOARD_CREATE_SUCCESS("대쉬보드 생성 성공", HttpStatus.OK),
    DASH_BOARD_GET_SUCCESS("채널 조회 성공", HttpStatus.OK),;

    private final String message;
    private final HttpStatus status;
}
