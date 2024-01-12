package com.example.notodo_backend.domain.mytodo.dto;

import com.example.notodo_backend.domain.mytodo.entity.NotodoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreateNotodoResponseDto {
    @JsonProperty("content")
    private String content;

    @JsonProperty("checkBox")
    private Boolean checkBox;


    public CreateNotodoResponseDto(NotodoEntity noTodoEntity) {
        this.content = noTodoEntity.getContent();
        this.checkBox = noTodoEntity.getCheckBox();
    }
}