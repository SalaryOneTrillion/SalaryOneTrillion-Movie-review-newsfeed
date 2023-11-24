package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    // 영화 코드
    private Long movieCd;
    // 영화명(국문)
    private String movieNm;
    // 영화명(영문)
    private String movieNmEn;
    // 대표 제작 국가명
    private String repNationNm;
    // 제작 국가 (전체)
    private String nationAlt;
    // 대표 장르명
    private String repGenreNm;
    // 영화 장르 (전체)
    private String genreAlt;
    // 개봉일
    private String openDt;
    // 영화 유형
    private String typeNm;
    // 제작 연도
    private String prdtYear;
    // 제작 상태
    private String prdtStatNm;
    // 영화 감독
    private List<DirectorDto> directors;
    // 제작사
    private List<MovieCompanyDto> companys;
}
