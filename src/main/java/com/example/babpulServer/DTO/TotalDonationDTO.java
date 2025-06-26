package com.example.babpulServer.DTO;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalDonationDTO {
    private String restaurantName; // 가게이름
    private String donationInfo; // 간단한 주문 정보
    private LocalDate donationDate; // 기부일시
    private String orderNumber; // 주문상세 조회를 위한 주문번호
    private String restaurantAdress; // 가게 보기 조회를 위한 주소정보
    private String SessionKey; // 세션키
}
