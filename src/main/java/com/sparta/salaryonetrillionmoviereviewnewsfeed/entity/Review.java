package com.sparta.salaryonetrillionmoviereviewnewsfeed.entity;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private Long reviewLike;

    @Column(nullable = false)
    private Long movieRating;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Movie movie;

    @OneToMany(mappedBy = "review")
    private List<ReviewComment> reviewComments;

    public Review(ReviewRequestDto requestDto, Movie movie, User user) {
        this.content = requestDto.getContent();
        this.movieRating = requestDto.getMovieRating();
        this.reviewLike = 0L;
        this.movie = movie;
        this.user = user;
    }
}