package com.example.notodo_backend.global.jwt;

import com.example.notodo_backend.global.dto.NotTodoApiResponse;
import com.example.notodo_backend.global.message.DefaultMessage;
import com.example.notodo_backend.global.message.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        // 401에러
        ResponseMessage errorMessage = DefaultMessage.UNAUTHORIZED;
        NotTodoApiResponse moaApiResponse = NotTodoApiResponse.createResponse(null, errorMessage);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(moaApiResponse));
    }
}
