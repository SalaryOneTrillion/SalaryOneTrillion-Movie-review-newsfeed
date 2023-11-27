package com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.dto;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MoviePageResponseDto {
    private String title;
    private String director;
    private String releaseDate;
    private String genre;
    private String ratingAvg;
    private String country;

    public MoviePageResponseDto(Movie movie) {
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.releaseDate = movie.getRelease_date();
        this.genre = movie.getGenre();
        this.country = movie.getCountry();
        this.ratingAvg = movie.getReviewRatingAvg();
    }
}
