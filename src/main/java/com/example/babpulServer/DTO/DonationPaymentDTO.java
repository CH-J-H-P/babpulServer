package com.example.babpulServer.DTO;

import com.example.babpulServer.Entity.DonationPaymentEntity;
import com.example.babpulServer.Entity.MenuEntity;
import com.example.babpulServer.Entity.UserEntity;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationPaymentDTO {
    private Long donationMenuKey;
    private String orderNumber;
    private LocalDate orderDate;
    private int menuPrice;
    private boolean use;
    private Long menuKey;
    private String menuName;
    private String state;
    private Long userKey;

    // Entity → DTO 변환
    public static DonationPaymentDTO fromEntity(DonationPaymentEntity entity) {
        return DonationPaymentDTO.builder()
                .donationMenuKey(entity.getDonationMenuKey())
                .orderNumber(entity.getOrderNumber())
                .orderDate(entity.getOrderDate())
                .menuPrice(entity.getMenuPrice())
                .use(entity.isUse())
                .menuKey(entity.getMenu().getMenuKey())
                .menuName(entity.getMenu().getMenuName())
                .state(entity.getState())
                .userKey(entity.getUser().getUserKey())
                .build();
    }

    // DTO → Entity 변환 (MenuEntity, UserEntity는 별도 조회 필요)
    public DonationPaymentEntity toEntity(MenuEntity menu, UserEntity user) {
        return DonationPaymentEntity.builder()
                .donationMenuKey(this.donationMenuKey)
                .orderNumber(this.orderNumber)
                .orderDate(this.orderDate)
                .menuPrice(this.menuPrice)
                .use(this.use)
                .menu(menu)
                .state(this.state)
                .user(user)
                .build();
    }
}

