package com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCommentRequestDto {

    @Size(min = 1, max = 255, message = "최대 255자까지 작성 가능합니다")
    String content;
}
