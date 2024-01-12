package com.example.notodo_backend.domain.mytodo.controller;

import com.example.notodo_backend.domain.mytodo.dto.CreateNotodoResponseDto;
import com.example.notodo_backend.domain.mytodo.dto.NotodoRequestDto;
import com.example.notodo_backend.domain.mytodo.service.NotodoService;
import com.example.notodo_backend.global.dto.NoTodoApiResponse;
import com.example.notodo_backend.global.jwt.oauth.UserInfo;
import com.example.notodo_backend.global.message.NoTodoMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mytodo")
@Slf4j
public class NotodoController {
   private final NotodoService notodoService;

   @Operation(summary = "개인 투두 생성하기")
   @PostMapping("")
   public NoTodoApiResponse<CreateNotodoResponseDto> createNotodo(@RequestParam String content, Boolean checkBox , @AuthenticationPrincipal UserInfo user) {
      CreateNotodoResponseDto createNotodoResponseDto = notodoService.createTodo(content,checkBox, user.getEmail());
      log.info("createNotodoResponseDto = {}", createNotodoResponseDto);
      return NoTodoApiResponse.createResponse(createNotodoResponseDto, NoTodoMessage.TODO_CREATE_SUCCESS);
   }


   @Operation(summary = "개인 투두 수정하기")
   @PatchMapping("")
   public NoTodoApiResponse<CreateNotodoResponseDto> updateTodo( @RequestBody NotodoRequestDto todoRequestDto, @AuthenticationPrincipal UserInfo user) {
      CreateNotodoResponseDto createNotodoResponseDto = notodoService.updateTodo(todoRequestDto, user.getEmail());
      return NoTodoApiResponse.createResponse(createNotodoResponseDto, NoTodoMessage.TODO_UPDATE_SUCCESS);
   }


}





