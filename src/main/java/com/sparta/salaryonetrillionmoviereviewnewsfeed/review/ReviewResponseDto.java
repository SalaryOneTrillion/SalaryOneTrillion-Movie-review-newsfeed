package com.sparta.salaryonetrillionmoviereviewnewsfeed.review;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewResponseDto {

    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private Long reviewLike;
    private Long movieRating;

    public ReviewResponseDto(Review review) {
        this.nickname = review.getUser().getUsername();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.reviewLike = review.getReviewLike();
        this.movieRating = review.getMovieRating();
    }
}
