package com.sparta.salaryonetrillionmoviereviewnewsfeed.repository;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review>  findAllByOrderByCreatedAtDesc();
}
