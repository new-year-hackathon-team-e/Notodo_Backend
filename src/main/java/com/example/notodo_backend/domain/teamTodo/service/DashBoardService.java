package com.example.notodo_backend.domain.teamTodo.service;

import com.example.notodo_backend.domain.teamTodo.entity.DashBoardEntity;
import com.example.notodo_backend.domain.teamTodo.repository.DashBoardRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;  // Import for @Transactional

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final DashBoardRepository dashBoardRepository;

    @Transactional
    public DashBoardEntity createDashboard(Long channelId,  Boolean checkDone, String email) {

        DashBoardEntity dashboardEntity = new DashBoardEntity(channelId,  checkDone, email);
        dashboardEntity = DashBoardRepository.save(dashboardEntity);
        return dashboardEntity;
    }


}

