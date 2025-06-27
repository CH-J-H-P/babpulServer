package com.example.babpulServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable); // CSRF 보호 비활성화
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/user/signup", "/user/login", "/user/auto/login"
                                        , "/card/save", "/restaurant/list", "/donation/save").permitAll()
                                .anyRequest().permitAll()
                        //authenticated()로 하면 설정한 것 이외의 요청은 인증 필요
                );
        return http.build();
    }
}