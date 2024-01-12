package com.example.notodo_backend.domain.teamTodo.dto;

import com.example.notodo_backend.domain.teamTodo.entity.CategoryEntity;
import com.example.notodo_backend.domain.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class ChannelCreateResponseDto {
    private  Long id;
    private  String title;
    private  String description;
    private  String goal;
    private  Long categoryId;




}
