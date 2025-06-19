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
    private Long cardKey; // 기본키
    private String cardNumber; // 카드번호
    private String cardCompany; // 카드회사
    private Long userKey; // 유저키(UserEntity의 PK)
    private String validThru; // 유효기간
    private CardEntity.CardType cardType; // 카드 타입(기부용인지 급식카드인지 구분)


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

