package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity,Long> {
}
