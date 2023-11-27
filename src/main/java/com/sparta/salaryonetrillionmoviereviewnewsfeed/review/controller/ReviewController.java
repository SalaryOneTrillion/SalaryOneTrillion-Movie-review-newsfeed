package com.sparta.salaryonetrillionmoviereviewnewsfeed.review.controller;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.dto.ReviewRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.dto.ReviewResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.security.UserDetailsImpl;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies/{movieId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> postReview(@PathVariable Long movieId,
                                             @Valid @RequestBody ReviewRequestDto movieReviewRequestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewService.postReview(movieId, movieReviewRequestDto, userDetails.getUser());

        return ResponseEntity.status(201).body("리뷰가 등록되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getReviewList(@PathVariable Long movieId) {

        List<ReviewResponseDto> reviewResponseDto = reviewService.getReviewList(movieId);

        return ResponseEntity.status(200).body(reviewResponseDto);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long reviewId, @PathVariable Long movieId) {

        ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewId, movieId);

        return ResponseEntity.status(200).body(reviewResponseDto);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @Valid @RequestBody ReviewRequestDto reviewRequestDto,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long movieId) {

        reviewService.updateReview(reviewId, reviewRequestDto, userDetails.getUser(), movieId);

        return ResponseEntity.status(201).body("리뷰가 수정되었습니다.");
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long movieId) {

        reviewService.deleteReview(reviewId, userDetails.getUser(), movieId);

        return ResponseEntity.status(200).body("리뷰 삭제에 성공하였습니다.");
    }
}