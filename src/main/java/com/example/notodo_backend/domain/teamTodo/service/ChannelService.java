package com.example.notodo_backend.domain.teamTodo.service;

import com.example.notodo_backend.domain.teamTodo.dto.ChannelCreateResponseDto;
import com.example.notodo_backend.domain.teamTodo.dto.ChannelRequestDto;
import com.example.notodo_backend.domain.teamTodo.entity.ChannelEntity;
import com.example.notodo_backend.domain.teamTodo.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;

    @Transactional
    public ChannelEntity createChannel(ChannelRequestDto channelRequestDto, String email) {
        ChannelEntity channelEntity = new ChannelEntity(channelRequestDto, email);
        channelEntity = channelRepository.save(channelEntity);

        return channelEntity;

    }

    public List<ChannelEntity> getChannel(Long categoryId) {
        List<ChannelEntity> channelEntity = channelRepository.findAllByCategoryId(categoryId);
        return channelEntity;
    }


}


