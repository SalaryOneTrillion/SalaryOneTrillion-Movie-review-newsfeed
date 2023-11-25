package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserPasswordDto {

    String oldPassword;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,20}$", message = "대, 소문자 8~20자로 구성해주세요")
    String newPassword;
}
