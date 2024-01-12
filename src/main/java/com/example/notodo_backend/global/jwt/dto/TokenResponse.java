package com.example.notodo_backend.global.jwt.dto;


public record TokenResponse(
        String grantType,
        String accessToken,
        String refreshToken,
        Long accessTokenExpiresIn
) {

}