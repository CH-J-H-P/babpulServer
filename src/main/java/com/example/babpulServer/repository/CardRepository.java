package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity,Long> {
}
