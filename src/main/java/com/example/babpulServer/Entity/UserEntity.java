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
    private Long userKey;

    @Column(nullable = false, length = 50)
    private String name;

    // xxxxxx@emain.com 형식
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 15)
    private String id;

    @Column(nullable = false, length = 19)
    private String pw;

    @Column(nullable = false, length = 10)
    private String gender;

    // yyyy-MM-dd 형식
    @Column(nullable = false, length = 10)
    private LocalDate birthDate;

    // xxx-xxxx-xxxx 형식
    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Column(nullable = false, length = 8)
    private String nickname;

    @Column(nullable = false, length = 5)
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private role userRole;

    public enum role {
        owner,
        contributor,
        babpul
    }
}

