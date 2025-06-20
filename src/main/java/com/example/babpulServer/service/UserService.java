package com.example.babpulServer.service;


import com.example.babpulServer.DTO.UserDTO;
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
public class UserService {
    private final UserRepository userRepository;
    private final UserSessionRepository userSessionRepository;
    private final UserSessionService userSessionService;

    // 회원가입 메서드
    public void signup(UserDTO userDTO){
        UserEntity userEntity= new UserEntity();
        userDTO.toUserEntity();
        userRepository.save(userEntity);
    }

    // 로그인 메서드
    public UserEntity login(UserDTO userDTO){
        Optional<UserEntity> userEntityOptional = // 입력한 Id를 기반으로 테이블 탐색
                userRepository.findById(userDTO.getId());

        if(userEntityOptional.isPresent()){ // case: 올바른 정보 입력시
            if(userEntityOptional.get().getPw().equals(userDTO.getPw())){ // do: 비밀번호 확인
                System.out.println("동일 유저 정보 존재");
                return userEntityOptional.get();
            }
        }
        // case: Id, Pw 탐지 실패시, do: null 출력
        System.out.println("Id, 패스워드 오류");
        return null;
    }

    // 로그아웃 메서드
    public void logout(String sessionKey){
        userSessionService.logout(sessionKey);
    }
}
