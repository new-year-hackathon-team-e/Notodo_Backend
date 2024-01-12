package com.example.notodo_backend.user.entity;

import com.example.notodo_backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String nickname;
    private String profileImageUrl;
    @Enumerated(EnumType.STRING)
    private RoleType userRole;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    @Enumerated(EnumType.STRING)
    private Status status;


    @Builder
    public UserEntity(String email,
                      String nickname,
                      String profileImageUrl,
                      RoleType userRole,
                      SocialType socialType,
                      Status status) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.userRole = userRole;
        this.socialType = socialType;
        this.status = status;
    }

    public void updateNickName(String nickname) {
        this.nickname = nickname;
    }

}
