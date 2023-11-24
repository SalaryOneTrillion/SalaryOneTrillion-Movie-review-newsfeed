package com.sparta.salaryonetrillionmoviereviewnewsfeed.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private String content;
    private Long reviewLike;
    private Long movieRating;
}
