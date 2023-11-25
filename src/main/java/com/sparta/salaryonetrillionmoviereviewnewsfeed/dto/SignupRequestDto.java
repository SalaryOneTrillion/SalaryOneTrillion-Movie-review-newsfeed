package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.UserRoleEnum;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    // 소문자 5~20자
    @Pattern(regexp = "^[a-z0-9]{5,20}$", message = "소문자 5~20자로 구성해주세요")
    String username;

    // 대,소문자 8~20자
    @Pattern(regexp = "^[a-zA-Z0-9]{8,20}$", message = "대, 소문자 8~20자로 구성해주세요")
    String password;

    // 소문자 @ 소문자.소문자 1~50자
    @Pattern(regexp = "^([a-z0-9]+)@([\\da-z\\.-]+)\\.([a-z\\.]{1,50})$", message = "이메일 형식으로 작성해주세요")
    String email;

    //한글 포함 공백
    @Pattern(regexp = "[^a-zA-Z0-9/]{2,10}", message = "한글, 알파벳 대,소문자, 숫자로 구성해주세요")
    String nickname;

    @Size(min = 0, max = 255, message = "최대 255자까지 작성 가능합니다")
    String introduction;

}