package com.sparta.salaryonetrillionmoviereviewnewsfeed.review;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewResponseDto {
    private Long id;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long reviewLike;
    private Long movieRating;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.username = review.getUser().getUsername();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
        this.reviewLike = review.getReviewLike();
        this.movieRating = review.getMovieRating();
    }
}
