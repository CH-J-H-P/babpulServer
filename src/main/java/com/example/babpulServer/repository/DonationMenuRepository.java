package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.DonationMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationMenuRepository extends JpaRepository<DonationMenuEntity,Long> {
    List<DonationMenuEntity> findByOrderNumber(String orderNumber);
}
