package com.example.babpulServer.service;


import com.example.babpulServer.DTO.CardDTO;
import com.example.babpulServer.Entity.CardEntity;
import com.example.babpulServer.Entity.CompanyMoneyEntity;
import com.example.babpulServer.Entity.UserEntity;
import com.example.babpulServer.Entity.UserSessionEntity;
import com.example.babpulServer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CardService {
    final private CardRepository cardRepository;
    final private UserSessionRepository userSessionRepository;
    private final CompanyMoneyRepository companyMoneyRepository;

    // 카드정보 저장 메서드
    public void saveCardInfo(CardDTO cardDTO){
        CardEntity cardEntity = cardDTO.toEntity();

        // entity 저장
        UserEntity userEntity = userSessionRepository.findBySessionKey(cardDTO.getSessionKey()).get().getUser();
        if(cardEntity.getCardType().equals(CardEntity.CardType.CHILD_MEAL_CARD)){
            cardEntity.setMoney(9500);
            CompanyMoneyEntity companyMoneyEntity = new CompanyMoneyEntity();
            companyMoneyEntity.setTotalMoney(30000);
            companyMoneyEntity.setUser(userEntity);
            companyMoneyRepository.save(companyMoneyEntity);
        }
        cardEntity.setUser(userEntity);
        cardRepository.save(cardEntity);
    }

    public List<CardDTO> getCardInfoBySessionKey(String sessionKey){
        // 1. 세션 정보를 활용하여 세션 Entity에 정보가 있는지 확인
        Optional<UserSessionEntity> userSessionEntity = userSessionRepository.findBySessionKey(sessionKey);
        if(userSessionEntity.isPresent()){
            // 2. 세션 테이블에 정보가 있다면 유저 정보를 활용하여 카드 정보를 전부 가져오기
            List<CardEntity> cardEntityList = cardRepository.findAllByUser(userSessionEntity.get().getUser());

            // 3. 얻은 Entity들을 DTO로 변환하여 주기
            return cardEntityList.stream().map(CardDTO::toDTOForSerch)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
