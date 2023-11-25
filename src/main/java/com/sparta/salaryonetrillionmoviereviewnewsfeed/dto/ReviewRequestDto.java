package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {

    @Size(min = 1, max = 1000, message = "최대 1,000자까지 작성가능 합니다.")
    private String content;

    @Pattern(regexp = "^[1-5]$")
    private Long movieRating;
}