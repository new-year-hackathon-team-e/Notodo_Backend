package com.example.notodo_backend.domain.teamTodo.entity;

import com.example.notodo_backend.domain.user.entity.UserEntity;
import jakarta.persistence.*;

public class DashBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean checkDone;
    private Long duration;


    @ManyToOne
    @JoinColumn(name = "channel_id")
    private ChannelEntity channelEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    public DashBoardEntity(Long channelId, Boolean checkDone, String email) {
    }
}
