package com.sparta.salaryonetrillionmoviereviewnewsfeed.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.MoviePageResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Movie;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.MovieRepository;
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
}
