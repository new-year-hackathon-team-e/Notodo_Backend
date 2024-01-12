package com.example.notodo_backend.domain.mytodo.repository;

import com.example.notodo_backend.domain.mytodo.entity.NotodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NotodoRepository extends JpaRepository<NotodoEntity, Long> {
    Optional<NotodoEntity> findById(Long Id);
}
