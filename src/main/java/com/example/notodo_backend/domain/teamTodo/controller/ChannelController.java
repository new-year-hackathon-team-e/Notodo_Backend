package com.example.notodo_backend.domain.teamTodo.controller;
import com.example.notodo_backend.domain.teamTodo.dto.ChannelCreateResponseDto;
import com.example.notodo_backend.domain.teamTodo.dto.ChannelRequestDto;
import com.example.notodo_backend.domain.teamTodo.entity.ChannelEntity;
import com.example.notodo_backend.domain.teamTodo.service.ChannelService;
import com.example.notodo_backend.global.dto.NoTodoApiResponse;
import com.example.notodo_backend.global.jwt.oauth.UserInfo;
import com.example.notodo_backend.global.message.ChannelMassage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/channel")
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping("")
    NoTodoApiResponse<ChannelEntity> createChannel(@RequestBody ChannelRequestDto channelCreateResponseDto, @AuthenticationPrincipal UserInfo user) {
        ChannelEntity channelEntity = channelService.createChannel(channelCreateResponseDto, user.getEmail());
        return NoTodoApiResponse.createResponse(channelEntity, ChannelMassage.CHNNEL_CREATE_SUCCESS);
    }

    @GetMapping("{categoryId}")
    NoTodoApiResponse<List<ChannelEntity>> getAllChannel(@PathVariable Long categoryId) {
        List<ChannelEntity> channelEntityList = channelService.getChannel(categoryId);
        return NoTodoApiResponse.createResponse(channelEntityList, ChannelMassage.CHNNEL_GET_SUCCESS);
    }



}
