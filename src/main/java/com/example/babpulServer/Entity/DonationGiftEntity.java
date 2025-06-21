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
@Table(name = "donation_gift_table")
public class DonationGiftEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationGiftKey;

    @Column(nullable = false)
    private LocalDate reservationAt;

    @Column(nullable = false)
    private LocalDate expirationAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donationMenuKey", nullable = false)
    private DonationMenuEntity donationMenu; // 기부기록과 연결(기부일시, 기부금액, 메뉴엔티티와의 연결)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserKey")
    private UserEntity user; // 유저(수혜자) 엔티티 연결, null 허용

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationState donationState; // 상태(사용전, 예약, 사용완료)

    public enum DonationState {
        UNUSED,
        RESERVED,
        USED
    }
}
