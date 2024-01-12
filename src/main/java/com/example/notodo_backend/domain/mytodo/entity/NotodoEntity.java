package com.example.notodo_backend.domain.mytodo.entity;

import com.example.notodo_backend.domain.mytodo.dto.CreateNotodoRequestDto;
import com.example.notodo_backend.domain.mytodo.dto.NotodoRequestDto;
import com.example.notodo_backend.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@Getter
@Entity
public class NotodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Boolean checkBox;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public NotodoEntity(CreateNotodoRequestDto createNotodoRequestDto, UserEntity userEntity) {
        this.content = createNotodoRequestDto.getContent();
        this.checkBox = createNotodoRequestDto.getCheckBox();
        this.date = new Date();  // 현재 날짜 및 시간으로 초기화
        this.userEntity = userEntity;
    }

    public NotodoEntity(NotodoRequestDto todoRequestDto) {
        this.content = todoRequestDto.getContent();
        this.checkBox = todoRequestDto.getCheckBox();
    }


    public NotodoEntity(String content, Boolean checkBox, UserEntity userEntity) {
        this.content = content;
        this.checkBox = checkBox;
        this.userEntity = userEntity;
    }
}
