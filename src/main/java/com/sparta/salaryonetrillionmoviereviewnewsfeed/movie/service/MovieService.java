package com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.exception.CustomException;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.exception.ExceptionCode;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.dto.MoviePageResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.dto.MovieResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.entity.Movie;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<MoviePageResponseDto> getMovies(int page, int size, String sortBy, boolean isAsc) {

        Sort.Direction direction = isAsc ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Movie> moviePage;

        moviePage = movieRepository.findAll(pageable);

        return moviePage.map(MoviePageResponseDto::new);
    }

    public MovieResponseDto getMovie(Long movieId) {

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new CustomException(
                ExceptionCode.NOT_FOUND_MOVIE));

        return new MovieResponseDto(movie);
    }
}
