package com.sparta.salaryonetrillionmoviereviewnewsfeed.controller;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewPostResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.security.UserDetailsImpl;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.service.ReviewService;

import java.util.List;

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
@RequiredArgsConstructor
@RequestMapping("/movies/{movieId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewPostResponseDto> postReview(@PathVariable Long movieId,
            @Valid @RequestBody ReviewRequestDto movieReviewRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ReviewPostResponseDto movieReviewResponseDto = reviewService.postReview(movieId,
                movieReviewRequestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(movieReviewResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getReviewList(@PathVariable Long movieId) {

        List<ReviewResponseDto> reviewResponseDto = reviewService.getReviewList(movieId);

        return ResponseEntity.status(200).body(reviewResponseDto);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long reviewId) {

        ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewId);

        return ResponseEntity.status(200).body(reviewResponseDto);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long reviewId,
            @Valid @RequestBody ReviewRequestDto reviewRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ReviewResponseDto reviewResponseDto = reviewService.updateReview(reviewId, reviewRequestDto,
                userDetails.getUser());

        return ResponseEntity.status(201).body(reviewResponseDto);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewService.deleteReview(reviewId, userDetails.getUser());

        return ResponseEntity.noContent().build();
    }
}