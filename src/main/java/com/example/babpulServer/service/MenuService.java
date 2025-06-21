package com.example.babpulServer.service;

import com.example.babpulServer.DTO.MenuDTO;
import com.example.babpulServer.Entity.MenuEntity;
import com.example.babpulServer.repository.CardRepository;
import com.example.babpulServer.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {
    final private MenuRepository menuRepository;

    public void saveMenuInfo(MenuDTO menuDTO) {
        MenuEntity menuEntity = menuDTO.toEntity();
        menuRepository.save(menuEntity);
    }
}
