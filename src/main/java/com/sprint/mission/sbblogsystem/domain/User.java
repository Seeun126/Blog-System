package com.sprint.mission.sbblogsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String id;
    private String password;
    private String email;
    private String nickname;
    private Instant createdAt;

}
