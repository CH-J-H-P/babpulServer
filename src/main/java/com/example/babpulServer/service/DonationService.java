package com.example.babpulServer.service;

import com.example.babpulServer.DTO.*;
import com.example.babpulServer.Entity.*;
import com.example.babpulServer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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


    public List<DonationDTO> myDonation(String sessionKey){
        Optional<UserSessionEntity> userSessionEntity = userSessionRepository.findBySessionKey(sessionKey);
        UserEntity userEntity = userSessionEntity.get().getUser();
        List<DonationEntity> donationEntity = donationRepository.findByUser(userEntity);

        List<DonationDTO> donationDTOs = new ArrayList<>();
        for(DonationEntity donationEntity1 : donationEntity){
            DonationDTO donationDTO = new DonationDTO();
            donationDTO.setMoney(donationEntity1.getMoney());

            donationDTOs.add(donationDTO);
        }

        return donationDTOs;
    }

    public DonationReceiptDTO donationReceipt(String orderNumber){
        List<DonationPaymentEntity> donationPaymentEntities = donationPaymentRepository.findByOrderNumber(orderNumber);
        DonationReceiptDTO donationReceipt = new DonationReceiptDTO();
        donationReceipt.setOrderDate(donationPaymentEntities.get(0).getOrderDate());
        List<DonationReceiptDTO.MenuDTO> menuDTOS = new ArrayList<>();
        int totalAmount = 0;
        for(DonationPaymentEntity donationPaymentEntity : donationPaymentEntities){
            DonationReceiptDTO.MenuDTO menuDTO = new DonationReceiptDTO.MenuDTO();
            menuDTO.setMenuName(donationPaymentEntity.getMenu().getMenuName());
            menuDTO.setQuantity(donationPaymentEntity.getMenuPrice() / donationPaymentEntity.getMenu().getMenuPrice());
            menuDTO.setDonationAmount(donationPaymentEntity.getMenuPrice());
            menuDTOS.add(menuDTO);
            totalAmount += donationPaymentEntity.getMenu().getMenuPrice();
        }
        donationReceipt.setMenuList(menuDTOS);
        donationReceipt.setTotalAmount(totalAmount);

        return donationReceipt;
    }


    public List<DonationReceiptDTO> allDonation(String sessionKey) {
        // 1. 세션키로 유저 조회
        UserEntity userEntity = userSessionRepository.findBySessionKey(sessionKey)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 세션입니다."))
                .getUser();

        // 2. 해당 유저의 모든 결제 내역 조회
        List<DonationPaymentEntity> paymentEntities = donationPaymentRepository.findByUser(userEntity);

        // 3. 주문번호별로 그룹핑
        Map<String, List<DonationPaymentEntity>> groupedByOrderNumber = paymentEntities.stream()
                .collect(Collectors.groupingBy(DonationPaymentEntity::getOrderNumber));

        List<DonationReceiptDTO> result = new ArrayList<>();

        // 4. 주문번호별로 영수증 생성
        for (List<DonationPaymentEntity> orderPayments : groupedByOrderNumber.values()) {
            DonationReceiptDTO receipt = buildReceiptFromPaymentEntities(orderPayments);
            result.add(receipt);
        }

        // (선택) 주문일자 내림차순 정렬
        result.sort(Comparator.comparing(DonationReceiptDTO::getOrderDate).reversed());

        return result;
    }

    // 기존 donationReceipt 로직을 재사용할 수 있게 분리
    private DonationReceiptDTO buildReceiptFromPaymentEntities(List<DonationPaymentEntity> donationPaymentEntities) {
        DonationReceiptDTO donationReceipt = new DonationReceiptDTO();
        donationReceipt.setOrderDate(donationPaymentEntities.get(0).getOrderDate());
        List<DonationReceiptDTO.MenuDTO> menuDTOS = new ArrayList<>();
        int totalAmount = 0;
        for (DonationPaymentEntity donationPaymentEntity : donationPaymentEntities) {
            DonationReceiptDTO.MenuDTO menuDTO = new DonationReceiptDTO.MenuDTO();
            menuDTO.setMenuName(donationPaymentEntity.getMenu().getMenuName());
            menuDTO.setQuantity(donationPaymentEntity.getMenuPrice() / donationPaymentEntity.getMenu().getMenuPrice());
            menuDTO.setDonationAmount(donationPaymentEntity.getMenuPrice());
            menuDTOS.add(menuDTO);
            totalAmount += donationPaymentEntity.getMenuPrice(); // 실제 결제 금액 합산
        }
        donationReceipt.setMenuList(menuDTOS);
        donationReceipt.setTotalAmount(totalAmount);

        return donationReceipt;
    }
}

