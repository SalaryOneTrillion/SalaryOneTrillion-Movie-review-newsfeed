package com.sparta.salaryonetrillionmoviereviewnewsfeed.controller;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewCommentRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewCommentResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.security.UserDetailsImpl;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.service.ReviewCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class ReviewCommentController {

    private final ReviewCommentService reviewCommentService;

    @PostMapping("/{movieId}/reviews/{reviewId}/comments")
    public ResponseEntity<ReviewCommentResponseDto> postComment(@Valid @RequestBody ReviewCommentRequestDto requestDto,
            @PathVariable Long movieId, @PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(200)
                .body(reviewCommentService.postComment(requestDto, movieId, reviewId,
                        userDetails.getUser()));
    }

    @PutMapping("/{movieId}/reviews/{reviewId}/comments/{commentId}")
    public ResponseEntity<ReviewCommentResponseDto> updateComment(@Valid @RequestBody ReviewCommentRequestDto requestDto,
            @PathVariable Long movieId, @PathVariable Long reviewId, @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(200).body(reviewCommentService.updateComment(requestDto, movieId, reviewId, commentId, userDetails.getUser()));
    }

    @DeleteMapping("/{movieId}/reviews/{reviewId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long movieId, @PathVariable String reviewId, @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewCommentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.status(200).body("댓글 삭제에 성공 했습니다.");
    }
}
