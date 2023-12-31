package com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.dto.MovieReviewResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MovieResponseDto {

    private String title;
    private String director;
    private String releaseDate;
    private String genre;
    private String country;
    private String ratingAvg;
    private List<MovieReviewResponseDto> reviews;

    public MovieResponseDto(Movie movie) {
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.releaseDate = movie.getRelease_date();
        this.genre = movie.getGenre();
        this.country = movie.getCountry();
        this.ratingAvg = movie.getReviewRatingAvg();
        this.reviews = movie.getReviews().stream()
                .map(MovieReviewResponseDto::new)
                .collect(Collectors.toList());
    }
}