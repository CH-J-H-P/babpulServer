package com.example.babpulServer.DTO;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantInfoDTO {
    String restaurantName;
    String category;
    String giftCount;
    String totalGiftCount;
    String address;
}
