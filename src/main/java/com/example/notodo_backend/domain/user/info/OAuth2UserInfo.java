package com.example.notodo_backend.domain.user.info;


import com.example.notodo_backend.domain.user.entity.RoleType;
import com.example.notodo_backend.domain.user.entity.SocialType;
import com.example.notodo_backend.domain.user.entity.Status;
import com.example.notodo_backend.domain.user.entity.UserEntity;

public abstract class OAuth2UserInfo {
    public abstract String getId();

    public abstract String getNickname();

    public abstract String getEmail();

    public abstract String getImageUrl();

    public abstract SocialType getSocialType();


    public UserEntity createUserEntity() {
        new UserEntity();
        return UserEntity.builder()
                .email(getEmail())
                .nickname(getNickname())
                .profileImageUrl(getImageUrl())
                .userRole(RoleType.MEMBER)
                .socialType(getSocialType())
                .status(Status.ACTIVE)
                .build();
    }
}
