package com.example.babpulServer.service;

import com.example.babpulServer.DTO.DonationGiftDTO;
import com.example.babpulServer.DTO.DonationMenuDTO;
import com.example.babpulServer.DTO.DonationReceiptDTO;
import com.example.babpulServer.Entity.DonationGiftEntity;
import com.example.babpulServer.Entity.DonationMenuEntity;
import com.example.babpulServer.Entity.MenuEntity;
import com.example.babpulServer.Entity.UserEntity;
import com.example.babpulServer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final UserSessionRepository userSessionRepository;
    private final RestaurantReository restaurantReository;
    private final DonationMenuRepository donationMenuRepository;
    private final DonationGiftRepository donationGiftRepository;
    private final MenuRepository menuRepository;

    // 기부내역 남기기 + 기프티콘 생성
    public void saveDonationMenu(DonationMenuDTO donaDTO, String orderNumber, LocalDate orderDate) {
        List<DonationMenuDTO.MenuDTO> menuDTOList = donaDTO.getMenus();
        UserEntity donor = userSessionRepository.findBySessionKey(donaDTO.getSessionKey()).get().getUser();

        for (DonationMenuDTO.MenuDTO menuDTO : menuDTOList) {
            // 1. 메뉴별 기부내역 엔티티 생성 및 값 세팅
            DonationMenuEntity donationMenuEntity = new DonationMenuEntity();
            donationMenuEntity.setOrderNumber(orderNumber);
            donationMenuEntity.setOrderDate(orderDate);
            donationMenuEntity.setRestaurant(restaurantReository.findByRestaurantAddress(donaDTO.getAddress()).get());
            donationMenuEntity.setDonor(donor);
            donationMenuEntity.setTotalPrice(menuDTO.getDonationAmount());
            donationMenuEntity.setMenuPrice(menuDTO.getDonationAmount() / menuDTO.getQuantity());

            // 2. 메뉴 엔티티 저장
            donationMenuRepository.save(donationMenuEntity);

            // 3. 기프티콘 엔티티를 메뉴 수량만큼 생성
            for (int i = 0; i < menuDTO.getQuantity(); i++) {
                DonationGiftEntity donationGiftEntity = new DonationGiftEntity();
                donationGiftEntity.setDonor(donor);
                donationGiftEntity.setMenuName(menuDTO.getMenuName());
                donationGiftEntity.setMenu(menuRepository.findByMenuKey(menuDTO.getMenuKey()).get());
                donationGiftEntity.setDonationMenu(donationMenuEntity); // 기부내역 외래키
                donationGiftRepository.save(donationGiftEntity);
            }
        }
    }

    // 전체 기부내역 보여주기


    // 기부내역 상세 보여주기
    public DonationReceiptDTO getDonationReceipt(String orderNumber) {
        // 1. 데이터 조회
        List<DonationMenuEntity> dmList = donationMenuRepository.findByOrderNumber(orderNumber);

        // 반환할 데이터 불러오기
        DonationReceiptDTO donationReceipt = new DonationReceiptDTO();
        List<DonationReceiptDTO.MenuDTO> menuDTOList = new ArrayList<>();

        // 공통 데이터 뽑기(주문일자)
        donationReceipt.setOrderDate(dmList.get(0).getOrderDate());

        // 총기부금액, 기부 리스트 가져오기
        int totalAmount = 0;
        for (DonationMenuEntity dmEntity : dmList) {
            DonationReceiptDTO.MenuDTO menuDTO = DonationReceiptDTO.MenuDTO.builder()
                    .menuName(dmEntity.getMenuName())
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

    // 기프티콘을 사용한 경우
}
