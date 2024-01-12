package com.example.notodo_backend.domain.teamTodo.dto;

import com.example.notodo_backend.domain.teamTodo.entity.CategoryEntity;
import com.example.notodo_backend.domain.user.dto.AuthorDto;
import lombok.Data;

@Data
public class ChannelRequestDto {
    private final String title;
    private final String description;
    private final String goal;
    private final CategoryEntity category;
    private final AuthorDto author;


}
