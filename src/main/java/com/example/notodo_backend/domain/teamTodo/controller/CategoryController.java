package com.example.notodo_backend.domain.teamTodo.controller;

import com.example.notodo_backend.domain.teamTodo.entity.CategoryEntity;
import com.example.notodo_backend.domain.teamTodo.repository.CategoryRepository;
import com.example.notodo_backend.global.dto.NoTodoApiResponse;
import com.example.notodo_backend.global.message.CategoryMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    @GetMapping("")
    public NoTodoApiResponse<List<CategoryEntity>> getCategory() {
        List<CategoryEntity> categoryEntity = categoryRepository.findAll();
        return NoTodoApiResponse.createResponse(categoryEntity, CategoryMessage.CATEGORY_SHOW_ALL_SUCCESS);
    }

}
