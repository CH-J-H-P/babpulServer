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

    private String sessionKey; // 수혜자 정보


    // DTO -> Entity
    public ThankYouEntity youEntity(){
        return ThankYouEntity.builder()
                .subTitle(this.subTitle)
                .text(this.text)
                .build();
    }
}
