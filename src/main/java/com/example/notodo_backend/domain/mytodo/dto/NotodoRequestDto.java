package com.example.notodo_backend.domain.mytodo.dto;

import lombok.Getter;

@Getter
public class NotodoRequestDto {

    private Long id;
    private String content;
    private Boolean checkBox;
}
