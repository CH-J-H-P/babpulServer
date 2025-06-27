package com.example.babpulServer.service;

import com.example.babpulServer.DTO.MenuDTO;
import com.example.babpulServer.Entity.MenuEntity;
import com.example.babpulServer.Entity.RestaurantEntity;
import com.example.babpulServer.repository.CardRepository;
import com.example.babpulServer.repository.MenuRepository;
import com.example.babpulServer.repository.RestaurantReository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    final private MenuRepository menuRepository;
    final private RestaurantReository restaurantReository;

    public void saveMenuInfo(MenuDTO menuDTO) {
        MenuEntity menuEntity = menuDTO.toEntity();
        menuRepository.save(menuEntity);
    }



    // 각 가게 상세 정보(기부금, 재고 정보 추가 필요)
    public List<MenuDTO> getMenus(Long restaurantKey) {
        // 1. 가게 엔티티 조회
        Optional<RestaurantEntity> restaurantEntity = restaurantReository.findByRestaurantKey(restaurantKey);
        // 2. 해당 가게의 메뉴 리스트 조회
        List<MenuEntity> menus = menuRepository.findByRestaurant(restaurantEntity.get());

        // 3. 메뉴 리스트 DTO로 바꾸기
        List<MenuDTO> menuDTOs = new ArrayList<>();
        for (MenuEntity menu : menus) {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO = menuDTO.toDTO(menu);
            menuDTOs.add(menuDTO);
        }


        // DB에서 정보들을 가져오고 추가로 각 메뉴마다 기부금, 재고 정보를 파악하고 추가하는 로직 필요
        return menuDTOs;
    }
}
