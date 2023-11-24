package com.sparta.salaryonetrillionmoviereviewnewsfeed.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewCommentResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewCommentRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.ReviewComment;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.MovieRepository;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.ReviewCeommentRepository;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ReviewCeommentRepository reviewCeommentRepository;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewCommentResponseDto postComment(ReviewCommentRequestDto requestDto, Long movieId,
            Long reviewId,
            User user) {

        checkMovieAndReview(movieId, reviewId);

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));
        ReviewComment reviewComment = new ReviewComment(requestDto, review, user);
        reviewCeommentRepository.save(reviewComment);

        return new ReviewCommentResponseDto(reviewComment);
    }

    @Transactional
    public ReviewCommentResponseDto updateComment(ReviewCommentRequestDto requestDto, Long movieId,
            Long reviewId, Long commentId,
            User user) {

        checkMovieAndReview(movieId, reviewId);

        ReviewComment reviewComment = reviewCeommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));

        if (!reviewComment.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 댓글을 수정할 권한이 없습니다.");
        }

        reviewComment.setContent(requestDto.getContent());

        return new ReviewCommentResponseDto(reviewComment);
    }

    @Transactional
    public void deleteComment(Long commentId,
            User user) {

        ReviewComment reviewComment = reviewCeommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));

        if (!reviewComment.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 댓글을 삭제할 권한이 없습니다.");
        }

        reviewCeommentRepository.delete(reviewComment);
    }

    private void checkMovieAndReview(Long movieId, Long reviewId) {
        if (!movieRepository.findById(movieId).isPresent()) {
            throw new IllegalArgumentException("해당 영화는 존재하지 않습니다.");
        }
        if (!reviewRepository.findById(reviewId).isPresent()) {
            throw new IllegalArgumentException("해당 리뷰는 존재하지 않습니다.");
        }
    }
}
