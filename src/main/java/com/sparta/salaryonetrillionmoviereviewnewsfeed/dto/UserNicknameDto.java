package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserNicknameDto {

    @Pattern(regexp = "^[a-zA-Z0-9/가-힣]{2,10}$", message = "한글, 알파벳 대,소문자, 숫자로 구성해주세요")
    String nickname;
}
