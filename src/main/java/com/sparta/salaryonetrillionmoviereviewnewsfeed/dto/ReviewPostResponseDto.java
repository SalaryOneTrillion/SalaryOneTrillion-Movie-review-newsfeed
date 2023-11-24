package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReviewPostResponseDto {

    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private Long reviewLike;
    private Long movieRating;


    public ReviewPostResponseDto(Review review) {
        this.nickname = review.getUser().getUsername();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.reviewLike = review.getReviewLike();
        this.movieRating = review.getMovieRating();
    }
}
