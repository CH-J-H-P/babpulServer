package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.DonationPaymentEntity;
import com.example.babpulServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DonationPaymentRepository extends JpaRepository<DonationPaymentEntity,Long> {
    List<DonationPaymentEntity> findByOrderNumber(String orderNumber);
    List<DonationPaymentEntity> findByUser(UserEntity userEntity);
}
