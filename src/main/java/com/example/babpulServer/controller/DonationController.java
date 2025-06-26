package com.example.babpulServer.controller;


import com.example.babpulServer.DTO.DonationMenuDTO;
import com.example.babpulServer.Entity.DonationMenuEntity;
import com.example.babpulServer.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@ResponseBody
@RequiredArgsConstructor
@CrossOrigin
public class DonationController {
    final private DonationService donationService;

    @PostMapping("/donation/order")
    public ResponseEntity<Void> donationOrder(@RequestBody DonationMenuDTO donationMenuDTO){

        DonationMenuEntity donationMenuEntity = new DonationMenuEntity();
        String orderNumber = UUID.randomUUID().toString();
        LocalDate orderDate = LocalDate.now();

        // 음식의 종류별로 주문기록과 기프티콘을 만들어야함
        // 1. 기부내역 남기기
        // 2. 기프티콘 만들기
        donationService.saveDonationMenu(donationMenuDTO, orderNumber,orderDate);
        return ResponseEntity.ok().build();
    }
}
