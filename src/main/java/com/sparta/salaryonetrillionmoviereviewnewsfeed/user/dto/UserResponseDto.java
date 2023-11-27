package com.sparta.salaryonetrillionmoviereviewnewsfeed.user.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    String username;
    String nickname;
    String email;
    String introduction;
    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.introduction = user.getIntroduction();
    }
}
