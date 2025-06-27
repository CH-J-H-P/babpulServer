package com.example.babpulServer.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationDTO {
    private int money;
    private String sessionKey;
}
