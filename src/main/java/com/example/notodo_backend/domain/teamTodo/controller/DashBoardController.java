package com.example.notodo_backend.domain.teamTodo.controller;


import com.example.notodo_backend.domain.teamTodo.entity.ChannelEntity;
import com.example.notodo_backend.domain.teamTodo.entity.DashBoardEntity;
import com.example.notodo_backend.domain.teamTodo.service.DashBoardService;
import com.example.notodo_backend.global.dto.NoTodoApiResponse;
import com.example.notodo_backend.global.jwt.oauth.UserInfo;
import com.example.notodo_backend.global.message.DashBoardMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class DashBoardController {
    private final DashBoardService dashBoardService;

    @PostMapping("")
    NoTodoApiResponse<DashBoardEntity> createDashboard(@RequestParam Long channelId, boolean checkDone, Long duration, @AuthenticationPrincipal UserInfo user) {

        DashBoardEntity dashBoardEntity = dashBoardService.createDashboard(channelId,checkDone, user.getEmail());
        return NoTodoApiResponse.createResponse( dashBoardEntity, DashBoardMessage.DASH_BOARD_CREATE_SUCCESS);
    }

    /*
    @PutMapping("{dashBoardId}")
    NoTodoApiResponse<DashBoardEntity> checkDashBoard(@PathVariable Long dashBoardId, @AuthenticationPrincipal UserInfo user){
        DashBoardEntity dashBoardEntity = dashBoardService.updateDashboard(dashBoardId, user.getEmail());
        return NoTodoApiResponse.createResponse(dashBoardEntity, DashBoardMessage.DASH_BOARD_GET_SUCCESS);
    }
    */
}






