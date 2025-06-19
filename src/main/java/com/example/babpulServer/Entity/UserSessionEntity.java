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
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", nullable = false)
    private UserEntity user; // UserEntity의 PK

    @Column(nullable = false)
    private String sessionKey;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    //@Column(nullable = false) // 디바이스 정보 받아야함, 힘들면 빼고......
    //private String deviceInfo;
}
