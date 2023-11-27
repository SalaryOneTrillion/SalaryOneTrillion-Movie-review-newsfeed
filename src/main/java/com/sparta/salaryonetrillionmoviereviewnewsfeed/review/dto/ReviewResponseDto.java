package com.sparta.salaryonetrillionmoviereviewnewsfeed.review.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.dto.ReviewCommentResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReviewResponseDto {

    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private Long reviewLike;
    private Long movieRating;
    private List<ReviewCommentResponseDto> comment;


    public ReviewResponseDto(Review review) {
        this.nickname = review.getUser().getNickname();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.reviewLike = review.getReviewLike();
        this.movieRating = review.getMovieRating();
        this.comment = review.getReviewComments().stream()
                .map(ReviewCommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
