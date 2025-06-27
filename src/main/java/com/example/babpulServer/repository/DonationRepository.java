package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.DonationEntity;
import com.example.babpulServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<DonationEntity,Long> {
    List<DonationEntity> findByUser(UserEntity userEntity);
}
