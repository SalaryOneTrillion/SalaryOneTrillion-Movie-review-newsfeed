package com.sparta.salaryonetrillionmoviereviewnewsfeed.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewPostResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Movie;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.MovieRepository;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewPostResponseDto postReview(Long movieId, ReviewRequestDto movieReviewRequestDto, User user) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화는 존재하지 않습니다."));
        Review review = new Review(movieReviewRequestDto, movie, user);
        reviewRepository.save(review);

        return new ReviewPostResponseDto(review);
    }

    public List<ReviewResponseDto> getReviewList() {

        return reviewRepository.findAllByOrderByReviewLikeDescCreatedAtDesc().stream()
                .map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    public ReviewResponseDto getReviewDto(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다"));

        return new ReviewResponseDto(review);
    }

    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, ReviewRequestDto reviewRequestDto, User user) {

        Review review = getUserReviewSearchById(reviewId, user);

        review.setContent(reviewRequestDto.getContent());
        review.setMovieRating(reviewRequestDto.getMovieRating());

        return new ReviewResponseDto(review);
    }

    private Review getUserReviewSearchById(Long reviewId, User user) {

        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다"));

        if (!user.getId().equals(review.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다!");
        }

        return review;
    }

    @Transactional
    public void deleteReview(Long reviewId, User user) {

        Review review = getUserReviewSearchById(reviewId, user);
        reviewRepository.delete(review);
    }
}
