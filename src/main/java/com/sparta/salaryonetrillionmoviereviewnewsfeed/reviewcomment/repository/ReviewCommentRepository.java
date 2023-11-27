package com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.repository;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.reviewcomment.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

}
