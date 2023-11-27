package com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.controller;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.dto.ReviewCommentRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.security.UserDetailsImpl;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.service.ReviewCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies/{movieId}/reviews/{reviewId}/comments")
@RequiredArgsConstructor
public class ReviewCommentController {

    private final ReviewCommentService reviewCommentService;

    @PostMapping
    public ResponseEntity<String> postComment(
            @Valid @RequestBody ReviewCommentRequestDto requestDto,
            @PathVariable Long movieId, @PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewCommentService.postComment(requestDto, movieId, reviewId, userDetails.getUser());

        return ResponseEntity.status(201).body("댓글이 등록되었습니다.");
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(
            @Valid @RequestBody ReviewCommentRequestDto requestDto,
            @PathVariable Long movieId, @PathVariable Long reviewId, @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewCommentService.updateComment(requestDto, movieId, reviewId, commentId,
                userDetails.getUser());

        return ResponseEntity.status(200).body("댓글이 수정 되었습니다.");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long movieId,
            @PathVariable String reviewId, @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewCommentService.deleteComment(commentId, userDetails.getUser());

        return ResponseEntity.status(200).body("댓글 삭제에 성공하였습니다.");
    }
}
