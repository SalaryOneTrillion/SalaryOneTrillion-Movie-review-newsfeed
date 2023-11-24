package com.sparta.salaryonetrillionmoviereviewnewsfeed.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewCommentResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewCommentRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.ReviewComment;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.ReviewCeommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ReviewCeommentRepository reviewCeommentRepository;
    private final ReviewRepository reviewRepository;

    public ReviewCommentResponseDto postComment(ReviewCommentRequestDto requestDto, Long reviewId,
            User user) {
        Review review = reviewRepository.findById(reviewId);
        ReviewComment reviewComment = new ReviewComment(requestDto, review, user);
        reviewCeommentRepository.save(reviewComment);

        return new ReviewCommentResponseDto(reviewComment);
    }

    @Transactional
    public ReviewCommentResponseDto updateComment(ReviewCommentRequestDto requestDto, Long commentId,
            User user) {
        ReviewComment reviewComment = reviewCeommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));

        if (!reviewComment.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 댓글을 수정할 권한이 없습니다.");
        }

        reviewComment.setContent(requestDto.getContent());

        return new ReviewCommentResponseDto(reviewComment);
    }

    @Transactional
    public void deleteComment(ReviewCommentRequestDto requestDto, Long commentId,
            User user) {
        ReviewComment reviewComment = reviewCeommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));

        if (!reviewComment.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 댓글을 수정할 권한이 없습니다.");
        }

        reviewCeommentRepository.delete(reviewComment);
    }
}
