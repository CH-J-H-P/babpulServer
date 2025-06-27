package com.example.babpulServer.service;

import com.example.babpulServer.DTO.RestaurantDTO;
import com.example.babpulServer.Entity.RestaurantEntity;
import com.example.babpulServer.repository.RestaurantReository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantReository restaurantReository;


    public void saveRestaurantInfo(RestaurantDTO restaurantDTO) {
        RestaurantEntity restaurantEntity = restaurantDTO.toEntity();
        restaurantReository.save(restaurantEntity);
    }

    // 전체 가게 리스트 불러오기

    public List<RestaurantDTO> getAllRestaurantInfo() {
        // 1. 모든 가게의 정보 받아오기
        List<RestaurantEntity> allRestaurants = restaurantReository.findAll();

        // 2. 엔티티 DTO로 변환하기
        List<RestaurantDTO> allRestaurtantDTOs = new ArrayList<>();
        for (RestaurantEntity restaurantEntity : allRestaurants) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO = restaurantDTO.fromEntity(restaurantEntity);
            allRestaurtantDTOs.add(restaurantDTO);
        }

        return allRestaurtantDTOs;
    }
}
