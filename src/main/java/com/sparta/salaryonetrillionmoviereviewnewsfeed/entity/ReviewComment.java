package com.sparta.salaryonetrillionmoviereviewnewsfeed.entity;

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
    @Column(name = "comm_id", nullable = false)
    private Long id;

    @Column(name = "comm_content", nullable = false, length = 255)
    private String content;

    @CreatedDate
    @Column(name = "review_created_At", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "review_modified_At",updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Review review;

}
