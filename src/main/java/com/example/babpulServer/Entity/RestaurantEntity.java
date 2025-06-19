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
    private Long restaurantKey;

    @Column(nullable = false, length = 20)
    private String restaurantName;

    @Column(nullable = false, length = 20)
    private String restaurantAddress;

    @Column(nullable = false, length = 20)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 옵션(필요할 때만 불러옴)
    @JoinColumn(name = "userKey", nullable = false) // 외래키 옵션
    private UserEntity user; // 유저 정보 그 자체를 가져온다
}