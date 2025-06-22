package com.example.babpulServer.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userKey; // 기본키

    @Column(nullable = false, length = 50)
    private String name; // 이름


    @Column(nullable = false, length = 15)
    private String id;

    @Column(nullable = false, length = 19)
    private String pw;

    @Column(nullable = false, length = 10)
    private String gender; // 성별(남, 여)

    // yyyy-MM-dd 형식
    @Column(nullable = false, length = 10)
    private LocalDate birthDate; // 생년월일

    // xxx-xxxx-xxxx 형식
    @Column(nullable = false, length = 13)
    private String phoneNumber; // 휴대전화 번호

    @Column(nullable = false, length = 8)
    private String nickname; // 닉네임

    @Column(nullable = false)
    private boolean userCheck; // 사용자(가게주인, 아동급식카드 사용자) 인증여부


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private role userRole; // 유저역할

    public enum role {
        DONOR,
        BABPUL,
        BOSS
    }

    
}

