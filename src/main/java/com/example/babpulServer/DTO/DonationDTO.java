package com.example.babpulServer.DTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationDTO {
    private int money;
    private String sessionKey;
    private LocalDateTime donationDate;
}
