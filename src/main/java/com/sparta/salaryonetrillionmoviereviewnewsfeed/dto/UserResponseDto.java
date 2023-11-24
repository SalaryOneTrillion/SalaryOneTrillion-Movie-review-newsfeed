package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;

public class UserResponseDto {

    String username;
    String nickname;
    String email;
    String introduction;
    public UserResponseDto(User user) {
    }
}
