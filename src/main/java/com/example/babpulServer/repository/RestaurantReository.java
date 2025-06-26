package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantReository extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByRestaurantAddress(String restaurantAddress);
    Optional<RestaurantEntity> findByRestaurantKey(Long restaurantKey);
}
