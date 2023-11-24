package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    String username;
    String password;
    String email;
    String nickname;
    String introduction;

}
