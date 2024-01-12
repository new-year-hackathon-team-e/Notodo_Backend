package com.example.notodo_backend.global.dto;

import com.example.notodo_backend.global.message.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoTodoApiResponse<T> {
    T data;
    String message;
    String code;

    public static <G> NoTodoApiResponse<G> createResponse(G data, ResponseMessage responseMessage) {
        return new NoTodoApiResponse<>(data, responseMessage.getMessage(), responseMessage.toString());
    }
}
