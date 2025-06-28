package com.example.babpulServer.controller;

import com.example.babpulServer.DTO.MyPageDTO;
import com.example.babpulServer.DTO.UserDTO;
import com.example.babpulServer.DTO.UserSessionDTO;
import com.example.babpulServer.Entity.UserEntity;
import com.example.babpulServer.Entity.UserSessionEntity;
import com.example.babpulServer.repository.UserRepository;
import com.example.babpulServer.repository.UserSessionRepository;
import com.example.babpulServer.service.UserService;
import com.example.babpulServer.service.UserSessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final UserSessionService userSessionService;
    private final UserSessionRepository userSessionRepository;

    // 회원가입 로직
    @PostMapping("/user/signup")
    public ResponseEntity<Void> signup(@RequestBody UserDTO userDTO){
        if(userDTO == null){ // case: null로 요청시(변환 실패, 요청 본문 빔 등등), do: 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // case: 올바른 요청일 경우, do: 200 OK 상태 코드로 빈 응답을 반환
        System.out.println(userDTO.toString());
        userService.signup(userDTO);
        return ResponseEntity.ok().build();
    }

    // 로그인 로직
    @PostMapping("/user/login")
    public ResponseEntity<Void> login(@RequestBody UserDTO userDTO, HttpSession session,
                                      HttpServletResponse response){
        System.out.println(userDTO.toString());
        UserEntity userEntity = userService.login(userDTO);
        if(userEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        UserSessionEntity userSessionEntity = userSessionService.createSession(session, userEntity);

        // SameSite=None; Secure 적용된 쿠키 직접 헤더로 세팅
        String sessionCookie = "sessionKey=" + userSessionEntity.getSessionKey() +
                "; Path=/; HttpOnly; Secure; SameSite=None";
        String roleCookie = "userRole=" + userEntity.getUserRole().name() +
                "; Path=/; Secure; SameSite=None";

        response.addHeader("Set-Cookie", sessionCookie);
        response.addHeader("Set-Cookie", roleCookie);

        userSessionService.sessionSave(userSessionEntity);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/user/auto/login")
    public ResponseEntity<String> autoLogin(@RequestBody String sessionKey){
        Optional<UserSessionEntity> userSessionEntity = userSessionRepository.findBySessionKey(sessionKey);
        if(userSessionEntity.isEmpty()){
            // 401 Unauthorized 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 세션키가 유효할 때의 처리
        return ResponseEntity.ok().build();
    }

    // 로그아웃 로직
    @PostMapping("/user/logout")
    public ResponseEntity<Void> logout(@RequestBody UserSessionDTO userSessionDTO){
        userService.logout(userSessionDTO.getSessionKey());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/mypage")
    public ResponseEntity<MyPageDTO> myPage(@RequestBody String sessionKey){
        MyPageDTO myPageDTO = userService.myPage(sessionKey);
        return ResponseEntity.ok(myPageDTO);
    }
}
