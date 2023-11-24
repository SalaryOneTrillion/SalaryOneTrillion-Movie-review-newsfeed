package com.sparta.salaryonetrillionmoviereviewnewsfeed.controller;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.MoviePageResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.MovieResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<MoviePageResponseDto>> getMovies(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam boolean isAsc) {

        return ResponseEntity.status(200).body(movieService.getMovies(page-1, size, sortBy, isAsc));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable Long movieId) {

        return ResponseEntity.status(200).body(movieService.getMovie(movieId));
    }
}
