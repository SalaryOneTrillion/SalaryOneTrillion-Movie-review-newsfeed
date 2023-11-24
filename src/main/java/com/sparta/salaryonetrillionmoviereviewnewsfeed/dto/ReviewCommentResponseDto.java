package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;
import lombok.Getter
import lombok.Setter
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.ReviewComment;
import java.time.LocalDateTime;

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
