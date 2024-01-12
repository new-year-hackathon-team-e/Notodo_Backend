package com.example.notodo_backend.domain.teamTodo.repository;

import com.example.notodo_backend.domain.teamTodo.entity.DashBoardEntity;
import org.springframework.stereotype.Repository;


@Repository
public class DashBoardRepository {

    public static DashBoardEntity save(DashBoardEntity dashboardEntity) {
        return dashboardEntity;
    }

}
