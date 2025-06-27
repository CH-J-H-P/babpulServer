package com.example.babpulServer.controller;


import com.example.babpulServer.DTO.DonationMenuDTO;
import com.example.babpulServer.DTO.DonationReceiptDTO;
import com.example.babpulServer.Entity.DonationMenuEntity;
import com.example.babpulServer.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@ResponseBody
@RequiredArgsConstructor
@CrossOrigin
public class DonationController {
    final private DonationService donationService;

    @PostMapping("/donation/save")
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

    // 기부내역 상세 확인
    @GetMapping("/donation/receipt/{orderNumber}")
    public ResponseEntity<DonationReceiptDTO> getDonationReceipt(@PathVariable String orderNumber){
        DonationReceiptDTO receipt = donationService.getDonationReceipt(orderNumber);
        return ResponseEntity.ok().body(receipt);
    }
}
    