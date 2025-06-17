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
    private Long restaurantKey;
    private String restaurantName;
    private String restaurantAddress;
    private String category;
    private Long userKey; // 유저 PK만 포함

    // DTO → Entity 변환 메서드
    public RestaurantEntity toEntity() {
        // userKey만 있을 경우, PK만 세팅된 UserEntity 객체를 생성
        UserEntity user = UserEntity.builder()
                .userKey(this.userKey)
                .build();

        return RestaurantEntity.builder()
                .restaurantKey(this.restaurantKey)
                .restaurantName(this.restaurantName)
                .restaurantAddress(this.restaurantAddress)
                .category(this.category)
                .user(user)
                .build();
    }
}