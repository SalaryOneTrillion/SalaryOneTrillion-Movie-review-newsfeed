package com.sparta.salaryonetrillionmoviereviewnewsfeed.review.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
