package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.UserEntity;
import com.example.babpulServer.Entity.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSessionEntity,Long> {
    Optional<UserSessionEntity> findByUser(UserEntity user);
    Optional<UserSessionEntity> findBySessionKey(String sessionKey);
}
