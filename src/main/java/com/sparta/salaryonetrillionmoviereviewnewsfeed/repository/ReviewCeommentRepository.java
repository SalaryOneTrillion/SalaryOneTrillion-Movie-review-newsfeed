package com.sparta.salaryonetrillionmoviereviewnewsfeed.repository;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCeommentRepository extends JpaRepository<ReviewComment, Long> {

}
