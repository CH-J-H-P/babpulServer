package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.CompanyMoneyEntity;
import com.example.babpulServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyMoneyRepository extends JpaRepository<CompanyMoneyEntity,Long> {
    Optional<CompanyMoneyEntity> findByUser(UserEntity userEntity);
}
