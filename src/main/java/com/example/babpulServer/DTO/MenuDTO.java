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
    private Long menuKey;
    private String menuName;
    private String menuDescription;
    private int menuPrice;
    private Long restaurantKey;


    // DTO → Entity 변환 메서드
    public MenuEntity toEntity() {
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .restaurantKey(this.restaurantKey)
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