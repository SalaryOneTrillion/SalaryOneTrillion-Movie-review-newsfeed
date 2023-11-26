package com.sparta.salaryonetrillionmoviereviewnewsfeed.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewPostResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Movie;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.exception.CustomException;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.exception.ExceptionCode;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.MovieRepository;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewPostResponseDto postReview(Long movieId, ReviewRequestDto movieReviewRequestDto,
            User user) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_MOVIE));

        if (reviewRepository.existsByMovieAndUser(movie, user)) {
            throw new CustomException(ExceptionCode.BAD_REQUEST_ALREADY_EDITED_REVIEW);
        }

        Review review = new Review(movieReviewRequestDto, movie, user);
        reviewRepository.save(review);

        return new ReviewPostResponseDto(review);
    }

    public List<ReviewResponseDto> getReviewList(Long movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_MOVIE));

        return reviewRepository.findAllByMovieOrderByReviewLikeDescCreatedAtDesc(movie).stream()
                .map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    public ReviewResponseDto getReview(Long reviewId, Long movieId) {

        checkMovie(movieId);

        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_REVIEW));

        return new ReviewResponseDto(review);
    }

    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, ReviewRequestDto reviewRequestDto,
            User user, Long movieId) {

        checkMovie(movieId);

        Review review = getUserReviewSearchById(reviewId);
        forbiddenReviewEditCheck(user, review);
        review.setContent(reviewRequestDto.getContent());
        review.setMovieRating(reviewRequestDto.getMovieRating());

        return new ReviewResponseDto(review);
    }

    @Transactional
    public void deleteReview(Long reviewId, User user, Long movieId) {

        checkMovie(movieId);

        Review review = getUserReviewSearchById(reviewId);
        forbiddenReviewDeleteCheck(user, review);
        reviewRepository.delete(review);
    }

    private Review getUserReviewSearchById(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_REVIEW));

        return review;
    }

    private void forbiddenReviewEditCheck(User user, Review review) {
        if (!user.getId().equals(review.getUser().getId())) {
            throw new CustomException(ExceptionCode.FORBIDDEN_EDIT_ONLY_EDITED);
        }
    }

    private void forbiddenReviewDeleteCheck(User user, Review review) {
        if (!user.getId().equals(review.getUser().getId())) {
            throw new CustomException(ExceptionCode.FORBIDDEN_DELETE_ONLY_EDITED);
        }
    }

    private void checkMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new CustomException(ExceptionCode.NOT_FOUND_MOVIE);
        }
    }
}