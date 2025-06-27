package com.example.babpulServer.service;

import com.example.babpulServer.DTO.DonationDTO;
import com.example.babpulServer.DTO.DonationMenuDTO;
import com.example.babpulServer.DTO.DonationReceiptDTO;
import com.example.babpulServer.Entity.*;
import com.example.babpulServer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final UserSessionRepository userSessionRepository;
    private final RestaurantReository restaurantReository;
    private final DonationMenuRepository donationMenuRepository;
    private final MenuRepository menuRepository;
    private final CardRepository cardRepository;
    private final CompanyMoneyRepository companyMoneyRepository;
    private final DonationPaymentRepository donationPaymentRepository;
    private final DonationRepository donationRepository;

    // 기부결제
    public void donationPay(DonationMenuDTO donationMenuDTO){
        List<DonationMenuDTO.MenuDTO> menuDTOS = donationMenuDTO.getMenus();
        String orderNumber = UUID.randomUUID().toString();
        LocalDate orderDate = LocalDate.now();
        Optional<UserSessionEntity> userSessionEntity = userSessionRepository.findBySessionKey(donationMenuDTO.getSessionKey());
        UserEntity userEntity = userSessionEntity.get().getUser();
        //Optional<MenuEntity> menuEntity = menuRepository.findByMenuKey(menuDTOS.get(0).getMenuKey());

        for(DonationMenuDTO.MenuDTO menuDTO : menuDTOS){
            Optional<MenuEntity> menuEntity =  menuRepository.findByMenuKey(menuDTO.getMenuKey());
            DonationPaymentEntity donationPaymentEntity = new DonationPaymentEntity();
            donationPaymentEntity.setOrderNumber(orderNumber);
            donationPaymentEntity.setOrderDate(orderDate);
            donationPaymentEntity.setMenuPrice(menuDTO.getDonationAmount());
            donationPaymentEntity.setMenu(menuEntity.get());
            donationPaymentEntity.setUser(userEntity);

            // 금액관리
            Optional<CardEntity> cardEntity = cardRepository.findByUser(userEntity);
            Optional<CompanyMoneyEntity> companyMoneyEntity = companyMoneyRepository.findByUser(userEntity);

            cardEntity.get().setMoney(cardEntity.get().getMoney() - donationMenuDTO.getCardMoney());
            companyMoneyEntity.get().setTotalMoney(companyMoneyEntity.get().getTotalMoney() - donationMenuDTO.getComponyMoney());

            donationPaymentRepository.save(donationPaymentEntity);
        }
    }

    public void donation(DonationDTO donationDTO){
        Optional<UserSessionEntity> userSessionEntity = userSessionRepository.findBySessionKey(donationDTO.getSessionKey());
        UserEntity userEntity = userSessionEntity.get().getUser();

        DonationEntity donationEntity = new DonationEntity();
        donationEntity.setMoney(donationDTO.getMoney());
        donationEntity.setUser(userEntity);

        donationRepository.save(donationEntity);
    }


    // 기프티콘 사용내역 보여주기


}
