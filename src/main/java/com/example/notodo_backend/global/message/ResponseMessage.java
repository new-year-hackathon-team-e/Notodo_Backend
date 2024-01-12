package com.example.notodo_backend.global.message;

import org.springframework.http.HttpStatus;

public interface ResponseMessage {
    String getMessage();
    HttpStatus getStatus();
}
