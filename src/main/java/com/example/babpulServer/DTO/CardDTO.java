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
    private String cardNumber; // 카드번호
    private String cardCompany; // 카드회사
    private Long userKey; // 유저키(UserEntity의 PK)
    private String validThru; // 유효기간
    private CardEntity.CardType cardType; // 카드 타입(기부용인지 급식카드인지 구분)


    // DTO -> Entity
    public CardEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .userKey(this.userKey)
                .build();

        return CardEntity.builder()
                .cardNumber(this.cardNumber)
                .cardCompany(this.cardCompany)
                .user(userEntity)
                .validThru(this.validThru)
                .cardType(this.cardType)
                .build();
    }

    // Entity -> DTO (사용자 카드 조회)
    public static CardDTO toDTOForSerch(CardEntity cardEntity) {
        return CardDTO.builder()
                .cardNumber(cardEntity.getCardNumber())
                .cardCompany(cardEntity.getCardCompany())
                .build();
    }
}

