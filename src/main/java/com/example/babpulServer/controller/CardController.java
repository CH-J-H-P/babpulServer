package com.example.babpulServer.controller;

import com.example.babpulServer.DTO.CardDTO;
import com.example.babpulServer.Entity.CardEntity;
import com.example.babpulServer.repository.CardRepository;
import com.example.babpulServer.service.CardService;
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
public class CardController {
    final CardService cardService;
    final CardRepository cardRepository;

    @PostMapping("/card/save")
    public ResponseEntity<Void> cardInfoSave(@RequestBody CardDTO cardDTO){
        if(cardDTO == null){ // case: null로 요청시, do:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // case: 올바른 요청인 경우, do: 로그인 허용과 함께 세션 반환
        cardService.saveCardInfo(cardDTO);
        return ResponseEntity.ok().build();

    }
}
