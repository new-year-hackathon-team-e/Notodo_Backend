package com.example.notodo_backend.domain.mytodo.service;

import com.example.notodo_backend.domain.mytodo.dto.CreateNotodoResponseDto;
import com.example.notodo_backend.domain.mytodo.dto.NotodoRequestDto;
import com.example.notodo_backend.domain.mytodo.entity.NotodoEntity;
import com.example.notodo_backend.domain.mytodo.repository.NotodoRepository;
import com.example.notodo_backend.domain.user.entity.UserEntity;
import com.example.notodo_backend.domain.user.repository.UserRepository;
import com.example.notodo_backend.global.exception.ApiException;
import com.example.notodo_backend.global.message.NoTodoMessage;
import com.example.notodo_backend.global.message.UserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotodoService {

    private final UserRepository userRepository;
    private final NotodoRepository notodoRepository;

    @Transactional
    public CreateNotodoResponseDto createTodo(String content, Boolean checkBox, String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ApiException(UserMessage.USER_NOT_FOUND));
        NotodoEntity noTodoEntity = new NotodoEntity(content, checkBox , userEntity);
        noTodoEntity = notodoRepository.save(noTodoEntity);
        return new CreateNotodoResponseDto(noTodoEntity);
    }




    @Transactional
    public CreateNotodoResponseDto updateTodo(NotodoRequestDto notodoRequestDto, String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ApiException(UserMessage.USER_NOT_FOUND));
        NotodoEntity noTodoEntity = notodoRepository.findById(notodoRequestDto.getId()).orElseThrow(() -> new ApiException(NoTodoMessage.TODO_NOT_FOUND));
        NotodoEntity noTodoEntity2 = new NotodoEntity(notodoRequestDto);
        noTodoEntity2 = notodoRepository.save(noTodoEntity2);
        return new CreateNotodoResponseDto(noTodoEntity2);

    }
}
