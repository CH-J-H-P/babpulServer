package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.MenuEntity;
import com.example.babpulServer.Entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuEntity,Long> {
    List<MenuEntity> findByRestaurant(RestaurantEntity restaurant);
    Optional<MenuEntity> findByMenuKey(Long menuKey);
}
