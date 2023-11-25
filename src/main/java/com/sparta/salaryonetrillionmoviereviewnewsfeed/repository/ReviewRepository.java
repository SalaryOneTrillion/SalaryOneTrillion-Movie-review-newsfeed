package com.sparta.salaryonetrillionmoviereviewnewsfeed.repository;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Movie;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review>  findAllByOrderByReviewLikeDescCreatedAtDesc();

    boolean existsByMovieAndUser(Movie movie, User user);
}