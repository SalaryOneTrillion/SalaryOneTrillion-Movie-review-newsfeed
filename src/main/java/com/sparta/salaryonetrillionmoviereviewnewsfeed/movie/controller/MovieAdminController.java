package com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.controller;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.dto.MovieUpdateRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.service.MovieAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/movie")
@RequiredArgsConstructor
public class MovieAdminController {

    private final MovieAdminService movieAdminService;

    @PostMapping("/update")
    public ResponseEntity<String> updateMovies(@RequestBody MovieUpdateRequestDto requestDto) {
        movieAdminService.updateMovies(requestDto.getCurPage(), requestDto.getItemPerPage(),
                requestDto.getOpenStartDt(), requestDto.getOpenEndDt());

        return ResponseEntity.status(201).body("영화 목록이 업데이트 되었습니다.");
    }

}
