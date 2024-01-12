package com.example.notodo_backend.global.config;

import com.example.notodo_backend.global.jwt.JwtAccessDeniedHandler;
import com.example.notodo_backend.global.jwt.JwtAuthenticationEntryPoint;
import com.example.notodo_backend.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return (web -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/swagger-ui/**"),
                new AntPathRequestMatcher("/h2-console/**"),
                new AntPathRequestMatcher("/favicon.ico"),
                new AntPathRequestMatcher("/v1/api-docs")));
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

        http.cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            // exception handling 할 때 우리가 만든 클래스를 추가
            .exceptionHandling(exceptionHandling ->
                    exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                            .accessDeniedHandler(jwtAccessDeniedHandler))
            //세션 설정을 Stateless 로 설정
            .sessionManagement(sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            //권한 설정
            .authorizeHttpRequests(httpRequest ->
                    httpRequest
                            .requestMatchers(new MvcRequestMatcher(introspector, "/**/auth/**")).authenticated()
                            .requestMatchers(new MvcRequestMatcher(introspector, "/**/user/**")).authenticated()
                            .requestMatchers(new MvcRequestMatcher(introspector, "/**/gifticon/**")).authenticated()
                            .anyRequest().permitAll())
            // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
            .apply(new JwtFilterConfig(tokenProvider));
        return http.build();
    }
}
