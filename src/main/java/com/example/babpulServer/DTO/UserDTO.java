package com.example.babpulServer.DTO;

import com.example.babpulServer.Entity.UserEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long userKey;
    private String name;
    private String email;
    private String id;
    private String pw;
    private String gender;
    private LocalDate birthDate;
    private String phoneNumber;
    private String nickname;
    private String role;
    private UserEntity.role userRole;

    // DTO → Entity 변환 메서드
    public UserEntity toUserEntity() {
        return UserEntity.builder()
                .name(this.name)
                .email(this.email)
                .id(this.id)
                .pw(this.pw)
                .gender(this.gender)
                .birthDate(this.birthDate)
                .phoneNumber(this.phoneNumber)
                .nickname(this.nickname)
                .role(this.role)
                .userRole(this.userRole)
                .build();
    }

}
