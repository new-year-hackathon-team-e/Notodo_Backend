package com.example.notodo_backend.domain.user.controller;

import com.example.notodo_backend.global.dto.NoTodoApiResponse;
import com.example.notodo_backend.global.jwt.oauth.UserInfo;
import com.example.notodo_backend.global.message.UserMessage;
import com.example.notodo_backend.domain.user.dto.AuthorDto;
import com.example.notodo_backend.domain.user.dto.NicknameRequest;
import com.example.notodo_backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PutMapping("/user")
    public NoTodoApiResponse<?> updateNickName(@AuthenticationPrincipal UserInfo user,
                                               @RequestBody NicknameRequest nicknameRequest) {
        userService.updateNickname(user.getEmail(), nicknameRequest.nickname());
        return NoTodoApiResponse.createResponse(null, UserMessage.NICKNAME_UPDATE_SUCCESS);
    }
    @GetMapping("/user/me")
    public NoTodoApiResponse<AuthorDto> getUserInfo(@AuthenticationPrincipal UserInfo user) {
        AuthorDto response = userService.getUserInfo(user.getEmail());
        return NoTodoApiResponse.createResponse(response, UserMessage.USER_INFO_GET_SUCCESS);
    }


}
