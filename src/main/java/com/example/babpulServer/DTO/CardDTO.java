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
    private String sessionKey; // 세션키 (요청에서 줄 정보)
    private String validThru; // 유효기간
    private String cvc;
    private String prefix; // 비밀번호 앞 2자리
    private CardEntity.CardType cardType; // 카드 타입(기부용인지 급식카드인지 구분)


    // DTO -> Entity // 요청에서 얻지 못하는 UserEntity를 제외하고 변환
    public CardEntity toEntity() {

        return CardEntity.builder()
                .cardNumber(this.cardNumber)
                .cardCompany(this.cardCompany)
                .validThru(this.validThru)
                .cvc(this.cvc)
                .prefix(this.prefix)
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

