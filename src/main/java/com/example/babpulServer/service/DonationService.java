package com.example.babpulServer.service;

import com.example.babpulServer.DTO.DonationMenuDTO;
import com.example.babpulServer.DTO.DonationReceiptDTO;
import com.example.babpulServer.Entity.CardEntity;
import com.example.babpulServer.Entity.CompanyMoneyEntity;
import com.example.babpulServer.Entity.DonationPaymentEntity;
import com.example.babpulServer.Entity.UserEntity;
import com.example.babpulServer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final UserSessionRepository userSessionRepository;
    private final RestaurantReository restaurantReository;
    private final DonationMenuRepository donationMenuRepository;
    private final MenuRepository menuRepository;
    private final CardRepository cardRepository;
    private final CompanyMoneyRepository companyMoneyRepository;

    // 기부내역 남기기 + 기프티콘 생성
    public void saveDonationMenu(DonationMenuDTO donaDTO, String orderNumber, LocalDate orderDate) {
        List<DonationMenuDTO.MenuDTO> menuDTOList = donaDTO.getMenus();
        UserEntity donor = userSessionRepository.findBySessionKey(donaDTO.getSessionKey()).get().getUser();

        for (DonationMenuDTO.MenuDTO menuDTO : menuDTOList) {
            // 1. 메뉴별 기부내역 엔티티 생성 및 값 세팅
            DonationPaymentEntity donationPaymentEntity = new DonationPaymentEntity();
            donationPaymentEntity.setOrderNumber(orderNumber);
            donationPaymentEntity.setOrderDate(orderDate);
            donationPaymentEntity.setRestaurant(restaurantReository.findByRestaurantAddress(donaDTO.getAddress()).get());
            donationPaymentEntity.setMenu(menuRepository.findByMenuKey(menuDTO.getMenuKey()).get());
            donationPaymentEntity.setMenuName(menuDTO.getMenuName());
            donationPaymentEntity.setDonor(donor);
            donationPaymentEntity.setTotalPrice(menuDTO.getDonationAmount());
            donationPaymentEntity.setMenuPrice(menuDTO.getDonationAmount() / menuDTO.getQuantity());

            // 아이들 카드 털기
            UserEntity userEntity = userSessionRepository.findBySessionKey(donaDTO.getSessionKey()).get().getUser();
            Optional<CardEntity> cardEntity = cardRepository.findByUser(userEntity);
            Optional<CompanyMoneyEntity> companyMoneyEntity = companyMoneyRepository.findByUser(userEntity);
            if(menuDTO.getDonationAmount() >= cardEntity.get().getMoney()) {
                companyMoneyEntity.get().setTotalMoney(companyMoneyEntity.get().getTotalMoney() - (menuDTO.getDonationAmount() - cardEntity.get().getMoney()));
                cardEntity.get().setMoney(0);
            }else{
                cardEntity.get().setMoney(cardEntity.get().getMoney()- menuDTO.getDonationAmount());
            }

            // 2. 메뉴 엔티티 저장
            donationMenuRepository.save(donationPaymentEntity);
        }
    }

    // 전체 기부내역 보여주기


    // 기부내역 상세 보여주기
    public DonationReceiptDTO getDonationReceipt(String orderNumber) {
        // 1. 데이터 조회
        List<DonationPaymentEntity> dmList = donationMenuRepository.findByOrderNumber(orderNumber);

        // 반환할 데이터 불러오기
        DonationReceiptDTO donationReceipt = new DonationReceiptDTO();
        List<DonationReceiptDTO.MenuDTO> menuDTOList = new ArrayList<>();

        // 공통 데이터 뽑기(주문일자)
        donationReceipt.setOrderDate(dmList.get(0).getOrderDate());

        // 총기부금액, 기부 리스트 가져오기
        int totalAmount = 0;
        for (DonationPaymentEntity dmEntity : dmList) {
            DonationReceiptDTO.MenuDTO menuDTO = DonationReceiptDTO.MenuDTO.builder()
                    .menuName(dmEntity.getMenu().getMenuName())
                    .quantity(dmEntity.getTotalPrice() / dmEntity.getMenuPrice())
                    .donationAmount(dmEntity.getTotalPrice())
                    .build();

            menuDTOList.add(menuDTO);
            totalAmount += dmEntity.getTotalPrice();
        }

        donationReceipt.setMenuList(menuDTOList);
        donationReceipt.setTotalAmount(totalAmount);

        return donationReceipt;
    }


    // 기프티콘 사용내역 보여주기

}
