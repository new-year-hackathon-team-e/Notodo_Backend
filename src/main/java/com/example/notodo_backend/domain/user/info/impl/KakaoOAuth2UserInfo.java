package com.example.notodo_backend.domain.user.info.impl;

import com.example.notodo_backend.global.jwt.dto.KakaoUserResponse;
import com.example.notodo_backend.domain.user.entity.RoleType;
import com.example.notodo_backend.domain.user.entity.SocialType;
import com.example.notodo_backend.domain.user.entity.Status;
import com.example.notodo_backend.domain.user.entity.UserEntity;
import com.example.notodo_backend.domain.user.info.OAuth2UserInfo;
import lombok.Getter;

@Getter
public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    private String id;
    private String nickname;
    private String email;
    private String imageUrl;

    private final SocialType socialType = SocialType.KAKAO;

    public UserEntity createUserEntity(KakaoOAuth2UserInfo kakaoProfile) {
        new UserEntity();
        return UserEntity.builder()
                .email(kakaoProfile.getEmail())
                .nickname(kakaoProfile.getNickname())
                .profileImageUrl(kakaoProfile.getImageUrl())
                .userRole(RoleType.MEMBER)
                .socialType(SocialType.KAKAO)
                .status(Status.ACTIVE)
                .build();
    }

    public KakaoOAuth2UserInfo(){
    }

    public KakaoOAuth2UserInfo(KakaoUserResponse kakaoProfile) {
        this.email = kakaoProfile.getKakao_account().getEmail();
        this.nickname = kakaoProfile.getKakao_account().getProfile().getNickname();
        this.imageUrl = kakaoProfile.getProperties().getProfile_image();
    }
}
