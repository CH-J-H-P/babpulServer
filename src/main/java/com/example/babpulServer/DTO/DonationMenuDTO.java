package com.example.babpulServer.DTO;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationMenuDTO {
    private String sessionKey;         // 세션키
    private String address;      // 주소
    private List<MenuDTO> menus; // 메뉴 배열
    private int cardMoney;
    private int componyMoney;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MenuDTO {
        private String menuName;    // 메뉴명
        private Long menuKey;       // 메뉴 기본키
        private int quantity;       // 수량
        private int donationAmount; // 기부금액
    }
}