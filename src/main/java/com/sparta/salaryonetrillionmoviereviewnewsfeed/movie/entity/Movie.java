package com.sparta.salaryonetrillionmoviereviewnewsfeed.movie.entity;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.review.entity.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Movie {

    @Id
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String release_date;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private List<Review> reviews;

    public Long getReviewRatingAvg() {
        if(reviews.isEmpty()) {
            return 0L;
        }
        Long sum = 0L;
        for(Review review : reviews) {
            sum += review.getMovieRating();
        }
        sum = sum / reviews.size();
        return sum;
    }
}
