package com.example.babpulServer.DTO;

import com.example.babpulServer.Entity.CardEntity;
import com.example.babpulServer.Entity.UserEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long cardKey;
    private String cardNumber;
    private String cardCompany;
    private Long userKey;
    private String validThru;
    private CardEntity.CardType cardType;


    // Entity -> DTO
    public CardEntity toCardEntity() {
        UserEntity userEntity = UserEntity.builder()
                .userKey(this.userKey)
                .build();

        return CardEntity.builder()
                .cardKey(this.cardKey)
                .cardNumber(this.cardNumber)
                .cardCompany(this.cardCompany)
                .user(userEntity)
                .validThru(this.validThru)
                .cardType(this.cardType)
                .build();
    }
}

