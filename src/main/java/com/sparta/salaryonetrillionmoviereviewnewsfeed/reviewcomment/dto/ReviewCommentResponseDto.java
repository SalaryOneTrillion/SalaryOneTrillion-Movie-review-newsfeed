package com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.entity.ReviewComment;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCommentResponseDto {

    String content;
    String nickname;
    LocalDateTime createdAt;

    public ReviewCommentResponseDto(ReviewComment reviewComment) {
        this.content = reviewComment.getContent();
        this.nickname = reviewComment.getUser().getNickname();
        this.createdAt = reviewComment.getCreatedAt();
    }
}
