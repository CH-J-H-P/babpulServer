package com.example.babpulServer.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_table")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantKey; // 기본키

    @Column(nullable = false)
    private String restaurantName; // 식당명

    @Column(nullable = false)
    private String restaurantAddress; // 식당 주소

    @Column(nullable = false)
    private String category; // 카테고리(분식, 중식 등등)

    @Column(nullable = false)
    private String restaurantExplain; // 식당 설명

    @Column(nullable = false)
    private String operatingHours; // 운영시간

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 옵션(필요할 때만 불러옴)
    @JoinColumn(name = "userKey", nullable = false) // 외래키 옵션
    private UserEntity user; // 유저 정보 그 자체를 가져온다
}