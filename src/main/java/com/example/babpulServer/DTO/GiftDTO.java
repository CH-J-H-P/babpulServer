package com.example.babpulServer.DTO;


import com.example.babpulServer.Entity.GiftEntity;
import com.example.babpulServer.Entity.UserEntity;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftDTO {
    private Long giftKey;
    private String orderNumber;
    private Long userKey;
    private LocalDateTime orderDate;
    private String status; // "UNUSED", "USED", "RESERVED"

    // Entity → DTO 변환
    public static GiftDTO fromEntity(GiftEntity entity) {
        return GiftDTO.builder()
                .giftKey(entity.getGiftKey())
                .orderNumber(entity.getOrderNumber())
                .userKey(entity.getUser().getUserKey())
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus().name())
                .build();
    }

    // DTO → Entity 변환 (UserEntity는 별도 조회 필요)
    public GiftEntity toEntity(UserEntity user) {
        return GiftEntity.builder()
                .giftKey(this.giftKey)
                .orderNumber(this.orderNumber)
                .user(user)
                .orderDate(this.orderDate)
                .status(GiftEntity.GiftStatus.valueOf(this.status))
                .build();
    }
}

