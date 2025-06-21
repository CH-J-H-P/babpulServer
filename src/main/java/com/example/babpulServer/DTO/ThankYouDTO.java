package com.example.babpulServer.DTO;

import com.example.babpulServer.Entity.ThankYouEntity;
import com.example.babpulServer.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThankYouDTO {
    private String subTitle; // 제목

    private String text; // 본문

    private UserEntity userDonor; // 수혜자 정보

    private UserEntity userRecipient; // 기부자 정보

    // DTO -> Entity
    public ThankYouEntity youEntity(){
        return ThankYouEntity.builder()
                .subTitle(this.subTitle)
                .text(this.text)
                .userDonor(this.userDonor)
                .userRecipient(this.userRecipient)
                .build();
    }
}
