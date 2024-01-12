package com.example.notodo_backend.domain.user.controller;

import com.example.notodo_backend.global.dto.NotTodoApiResponse;
import com.example.notodo_backend.global.jwt.dto.TokenResponse;
import com.example.notodo_backend.global.jwt.oauth.UserInfo;
import com.example.notodo_backend.global.message.UserMessage;
import com.example.notodo_backend.domain.user.dto.LogoutRequest;
import com.example.notodo_backend.domain.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class AuthController {
    private final AuthService authService;

    @GetMapping("kakaologin")
    public NotTodoApiResponse<TokenResponse> kakaoLogin(String accessToken) {
        TokenResponse response = authService.kakaoLogin(accessToken );
        return NotTodoApiResponse.createResponse(response, UserMessage.LOGIN_SUCCESS);
    }

    @Operation(summary = "토큰 재발급 API", description = "AccessToken 만료 시 RefreshToken을 가지고 AccessToken, RefreshToken을 재발급 받을 수 있습니다.")
    @PostMapping("auth/token")
    public NotTodoApiResponse<TokenResponse> reissue(@AuthenticationPrincipal UserInfo user,
                                                 @RequestBody LogoutRequest logoutRequest){
        TokenResponse response = authService.reissue(user.getEmail(), logoutRequest);
        return NotTodoApiResponse.createResponse(response, UserMessage.REISSUE_SUCCESS);
    }

    @Operation(summary = "로그아웃 API")
    @PostMapping("/auth/user/logout")
    public NotTodoApiResponse<String> logout(@AuthenticationPrincipal UserInfo user,
                                         @RequestBody LogoutRequest logoutRequest){
        authService.logout(user.getEmail(), logoutRequest);
        return NotTodoApiResponse.createResponse(null, UserMessage.LOGOUT_SUCCESS);
    }

    @Operation(summary = "회원 탈퇴 API")
    @PostMapping("/auth/user")
    public NotTodoApiResponse<String> deleteUser(@AuthenticationPrincipal UserInfo user){
        authService.deleteUser(user.getEmail());
        return NotTodoApiResponse.createResponse(null, UserMessage.DELETE_USER_SUCCESS);
    }

}


