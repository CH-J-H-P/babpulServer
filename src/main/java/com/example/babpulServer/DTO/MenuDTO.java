package com.example.babpulServer.DTO;

import com.example.babpulServer.Entity.MenuEntity;
import com.example.babpulServer.Entity.RestaurantEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private Long menuKey; // 메뉴키
    private String menuName; //메뉴명
    private String menuDescription; // 메뉴설명
    private int menuPrice; // 메뉴 가격
    private Long restaurant; // 식당 기본키


    // DTO → Entity 변환 메서드
    public MenuEntity toEntity() {
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .restaurantKey(this.restaurant)
                .build();

        return MenuEntity.builder()
                .menuKey(this.menuKey)
                .menuName(this.menuName)
                .menuDescription(this.menuDescription)
                .menuPrice(this.menuPrice)
                .restaurant(restaurant)
                .build();
    }
}