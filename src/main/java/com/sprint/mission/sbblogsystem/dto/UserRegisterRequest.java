package com.sprint.mission.sbblogsystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequest {
    private String id;
    private String password;
    private String email;
    private String nickname;
}
