package com.sparta.salaryonetrillionmoviereviewnewsfeed.review;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final  ReviewRepository reviewRepository;

    public ReviewResponseDto createReview(ReviewRequestDto movieReviewRequestDto, User user) {
        Review review = new Review(movieReviewRequestDto);
        return new ReviewResponseDto(review);
    }

    public List<ReviewResponseDto> getReviewList() {
        return reviewRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    public ReviewResponseDto getReviewDto(Long reviewId) {
        Review review = getReview(reviewId);
        return new ReviewResponseDto(review);
    }

    private Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다"));
    }


    public ReviewResponseDto updateReview(Long reviewId, ReviewRequestDto reviewRequestDto, User user) {
        Review review = getUserReview(reviewId, user);

        review.setContent(reviewRequestDto.getContent());
        review.setMovieRating(reviewRequestDto.getMovieRating());

        return new ReviewResponseDto(review);
    }

    private Review getUserReview(Long reviewId, User user) {
        Review review = getReview(reviewId);
        if (!user.getId().equals(review.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다!");
        }
        return review;
    }

    public void deleteReview(Long reviewId, User user) {
        Review review = getUserReview(reviewId,user);
        reviewRepository.delete(review);
    }

}
