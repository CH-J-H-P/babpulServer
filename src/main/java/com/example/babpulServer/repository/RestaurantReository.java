package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantReository extends JpaRepository<RestaurantEntity, Long> {
}
