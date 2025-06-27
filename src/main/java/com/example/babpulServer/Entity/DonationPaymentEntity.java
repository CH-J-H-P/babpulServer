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
@Table(name = "donation_payment_table")
public class DonationPaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationMenuKey; // 기본키

    @Column(nullable = false)
    private String orderNumber; // 주문번호(어떤 주문에 속하는 내용인지 구분하기 위함)

    @Column(nullable = false)
    private LocalDate orderDate; // 구매일시


    @Column(nullable = false)
    private int menuPrice;

    @Column(nullable = false)
    private boolean use;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuKey", nullable = false)
    private MenuEntity menu;

    @Column(nullable = false)
    private String state;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserKey", nullable = false)
    private UserEntity user; // 구매자
}
