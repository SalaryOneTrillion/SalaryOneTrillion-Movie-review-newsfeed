package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {

    @Size(min = 1, max = 1000, message = "최대 1,000자까지 작성가능 합니다.")
    private String content;

    @Min(1)
    @Max(5)
    private Long movieRating;
}