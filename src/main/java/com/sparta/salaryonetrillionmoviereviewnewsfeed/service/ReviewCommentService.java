package com.sparta.salaryonetrillionmoviereviewnewsfeed.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewCommentResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewCommentRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.ReviewComment;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.exception.CustomException;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.exception.ExceptionCode;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.MovieRepository;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.ReviewCeommentRepository;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ReviewCeommentRepository reviewCeommentRepository;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public void postComment(ReviewCommentRequestDto requestDto, Long movieId,
            Long reviewId,
            User user) {

        checkMovieAndReview(movieId, reviewId);

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_REVIEW));
        ReviewComment reviewComment = new ReviewComment(requestDto, review, user);

        reviewCeommentRepository.save(reviewComment);
    }

    @Transactional
    public void updateComment(ReviewCommentRequestDto requestDto, Long movieId,
            Long reviewId, Long commentId,
            User user) {

        checkMovieAndReview(movieId, reviewId);

        ReviewComment reviewComment = reviewCeommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_REVIEW_COMMENT));

        if (!reviewComment.getUser().getId().equals(user.getId())) {
            throw new CustomException(ExceptionCode.FORBIDDEN_EDIT_ONLY_EDITED);
        }

        reviewComment.setContent(requestDto.getContent());
    }

    public void deleteComment(Long commentId,
            User user) {

        ReviewComment reviewComment = reviewCeommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_REVIEW_COMMENT));

        if (!reviewComment.getUser().getId().equals(user.getId())) {
            throw new CustomException(ExceptionCode.FORBIDDEN_DELETE_ONLY_EDITED);
        }

        reviewCeommentRepository.delete(reviewComment);
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
