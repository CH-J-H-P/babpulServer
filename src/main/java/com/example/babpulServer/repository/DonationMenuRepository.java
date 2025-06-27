package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.DonationPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationMenuRepository extends JpaRepository<DonationPaymentEntity,Long> {
    List<DonationPaymentEntity> findByOrderNumber(String orderNumber);
}
