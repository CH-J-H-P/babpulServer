package com.example.babpulServer.DTO;

import com.example.babpulServer.Entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name; // 이름
    private String email; // 이메일
    private String id;
    private String pw;
    private String gender; // 성별
    private String phoneNumber; // 휴대전화 번호
    private String nickname; // 닉네임
    private UserEntity.role userRole; // 역할

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate; // 생년월일


    // DTO-> Entity 변환 메서드
    public UserEntity toUserEntity() {
        return UserEntity.builder()
                .name(this.name)
                .id(this.id)
                .pw(this.pw)
                .gender(this.gender)
                .birthDate(this.birthDate)
                .phoneNumber(this.phoneNumber)
                .nickname(this.nickname)
                .userRole(this.userRole)
                .build();
    }

    // Entity -> DTO 변환 베서드
    public UserDTO toUserDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .name(userEntity.getName())
                .id(userEntity.getId())
                .pw(userEntity.getPw())
                .gender(userEntity.getGender())
                .birthDate(userEntity.getBirthDate())
                .phoneNumber(userEntity.getPhoneNumber())
                .nickname(userEntity.getNickname())
                .userRole(userEntity.getUserRole())
                .build();
    }
}
