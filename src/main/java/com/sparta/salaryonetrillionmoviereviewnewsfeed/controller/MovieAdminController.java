package com.sparta.salaryonetrillionmoviereviewnewsfeed.controller;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.MovieUpdateRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.service.MovieAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MovieAdminController {

    private final MovieAdminService movieAdminService;

    @PostMapping("/updateMovies")
    public void updateMovies(@RequestBody MovieUpdateRequestDto requestDto) {
        movieAdminService.updateMovies(requestDto.getCurPage(), requestDto.getItemPerPage(),
                requestDto.getOpenStartDt(), requestDto.getOpenEndDt());
    }

}
