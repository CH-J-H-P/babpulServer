package com.example.babpulServer.service;

import com.example.babpulServer.Entity.UserEntity;
import com.example.babpulServer.Entity.UserSessionEntity;
import com.example.babpulServer.repository.UserRepository;
import com.example.babpulServer.repository.UserSessionRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserSessionService {
    final UserSessionRepository userSessionRepository;

    // 세션 Entity 생성 메서드
    public UserSessionEntity createSession(HttpSession session, UserEntity userEntity){
        // 1. 세션키, Entity 생성
        String sessionKey = UUID.randomUUID().toString(); // 랜덤키 생성
        UserSessionEntity userSessionEntity = new UserSessionEntity();

        // 2. 세션 Entity 구성 후 DB에 저장
        userSessionEntity.setSessionKey(sessionKey);
        userSessionEntity.setCreatedAt(LocalDateTime.now());
        userSessionEntity.setExpiresAt(LocalDateTime.now().plusDays(100));
        userSessionEntity.setUser(userEntity);

        return userSessionEntity;
    }

    // 테이블에 세션 저장하는 로직, 디바이스 정보가 없는 관계로 임시적으로 단일세션 기준으로 구현
    public void sessionSave(UserSessionEntity userSessionEntity){
        // 삭제 필요시 사용, 지금은 단순 저장
//        Optional<UserSessionEntity> checkSessionEntity = userSessionRepository.findByUser(userSessionEntity.getUser());
//
//        if(checkSessionEntity.isPresent()){ // case: 기존에 이미 만든 세션이 있는 경우, do: 삭제 후 저장
//            userSessionRepository.delete(checkSessionEntity.get());
//        }
        userSessionRepository.save(userSessionEntity);
    }

    public void logout(String sessionKey) {
        Optional<UserSessionEntity> userSessionEntity = userSessionRepository.findBySessionKey(sessionKey);
        if(userSessionEntity.isPresent()){ // case: 해당 세션키의 정보가 있음, do: 해당 정보 삭제
            userSessionRepository.delete(userSessionEntity.get());
        }
    }
}
