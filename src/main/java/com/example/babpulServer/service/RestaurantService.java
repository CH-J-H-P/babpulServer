package com.example.babpulServer.service;

import com.example.babpulServer.DTO.RestaurantDTO;
import com.example.babpulServer.Entity.RestaurantEntity;
import com.example.babpulServer.repository.RestaurantReository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    final RestaurantReository restaurantReository;

    public void saveRestaurantInfo(RestaurantDTO restaurantDTO) {
        RestaurantEntity restaurantEntity = restaurantDTO.toEntity();
        restaurantReository.save(restaurantEntity);
    }
}
