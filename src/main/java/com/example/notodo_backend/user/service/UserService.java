package com.example.notodo_backend.user.service;

import com.example.notodo_backend.global.exception.ApiException;
import com.example.notodo_backend.global.message.UserMessage;
import com.example.notodo_backend.user.dto.AuthorDto;
import com.example.notodo_backend.user.entity.UserEntity;
import com.example.notodo_backend.user.repository.UserRepository;
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

    public int updateReminderDay(String email, int reminderDay) {
        UserEntity user = findUserByEmail(email);
        user.updateReminderDay(reminderDay);
        userRepository.save(user);
        return reminderDay;
    }
}
