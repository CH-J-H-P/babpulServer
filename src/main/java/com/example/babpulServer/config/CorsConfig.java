package com.example.babpulServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration// 스프링의 설정 클래스임
public class CorsConfig {
    @Bean // 메서드의 반환 객체를 Spring IoC 컨테이너에 bean 으로 등록
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS 허용
                        .allowedOrigins("http://10.96.66.64:5173") // 클라이언트 도메인 허용
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                        .allowCredentials(true); // 쿠키 및 인증 정보를 포함한 요청 허용
            }
        };
    }
}