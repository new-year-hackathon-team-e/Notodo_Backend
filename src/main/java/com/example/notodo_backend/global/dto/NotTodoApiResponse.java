package com.example.notodo_backend.global.dto;

import com.example.notodo_backend.global.message.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotTodoApiResponse<T> {
    T data;
    String message;
    String code;

    public static <G> NotTodoApiResponse<G> createResponse(G data, ResponseMessage responseMessage) {
        return new NotTodoApiResponse<>(data, responseMessage.getMessage(), responseMessage.toString());
    }
}
