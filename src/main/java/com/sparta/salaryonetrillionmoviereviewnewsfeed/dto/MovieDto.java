package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    // 국가
    private String nationAlt;
    // 장르
    private String repGenreNm;
    // 영화 제목
    private String movieNm;
    // 영화 개봉일
    private String openDt;
    // 촬영 국가
    private String repNationNm;
    // 감독
    private List<DirectorDto> directors;
    // 영화 고유 번호
    private Long movieCd;
    //
    private String genreAlt;
    //
    private String prdStatNm;
    //
    private String prdtYear;
    //
    private String typeNm;
    //
    private String prdtStatNm;
    //
    private String movieNmEn;
    // 회사
    private List<MovieCompanyDto> companys;
}
