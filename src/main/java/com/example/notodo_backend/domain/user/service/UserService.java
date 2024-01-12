package com.example.notodo_backend.domain.user.service;

import com.example.notodo_backend.domain.user.repository.UserRepository;
import com.example.notodo_backend.global.exception.ApiException;
import com.example.notodo_backend.global.message.UserMessage;
import com.example.notodo_backend.domain.user.dto.AuthorDto;
import com.example.notodo_backend.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void updateNickname(String email, String nickname) {
        UserEntity user = findUserByEmail(email);
        user.updateNickName(nickname);
        userRepository.save(user);
    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ApiException(UserMessage.USER_NOT_FOUND)
                );
    }

    public AuthorDto getUserInfo(String email) {
        UserEntity user = findUserByEmail(email);
        return AuthorDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }

}
