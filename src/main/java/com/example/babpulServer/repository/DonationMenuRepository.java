package com.example.babpulServer.repository;

import com.example.babpulServer.Entity.DonationMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationMenuRepository extends JpaRepository<DonationMenuEntity,Long> {
}
