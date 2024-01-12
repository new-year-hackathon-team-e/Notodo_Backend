package com.example.notodo_backend.domain.teamTodo.repository;

import com.example.notodo_backend.domain.teamTodo.entity.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {
    @Query("SELECT c FROM ChannelEntity c WHERE c.categoryEntity.id = :categoryId")
    List<ChannelEntity> findAllByCategoryId(Long categoryId);
}
