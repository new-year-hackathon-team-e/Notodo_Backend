package com.example.notodo_backend.service;

import com.example.notodo_backend.dto.NotodoResponseDto;
import com.example.notodo_backend.dto.NotodoRequestDto;
import com.example.notodo_backend.entity.NotodoEntity;
import java.util.List;

//메서드 선언만
public interface NotodoService {
    NotodoRequestDto addNotodo(Long categoryId, NotodoRequestDto notodoRequestDto);
    void deleteNotodo(Long id);
    List<NotodoResponseDto> findAll(Long id);
    NotodoResponseDto findById(Long id);
    void update(Long id, NotodoRequestDto updateParam);
    boolean existsId(Long categoryId);


    // request
    // dto --> Entity 로 변환
    default NotodoEntity reqToEntity(NotodoRequestDto notodoRequestDto) {
        NotodoEntity notodoEntity = NotodoEntity.builder()
                .contents(notodoRequestDto.getContents())
                .complete_chk(notodoRequestDto.getComplete_chk())
                .build();
        return notodoEntity;
    }

    // Entity --> dto 로 변환
    default NotodoRequestDto reqToDto(NotodoEntity notodoEntity) {
        NotodoRequestDto notodoRequestDto = NotodoRequestDto.builder()
                .contents(notodoEntity.getContents())
                .complete_chk(notodoEntity.getComplete_chk())
                .build();
        return notodoRequestDto;
    }


    // response
    // dto --> Entity 로 변환
    default NotodoEntity resToEntity(NotodoResponseDto notodoResponseDto) {
        NotodoEntity notodoEntity = NotodoEntity.builder()
                .contents(notodoResponseDto.getContents())
                .complete_chk(notodoResponseDto.getComplete_chk())
                .build();
        return notodoEntity;
    }

    // Entity --> dto 로 변환
    default NotodoResponseDto resToDto(NotodoEntity notodoEntity) {
        NotodoResponseDto notodoResponseDto = NotodoResponseDto.builder()
                .id(notodoEntity.getId())
                .contents(notodoEntity.getContents())
                .complete_chk(notodoEntity.getComplete_chk())
                .categoryTitle(notodoEntity.getCategory().getTitle())
                .build();
        return notodoResponseDto;
    }
}
