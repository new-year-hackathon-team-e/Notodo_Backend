package com.example.notodo_backend.global.jwt;

import com.example.notodo_backend.global.dto.NoTodoApiResponse;
import com.example.notodo_backend.global.message.DefaultMessage;
import com.example.notodo_backend.global.message.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();

        // 403에러
        ResponseMessage errorMessage = DefaultMessage.FORBIDDEN;
        NoTodoApiResponse notTodoApiResponse = NoTodoApiResponse.createResponse(null, errorMessage);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorMessage.getStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(notTodoApiResponse));
    }
}
