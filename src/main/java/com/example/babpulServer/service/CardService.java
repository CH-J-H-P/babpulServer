package com.example.babpulServer.service;


import com.example.babpulServer.DTO.CardDTO;
import com.example.babpulServer.Entity.CardEntity;
import com.example.babpulServer.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    // 카드정보 저장 메서드
    public void saveCardInfo(CardDTO cardDTO){
        CardEntity cardEntity = cardDTO.toCardEntity();
        cardRepository.save(cardEntity);
    }
}
