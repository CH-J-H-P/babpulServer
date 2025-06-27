package com.example.babpulServer.DTO;

import com.example.babpulServer.Entity.RestaurantEntity;
import com.example.babpulServer.Entity.UserEntity;
import lombok.*;
import org.springframework.web.bind.annotation.SessionAttributes;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDTO {
    private Long restaurantKey;// 기본키
    private String restaurantName; // 식당 이름
    private String restaurantAddress; // 식당 주소
    private String category; // 카테고리(분식, 중식 등)
    private Long userKey; // 유저 PK만 포함



    // DTO → Entity 변환 메서드
    public RestaurantEntity toEntity() {
        // userKey만 있을 경우, PK만 세팅된 UserEntity 객체를 생성
        UserEntity user = UserEntity.builder()
                .userKey(this.userKey)
                .build();

        return RestaurantEntity.builder()
                .restaurantName(this.restaurantName)
                .restaurantAddress(this.restaurantAddress)
                .category(this.category)
                .user(user)
                .build();
    }

    // Entity -> DTO 변환 메서드
    public RestaurantDTO fromEntity(RestaurantEntity restaurantEntity) {
        return RestaurantDTO.builder()
                .restaurantKey(restaurantEntity.getRestaurantKey())
                .restaurantName(restaurantEntity.getRestaurantName())
                .restaurantAddress(restaurantEntity.getRestaurantAddress())
                .category(restaurantEntity.getCategory())
                .userKey(restaurantEntity.getUser().getUserKey())
                .build();
    }
}