package com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.entity;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.dto.ReviewCommentRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class ReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 255)
    private String content;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = true, nullable = false)
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public ReviewComment(ReviewCommentRequestDto requestDto, Review review, User user) {
        this.content = requestDto.getContent();
        this.review = review;
        this.user = user;
    }
}