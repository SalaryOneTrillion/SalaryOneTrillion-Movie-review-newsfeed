package com.sparta.salaryonetrillionmoviereviewnewsfeed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    @Column(name = "user_username")
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_nickname")
    private String nickname;

    @Column(name = "user_intro")
    private String intro;

    @Column(name = "user_created_at")
    private LocalDateTime createdAt;

    @OneToMany
    private List<Review> reviews;

    @OneToMany
    private List<ReviewComment> reviewComments
}
