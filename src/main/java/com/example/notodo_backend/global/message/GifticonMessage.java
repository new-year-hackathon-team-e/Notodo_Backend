package com.example.notodo_backend.global.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GifticonMessage implements ResponseMessage {
    GIFTICON_CREATE_SUCCESS("기프티콘 생성 성공", HttpStatus.OK),
    GIFTICON_CREATE_FAIL("기프티콘 생성 실패", HttpStatus.BAD_REQUEST),
    GIFTICON_DELETE_SUCCESS("기프티콘 삭제 성공", HttpStatus.OK),
    GIFTICON_DELETE_FAIL("기프티콘 삭제 실패", HttpStatus.BAD_REQUEST),
    GIFTICON_UPDATE_SUCCESS("기프티콘 수정 성공", HttpStatus.OK),
    GIFTICON_UPDATE_FAIL("기프티콘 수정 실패", HttpStatus.BAD_REQUEST),
    GIFTICON_NOT_FOUND("해당 기프티콘이 없습니다.", HttpStatus.BAD_REQUEST),
    GIFTICON_NOT_BELONG_TO_USER("해당 기프티콘이 유저에게 속해있지 않습니다.", HttpStatus.BAD_REQUEST),
    CATEGORY_CREATE_SUCCESS("카테고리 생성 성공", HttpStatus.OK),
    CATEGORY_NOT_FOUND("해당 카테고리가 없습니다.", HttpStatus.BAD_REQUEST),
    GIFTICON_GET_SUCCESS("기프티콘 조회 성공", HttpStatus.OK),
    CATEGORY_DELETE_SUCCESS("카테고리 삭제 성공", HttpStatus.OK),
    CATEGORY_ALREADY_EXIST("이미 존재하는 카테고리입니다.", HttpStatus.BAD_REQUEST),
    MONEY_HISTORY_GET_SUCCESS("금액권 사용기록 조회 성공", HttpStatus.OK),
    MONEY_HISTORY_CREATE_SUCCESS("금액권 사용기록 추가 성공", HttpStatus.OK),
    GIFTICON_USE_SUCCESS("기프티콘 사용 성공", HttpStatus.OK),
    CATEGORY_DUPLICATE("카테고리 이름이 중복됩니다.", HttpStatus.BAD_REQUEST),
    CATEGORY_UPDATE_SUCCESS("카테고리 수정 성공", HttpStatus.OK),
    ;
    private final String message;
    private final HttpStatus status;
    }
