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

    private String restaurantKey;
    private String restaurantName;
}
