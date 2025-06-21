package com.example.babpulServer.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "thank_you_table")
public class ThankYouEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thankYouKey; // 기본키

    @Column(nullable = false)
    private String subTitle; // 제목

    @Column(nullable = false)
    private String text; // 본문

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserEntity userDonor; // 수혜자 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserEntity userRecipient; // 기부자 정보
}
