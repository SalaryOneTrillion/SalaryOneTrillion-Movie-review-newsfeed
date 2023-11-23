package com.sparta.salaryonetrillionmoviereviewnewsfeed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_username", unique = true, nullable = false, length = 20)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "user_nickname", unique = true, nullable = false, length = 10)
    private String nickname;

    @Column(name = "user_intro", nullable = false)
    private String intro;

    @CreatedDate
    @Column(name = "user_created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<ReviewComment> reviewComments;
}