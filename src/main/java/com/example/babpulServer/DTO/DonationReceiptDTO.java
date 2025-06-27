package com.example.babpulServer.DTO;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationReceiptDTO {
    private LocalDate orderDate;
    private List<MenuDTO> menuList;
    private int totalAmount;


    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MenuDTO {
        private String menuName;
        private int quantity;
        private int donationAmount;
    }
}
