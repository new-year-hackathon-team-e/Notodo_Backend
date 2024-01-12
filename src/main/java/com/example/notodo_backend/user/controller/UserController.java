package com.example.notodo_backend.user.controller;

import com.example.notodo_backend.global.dto.NotTodoApiResponse;
import com.example.notodo_backend.global.jwt.oauth.UserInfo;
import com.example.notodo_backend.global.message.UserMessage;
import com.example.notodo_backend.user.dto.AuthorDto;
import com.example.notodo_backend.user.dto.NicknameRequest;
import com.example.notodo_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PutMapping("/user")
    public NotTodoApiResponse<?> updateNickName(@AuthenticationPrincipal UserInfo user,
                                            @RequestBody NicknameRequest nicknameRequest) {
        userService.updateNickname(user.getEmail(), nicknameRequest.nickname());
        return NotTodoApiResponse.createResponse(null, UserMessage.NICKNAME_UPDATE_SUCCESS);
    }
    @GetMapping("/user/me")
    public NotTodoApiResponse<AuthorDto> getUserInfo(@AuthenticationPrincipal UserInfo user) {
        AuthorDto response = userService.getUserInfo(user.getEmail());
        return NotTodoApiResponse.createResponse(response, UserMessage.USER_INFO_GET_SUCCESS);
    }


}
