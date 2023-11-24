package com.sparta.salaryonetrillionmoviereviewnewsfeed.review;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie/{movieid}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> postReview(@RequestBody ReviewRequestDto movieReviewRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ReviewResponseDto movieReviewResponseDto = reviewService.createReview(movieReviewRequestDto, userDetails.getUser());
        return ResponseEntity.status(201).body(movieReviewResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getReviewList() {
        List<ReviewResponseDto> reviewResponseDto = reviewService.getReviewList();
        return ResponseEntity.status(200).body(reviewResponseDto);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long reviewId) {
        ReviewResponseDto reviewResponseDto = reviewService.getReviewDto(reviewId);
        return ResponseEntity.status(200).body(reviewResponseDto);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(Long reviewId, @RequestBody ReviewRequestDto reviewRequestDto,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ReviewResponseDto reviewResponseDto = reviewService.updateReview(reviewId, reviewRequestDto, userDetails.getUser());
        return ResponseEntity.status(201).body(reviewResponseDto);
    }

    @DeleteMapping("/{reviewId")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.deleteReview(reviewId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
