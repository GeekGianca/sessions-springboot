package com.example.session.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    private Long id;
    private String sessionUuid;
    private long creationTimestamp;
    private boolean sessionState;
    private UserDto user;

    public String randomSrc() {
        List<String> src = new ArrayList<>();
        src.add("/img/team-1.jpg");
        src.add("/img/team-2.jpg");
        src.add("/img/team-3.jpg");
        src.add("/img/team-4.jpg");
        Random rand = new Random();
        int n = rand.nextInt(3);
        return src.get(n);
    }
}
