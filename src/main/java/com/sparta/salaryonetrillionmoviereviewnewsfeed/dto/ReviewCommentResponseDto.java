package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.ReviewComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter

public class ReviewCommentResponseDto {
    private String nickname;
    private String content;
    private LocalDateTime createAt;


    public ReviewCommentResponseDto(ReviewComment reviewComment) {
        this.nickname = reviewComment.getUser().getNickname();
        this.content = reviewComment.getContent();
        this.createAt = reviewComment.getCreatedAt();
    }
}
