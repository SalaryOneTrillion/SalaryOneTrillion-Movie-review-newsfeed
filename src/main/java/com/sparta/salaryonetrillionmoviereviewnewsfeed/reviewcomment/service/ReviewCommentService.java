package com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.exception.CustomException;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.exception.ExceptionCode;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.dto.ReviewCommentResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.dto.ReviewCommentRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.entity.ReviewComment;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.repository.MovieRepository;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.repository.ReviewCommentRepository;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewCommentResponseDto postComment(ReviewCommentRequestDto requestDto, Long movieId,
            Long reviewId,
            User user) {

        checkMovieAndReview(movieId, reviewId);

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_REVIEW));
        ReviewComment reviewComment = new ReviewComment(requestDto, review, user);
        reviewCommentRepository.save(reviewComment);

        return new ReviewCommentResponseDto(reviewComment);
    }

    @Transactional
    public ReviewCommentResponseDto updateComment(ReviewCommentRequestDto requestDto, Long movieId,
            Long reviewId, Long commentId,
            User user) {

        checkMovieAndReview(movieId, reviewId);

        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_REVIEW_COMMENT));

        if (!reviewComment.getUser().getId().equals(user.getId())) {
            throw new CustomException(ExceptionCode.FORBIDDEN_UPDATE_ONLY_WRITER);
        }

        reviewComment.setContent(requestDto.getContent());

        return new ReviewCommentResponseDto(reviewComment);
    }

    public void deleteComment(Long commentId, User user) {

        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_REVIEW_COMMENT));

        if (!reviewComment.getUser().getId().equals(user.getId())) {
            throw new CustomException(ExceptionCode.FORBIDDEN_DELETE_ONLY_WRITER);
        }

        reviewCommentRepository.delete(reviewComment);
    }

    private void checkMovieAndReview(Long movieId, Long reviewId) {
        if (!movieRepository.findById(movieId).isPresent()) {
            throw new CustomException(ExceptionCode.NOT_FOUND_MOVIE);
        }
        if (!reviewRepository.findById(reviewId).isPresent()) {
            throw new CustomException(ExceptionCode.NOT_FOUND_REVIEW);
        }
    }
}
