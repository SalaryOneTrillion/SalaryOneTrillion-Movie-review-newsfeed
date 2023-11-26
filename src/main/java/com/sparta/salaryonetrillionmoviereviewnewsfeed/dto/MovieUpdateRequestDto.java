package com.sparta.salaryonetrillionmoviereviewnewsfeed.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
public class MovieUpdateRequestDto {

    String curPage;

    String itemPerPage;

    String openStartDt;

    String openEndDt;

}
