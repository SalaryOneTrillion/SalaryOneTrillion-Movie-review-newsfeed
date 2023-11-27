package com.sparta.salaryonetrillionmoviereviewnewsfeed.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserEmailRequestDto {

    @Pattern(regexp = "^([a-z0-9]+)@([\\da-z\\.-]+)\\.([a-z\\.]{1,50})$", message = "이메일 형식으로 작성해주세요")
    String email;
}
