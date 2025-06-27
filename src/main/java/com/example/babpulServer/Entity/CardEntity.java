package com.example.babpulServer.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_table")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardKey; // 카드 기본키

    @Column(nullable = false, unique = true)
    private String cardNumber; // 카드번호

    @Column(nullable = false)
    private String cardCompany; // 카드회사

    // 카드 소유 유저(외래키)
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 옵션(필요할 때만 불러옴)
    @JoinColumn(name = "userKey", nullable = false) // 외래키 옵션
    private UserEntity user; // 유저 정보 그 자체를 가져온다

    @Column(nullable = false)
    private String cvc; // cvc

    @Column(nullable = false)
    private String prefix; // 비밀번호 앞 2자리

    // 유효기간(mm/yy)
    @Column(nullable = false)
    private String validThru;

    // 금액
    @Column(nullable = true)
    private int money;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType; // 기부용인지 아동급식카드인지 쿠분

    public enum CardType {
        CHILD_MEAL_CARD,
        NOMAL_CARD
    }
}
