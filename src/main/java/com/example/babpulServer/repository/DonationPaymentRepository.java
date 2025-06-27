package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.DonationPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationPaymentRepository extends JpaRepository<DonationPaymentEntity,Long> {
}
