package com.example.babpulServer.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "user_session_table")
public class UserSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId; // 기본키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", nullable = false)
    private UserEntity user; // UserEntity의 PK(실제로는 유저 엔티티 그 자체를 불러옴)

    @Column(nullable = false)
    private String sessionKey; // 유저와 실제로 주고받는 세션키

    @Column(nullable = false)
    private LocalDateTime createdAt; // 만든 일시

    @Column(nullable = false)
    private LocalDateTime expiresAt; // 세션 만료 일시

    //@Column(nullable = false) // 디바이스 정보 받아야함, 힘들면 빼고......
    //private String deviceInfo;
}
