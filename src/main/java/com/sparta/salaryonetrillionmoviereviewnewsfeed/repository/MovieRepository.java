package com.sparta.salaryonetrillionmoviereviewnewsfeed.repository;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


}
