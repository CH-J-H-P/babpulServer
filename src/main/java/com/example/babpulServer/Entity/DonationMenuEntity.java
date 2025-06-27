package com.example.babpulServer.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donation_menu_table")
public class DonationMenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationMenuKey; // 기본키

    @Column(nullable = false)
    private String orderNumber; // 주문번호(어떤 주문에 속하는 내용인지 구분하기 위함)

    @Column(nullable = false)
    private LocalDate orderDate; // 기부일시

    @Column(nullable = false)
    private int totalPrice; // 메뉴별 총 금액(기부 금액 기준)

    @Column(nullable = false)
    private int menuPrice; // 메뉴 단일가격(기부 금액 기준)


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RestaurantKey", nullable = false)
    private RestaurantEntity restaurant; // 레스토랑 Entity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserKey", nullable = false)
    private UserEntity donor; // 유저(기부자) Entity
}
