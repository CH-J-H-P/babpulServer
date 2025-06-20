package com.example.babpulServer.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final UserSessionService userSessionService;

    // 회원가입 로직
    @PostMapping("/user/singup")
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
    @PostMapping("user/login")
    public ResponseEntity<Void> login(@RequestBody UserDTO userDTO, HttpSession session,
                                      HttpServletResponse response){
        System.out.println(userDTO.toString());
        UserEntity userEntity = userService.login(userDTO);
        if(userEntity == null){ // case: null 요청이 들어온경우, do: 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // case: 올바른 요청인 경우, do: 로그인 허용과 함께 세션 반환
        UserSessionEntity userSessionEntity = userSessionService.createSession(session, userEntity);

        Cookie sessionCookie = new Cookie("session_key", userSessionEntity.getSessionKey());
        sessionCookie.setHttpOnly(true); // 자바스크립트에서 접근 불가하도록 설정
        sessionCookie.setPath("/"); // 사이트의 모든 경로에서 쿠키가 유효하도록 설정
        response.addCookie(sessionCookie); // 응답에 쿠키를 추가
        
        // 세션정보를 DB에 저장하고 ok 코드 응답
        userSessionService.sessionSave(userSessionEntity);
        return ResponseEntity.ok().build();
    }

    // 로그아웃 로직
    @PostMapping("/user/logout")
    public ResponseEntity<Void> logout(@RequestBody UserSessionDTO userSessionDTO){
        userService.logout(userSessionDTO.getSessionKey());
        return ResponseEntity.ok().build();
    }
}
