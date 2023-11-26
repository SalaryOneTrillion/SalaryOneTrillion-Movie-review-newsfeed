package com.sparta.salaryonetrillionmoviereviewnewsfeed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.MovieDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.Movie;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.MovieRepository;
import java.util.HashMap;
import java.util.Map;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MovieAdminService {

    private final MovieRepository movieRepository;

    @Autowired
    private Environment environment;
    private final String apiSecretKey;

    public MovieAdminService(MovieRepository movieRepository, Environment environment) {
        this.movieRepository = movieRepository;
        this.environment = environment;
        this.apiSecretKey = environment.getProperty("admin.key");
    }
    public void updateMovies(String curPage, String itemPerPage, String openStartDt,
            String openEndDt) {

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("openStartDt", openStartDt);
        paramMap.put("openEndDt", openEndDt);
        paramMap.put("curPage", curPage);
        paramMap.put("itemPerPage", itemPerPage);

        try {

            KobisOpenAPIRestService service = new KobisOpenAPIRestService(apiSecretKey);
            String response = service.getMovieList(true, paramMap);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(response);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject movieListResult = (JSONObject) jsonObject.get("movieListResult");
            JSONArray movieArr = (JSONArray) movieListResult.get("movieList");

            ObjectMapper objectMapper = new ObjectMapper();
            for (int i = 0; i < movieArr.size(); i++) {
                Movie movie = new Movie();
                JSONObject movieJson = (JSONObject) movieArr.get(i);
                MovieDto movieDto = objectMapper.readValue(movieJson.toString(), MovieDto.class);

                if (movieRepository.findById(movieDto.getMovieCd()).isPresent()) {
                    continue;
                }

                if (!movieDto.getDirectors().isEmpty()) {
                    movie.setDirector(movieDto.getDirectors().get(0).getPeopleNm());
                } else {
                    movie.setDirector("감독 정보 없음");
                }
                movie.setTitle(movieDto.getMovieNm());
                movie.setGenre(movieDto.getGenreAlt());
                movie.setId(movieDto.getMovieCd());
                movie.setRelease_date(movieDto.getOpenDt());
                movie.setCountry(movieDto.getRepNationNm());
                movieRepository.save(movie);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
