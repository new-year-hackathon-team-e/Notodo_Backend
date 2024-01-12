package com.example.notodo_backend.domain.teamTodo.entity;


import com.example.notodo_backend.domain.teamTodo.dto.ChannelCreateResponseDto;
import com.example.notodo_backend.domain.teamTodo.dto.ChannelRequestDto;
import com.example.notodo_backend.domain.user.entity.UserEntity;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Long id;

    private String title;

    private String description;

    private String goal;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    public ChannelEntity(ChannelRequestDto channelRequestDto, String email) {
        this.title = channelRequestDto.getTitle();
        this.description = channelRequestDto.getDescription();
        this.goal = channelRequestDto.getGoal();
        this.categoryEntity = channelRequestDto.getCategory();
        this.userEntity = UserEntity.builder().email(email).build();
    }
}
