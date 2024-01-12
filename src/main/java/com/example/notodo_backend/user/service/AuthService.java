package com.example.notodo_backend.user.service;

import com.example.notodo_backend.global.exception.ApiException;
import com.example.notodo_backend.global.jwt.TokenProvider;
import com.example.notodo_backend.global.jwt.dto.KakaoUserResponse;
import com.example.notodo_backend.global.jwt.dto.TokenResponse;
import com.example.notodo_backend.global.message.UserMessage;
import com.example.notodo_backend.user.dto.LogoutRequest;
import com.example.notodo_backend.user.entity.RefreshTokenEntity;
import com.example.notodo_backend.user.entity.RoleType;
import com.example.notodo_backend.user.entity.UserEntity;
import com.example.notodo_backend.user.info.impl.KakaoOAuth2UserInfo;
import com.example.notodo_backend.user.repository.RefreshTokenRepository;
import com.example.notodo_backend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    public TokenResponse kakaoLogin(String accessToken, String tokenId) {
        KakaoOAuth2UserInfo profile = getUserInfoByToken(accessToken);

        UserEntity user = userRepository.findByEmail(profile.getEmail())
                .orElse(profile.createUserEntity());
        userRepository.save(user);
        return createToken(user);

    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    @Transactional
    TokenResponse createToken(UserEntity user) {
        TokenResponse tokenResponse = tokenProvider.generateJwtToken(user.getEmail(), user.getNickname(), RoleType.MEMBER);
        RefreshTokenEntity refreshTokenEntity = checkExistingRefreshToken(user.getId());

        if(refreshTokenEntity == null) {
            RefreshTokenEntity newRefreshTokenEntity = new RefreshTokenEntity(
                    user.getId(),
                    tokenResponse.refreshToken()
            );
            refreshTokenRepository.save(newRefreshTokenEntity);
        }
        else {
            refreshTokenEntity.updateRefreshToken(tokenResponse.refreshToken());
        }

        return tokenResponse;
    }

    private RefreshTokenEntity checkExistingRefreshToken(Long userId) {
        Optional<RefreshTokenEntity> result = refreshTokenRepository.findByUserId(userId);
        return result.orElse(null);
    }

    private KakaoOAuth2UserInfo getUserInfoByToken(String accessToken) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kapi.kakao.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().responseTimeout(Duration.ofSeconds(5))))
                .build();
        try {
            KakaoUserResponse kakaoProfile = webClient.post()
                    .uri("/v2/user/me")
                    .retrieve()
                    .bodyToMono(KakaoUserResponse.class)
                    .block();
            return new KakaoOAuth2UserInfo(kakaoProfile);
        }
        catch (Exception e) {
            throw new ApiException(UserMessage.KAKAO_LOGIN_FAILED);
        }

    }

    public TokenResponse reissue(String email, LogoutRequest logoutRequest) {
        UserEntity user = findUserByEmail(email);
        if(user == null) {
            throw new ApiException(UserMessage.USER_NOT_FOUND);
        }

        validateRefreshToken(logoutRequest.refreshToken());

        return createToken(user);
    }

    private void validateRefreshToken(String refreshToken) {
        if(!tokenProvider.validateToken(refreshToken))
            throw new ApiException(UserMessage.REFRESH_TOKEN_INVALID);
    }

    public void logout(String email, LogoutRequest logoutRequest) {
        String refreshToken = logoutRequest.refreshToken();
        validateRefreshToken(refreshToken);
        RefreshTokenEntity refreshTokenEntity = checkExistingRefreshToken(findUserByEmail(email).getId());
        if(refreshTokenEntity == null || !refreshToken.equals(refreshTokenEntity.getRefreshToken()))
            throw new ApiException(UserMessage.REFRESH_TOKEN_INVALID);
        refreshTokenRepository.delete(refreshTokenEntity);
    }

    public void deleteUser(String email) {
        UserEntity user = findUserByEmail(email);
        if(user == null) {
            throw new ApiException(UserMessage.USER_NOT_FOUND);
        }
        userRepository.delete(user);
    }
}
