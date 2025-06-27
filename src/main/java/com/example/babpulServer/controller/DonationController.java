package com.example.babpulServer.controller;


import com.example.babpulServer.DTO.DonationDTO;
import com.example.babpulServer.DTO.DonationMenuDTO;
import com.example.babpulServer.DTO.DonationReceiptDTO;
import com.example.babpulServer.Entity.DonationPaymentEntity;
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

    @PostMapping("/donation/payment")
    public ResponseEntity<Void> donationOrder(@RequestBody DonationMenuDTO donationMenuDTO){

        donationService.donationPay(donationMenuDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/donation/save")
    public ResponseEntity<Void> saveDonation(@RequestBody DonationDTO donationDTO){
        donationService.donation( donationDTO);
        return ResponseEntity.ok().build();
    }
    // 기부내역 상세 확인

}
