package com.example.babpulServer.controller;


import com.example.babpulServer.DTO.DonationDTO;
import com.example.babpulServer.DTO.DonationMenuDTO;
import com.example.babpulServer.DTO.DonationPaymentDTO;
import com.example.babpulServer.DTO.DonationReceiptDTO;
import com.example.babpulServer.Entity.DonationPaymentEntity;
import com.example.babpulServer.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
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

    @PostMapping("/donation/my")
    public ResponseEntity<List<DonationDTO>> myDonation(@RequestBody String sessionKey){
        List<DonationDTO> donationDTOS= donationService.myDonation(sessionKey);
        return ResponseEntity.ok().body(donationDTOS);
    }

    // 기부내역 상세 확인
    @GetMapping("/donation/receipt/{orderNumber}")
    public ResponseEntity<DonationReceiptDTO> donationReceipt(@PathVariable  String orderNumber){
        DonationReceiptDTO donationReceiptDTO = donationService.donationReceipt(orderNumber);
        return ResponseEntity.ok().body(donationReceiptDTO);
    }

    @GetMapping("/donation/all/receipts")
    public ResponseEntity<List<DonationReceiptDTO>> getAllReceipts(@RequestParam String sessionKey) {
        List<DonationReceiptDTO> receipts = donationService.allDonation(sessionKey);
        return ResponseEntity.ok(receipts);
    }


    @GetMapping("/donation/used")
    public ResponseEntity<List<DonationPaymentDTO>> getUsedPaymentsBySessionKey(@RequestParam String sessionKey) {
        List<DonationPaymentDTO> dtos = donationService.getUsedPaymentsBySessionKey(sessionKey);
        return ResponseEntity.ok(dtos);
    }

    // sessionKey로 유저의 미사용 결제내역 조회 (use=false)
    @GetMapping("/donation/unused")
    public ResponseEntity<List<DonationPaymentDTO>> getUnusedPaymentsBySessionKey(@RequestParam String sessionKey) {
        List<DonationPaymentDTO> dtos = donationService.getUnusedPaymentsBySessionKey(sessionKey);
        return ResponseEntity.ok(dtos);
    }
}
