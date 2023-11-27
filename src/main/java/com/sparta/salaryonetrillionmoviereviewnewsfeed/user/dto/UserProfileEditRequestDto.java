package com.sparta.salaryonetrillionmoviereviewnewsfeed.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserProfileEditRequestDto {

    @Size(max =  255)
    String introduction;

}
