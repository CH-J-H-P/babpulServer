package com.example.babpulServer.DTO;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationGiftDTO {
    private LocalDate reservationAt;
    private LocalDate expirationAt;
    private Long donationMenu;
    private Long user;
    private String donationState;
}


