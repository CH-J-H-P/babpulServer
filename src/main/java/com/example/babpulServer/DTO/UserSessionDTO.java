package com.example.babpulServer.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionDTO {
    private String sessionKey; // 유저와 주고받을 세션정보
}

