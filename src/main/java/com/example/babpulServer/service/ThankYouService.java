package com.example.babpulServer.service;

import com.example.babpulServer.DTO.ThankYouDTO;
import com.example.babpulServer.Entity.ThankYouEntity;
import com.example.babpulServer.Entity.UserSessionEntity;
import com.example.babpulServer.repository.ThankYouRepository;
import com.example.babpulServer.repository.UserRepository;
import com.example.babpulServer.repository.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThankYouService {
    private final ThankYouRepository thankYouRepository;
    private final UserSessionRepository userSessionRepository;

    public void thankYou(ThankYouDTO thankYouDTO) {
        Optional<UserSessionEntity> userSession = userSessionRepository.findBySessionKey(thankYouDTO.getSessionKey());
        ThankYouEntity thankYouEntity = ThankYouEntity.builder()
                .subTitle(thankYouDTO.getSubTitle())
                .text(thankYouDTO.getText())
                .user(userSession.get().getUser())
                .build();

        thankYouRepository.save(thankYouEntity);
    }

    public List<ThankYouDTO> allThankYou() {
        List<ThankYouEntity> thankYouEntityList = thankYouRepository.findAll();
        List<ThankYouDTO> thankYouDTOList = new ArrayList<>();
        for (ThankYouEntity thankYouEntity : thankYouEntityList) {
            ThankYouDTO thankYouDTO = new ThankYouDTO();
            thankYouDTO.setSubTitle(thankYouEntity.getSubTitle());
            thankYouDTO.setText(thankYouEntity.getText());
            thankYouDTOList.add(thankYouDTO);
        }
        return thankYouDTOList;
    }
}


