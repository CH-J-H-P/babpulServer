package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<DonationEntity,Long> {
}
