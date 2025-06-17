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
@Table(name = "menu_table")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long menuKey;

    @Column(nullable = false, length = 20)
    private String menuName;

    @Column(nullable = false, length = 100)
    private String menuDescription;

    @Column(nullable = false, length = 10)
    private int menuPrice;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 옵션(필요할 때만 불러옴)
    @JoinColumn(nullable = false) // 외래키 옵션
    private RestaurantEntity restaurant; // 시당 정보 그 자체를 가져온다
}
