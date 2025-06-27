package com.example.babpulServer.DTO;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPageDTO {
    private String username;
    private String nickname;

    // 사장일 경우
    private Long restaurantKey;
    private String restaurantName;
    private String restaurantAddress;

    // 밥풀이일 경우
    private int kidMoney;
    private int companyMoney;
}
