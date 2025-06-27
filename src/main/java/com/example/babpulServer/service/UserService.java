package com.example.babpulServer.service;


import com.example.babpulServer.DTO.MyPageDTO;
import com.example.babpulServer.DTO.UserDTO;
import com.example.babpulServer.Entity.*;
import com.example.babpulServer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSessionRepository userSessionRepository;
    private final UserSessionService userSessionService;
    private final RestaurantReository restaurantReository;
    private final CardRepository cardRepository;
    private final CompanyMoneyRepository companyMoneyRepository;

    // 회원가입 메서드
    public void signup(UserDTO userDTO){
        UserEntity userEntity= userDTO.toUserEntity();

        // 유저 역할 따라 인증 상태 변경
        if(userDTO.getUserRole() == UserEntity.role.DONOR){
            userEntity.setUserCheck(true);
        }else{
            userEntity.setUserCheck(false);
        }
        System.out.println(userEntity.toString());
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

    public MyPageDTO myPage(String sessionKey){
        Optional<UserSessionEntity> userSessionEntity = userSessionRepository.findBySessionKey(sessionKey);
        UserEntity userEntity = userSessionEntity.get().getUser();
        MyPageDTO myPageDTO = new MyPageDTO();
        myPageDTO.setUsername(userEntity.getName());
        myPageDTO.setNickname(userEntity.getNickname());

        // 사장일 경우
        if(userEntity.getUserRole() == UserEntity.role.BOSS){
            Optional<RestaurantEntity> restaurantEntity = restaurantReository.findByUser(userEntity);
            myPageDTO.setRestaurantKey(restaurantEntity.get().getRestaurantKey());
            myPageDTO.setRestaurantName(restaurantEntity.get().getRestaurantName());
            myPageDTO.setRestaurantAddress(restaurantEntity.get().getRestaurantAddress());
        }else if(userEntity.getUserRole() == UserEntity.role.BABPUL){
            Optional<CardEntity> cardEntity = cardRepository.findByUser(userEntity);
            Optional<CompanyMoneyEntity> companyMoneyEntity = companyMoneyRepository.findByUser(userEntity);
            myPageDTO.setKidMoney(cardEntity.get().getMoney());
            myPageDTO.setCompanyMoney(companyMoneyEntity.get().getTotalMoney());
        }
        return myPageDTO;
    }


}
