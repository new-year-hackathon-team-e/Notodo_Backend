package com.example.notodo_backend.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDto {
    private Long id;
    private String nickname;
    private String profileImageUrl;
}