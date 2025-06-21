package com.example.babpulServer.DTO;


import com.example.babpulServer.Entity.MenuEntity;
import com.example.babpulServer.Entity.RestaurantEntity;
import com.example.babpulServer.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationMenuDTO {
    private Long donationMenuKey; // 기본키

    private String orderNumber; // 주문번호(어떤 주문에 속하는 내용인지 구분하기 위함)

    private LocalDate dotationTime; // 기부일시

    private String totalPrice; // 메뉴별 총 금액

    private String MenuPrice; // 메뉴 단일가격

    private MenuEntity menu; // 메뉴 Entity

    private RestaurantEntity restaurant; // 레스토랑 Entity

    private UserEntity donor; // 유저(기부자) Entity
}
