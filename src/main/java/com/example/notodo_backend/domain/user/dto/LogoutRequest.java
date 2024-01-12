package com.example.notodo_backend.domain.user.dto;

public record LogoutRequest(
        String refreshToken
) {
}
