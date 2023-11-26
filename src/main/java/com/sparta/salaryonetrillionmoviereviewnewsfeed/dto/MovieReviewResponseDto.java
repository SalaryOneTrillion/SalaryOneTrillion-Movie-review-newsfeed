package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class MovieReviewResponseDto {
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private Long reviewLike;
    private Long movieRating;
    private Long reviewCommentCnt;

    public MovieReviewResponseDto(Review review) {
        this.nickname = review.getUser().getNickname();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.reviewLike = review.getReviewLike();
        this.movieRating = review.getMovieRating();
        this.reviewCommentCnt = review.getReviewCommentCount();
    }
}
