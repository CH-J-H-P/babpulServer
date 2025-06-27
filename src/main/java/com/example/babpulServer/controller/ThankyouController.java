package com.example.babpulServer.controller;

import com.example.babpulServer.DTO.ThankYouDTO;
import com.example.babpulServer.repository.ThankYouRepository;
import com.example.babpulServer.service.ThankYouService;
import lombok.RequiredArgsConstructor;
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
public class ThankyouController {
    private final ThankYouRepository thankYouRepository;
    private final ThankYouService thankYouService;

    @PostMapping("/thank/save")
    public ResponseEntity<Void> thankYou(@RequestBody ThankYouDTO thankYouDTO){
        thankYouService.thankYou(thankYouDTO);
        return ResponseEntity.ok().build();
    }
}
