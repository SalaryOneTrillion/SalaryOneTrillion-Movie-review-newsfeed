package com.sparta.salaryonetrillionmoviereviewnewsfeed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    public String getReviewRatingAvg() {
        if(reviews.isEmpty()) {
            return "0";
        }
        float sum = 0L;
        for(Review review : reviews) {
            sum += review.getMovieRating();
        }
        sum = sum / reviews.size();
        return String.format("%.2f", sum);
    }
}
