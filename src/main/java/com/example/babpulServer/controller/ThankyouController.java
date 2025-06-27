package com.example.babpulServer.controller;

import com.example.babpulServer.DTO.ThankYouDTO;
import com.example.babpulServer.repository.ThankYouRepository;
import com.example.babpulServer.service.ThankYouService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/thank/all")
    public ResponseEntity<List<ThankYouDTO>> thankYou(@RequestBody String sessionKey){
        List<ThankYouDTO> thankYouDTOList = thankYouService.allThankYou();
        return ResponseEntity.ok().body(thankYouDTOList);
    }
}
