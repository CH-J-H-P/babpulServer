package com.example.babpulServer.service;

import com.example.babpulServer.DTO.DonationMenuDTO;
import com.example.babpulServer.Entity.DonationGiftEntity;
import com.example.babpulServer.Entity.DonationMenuEntity;
import com.example.babpulServer.Entity.UserEntity;
import com.example.babpulServer.repository.DonationGiftRepository;
import com.example.babpulServer.repository.DonationMenuRepository;
import com.example.babpulServer.repository.RestaurantReository;
import com.example.babpulServer.repository.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final UserSessionRepository userSessionRepository;
    private final RestaurantReository restaurantReository;
    private final DonationMenuRepository donationMenuRepository;
    private final DonationGiftRepository donationGiftRepository;

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
                donationGiftEntity.setDonationMenu(donationMenuEntity); // 외래키 연관관계 설정
                donationGiftRepository.save(donationGiftEntity);
            }
        }
    }

    // 전체 기부내역 보여주기


    // 영수증 주기


    // 기프티콘 DTO 주기

    // 기프티콘을 사용한 경우
}
