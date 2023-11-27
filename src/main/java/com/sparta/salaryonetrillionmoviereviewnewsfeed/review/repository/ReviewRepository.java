package com.sparta.salaryonetrillionmoviereviewnewsfeed.review.repository;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.entity.Movie;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.entity.Review;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByMovieAndUser(Movie movie, User user);

    List<Review> findAllByMovieOrderByReviewLikeDescCreatedAtDesc(Movie movie);
}