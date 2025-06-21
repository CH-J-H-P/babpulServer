package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.CardEntity;
import com.example.babpulServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity,Long> {
    List<CardEntity> findAllByUser(UserEntity userEntity);
}
