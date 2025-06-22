package com.example.babpulServer.controller;

import com.example.babpulServer.DTO.CardDTO;
import com.example.babpulServer.Entity.CardEntity;
import com.example.babpulServer.Entity.UserSessionEntity;
import com.example.babpulServer.repository.CardRepository;
import com.example.babpulServer.repository.UserSessionRepository;
import com.example.babpulServer.service.CardService;
import com.example.babpulServer.service.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequiredArgsConstructor
@CrossOrigin
public class CardController {
    final CardService cardService;
    final UserSessionRepository userSessionRepository;


    @PostMapping("/card/save")
    public ResponseEntity<Void> cardInfoSave(@RequestBody CardDTO cardDTO){
        if(cardDTO == null){ // case: null로 요청시, do:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // case: 올바른 요청인 경우, do: 로그인 허용과 함께 세션 반환
        cardService.saveCardInfo(cardDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/card/mycard")
    public ResponseEntity<List<CardDTO>> cardInfoMyCard(@RequestParam String sessionKey){
        List<CardDTO> cardDTOList = cardService.getCardInfoBySessionKey(sessionKey);

        if(cardDTOList.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(cardDTOList);
    }

    
}
