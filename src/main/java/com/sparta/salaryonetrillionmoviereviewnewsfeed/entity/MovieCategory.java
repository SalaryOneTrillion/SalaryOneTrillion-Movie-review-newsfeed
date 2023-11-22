package com.sparta.salaryonetrillionmoviereviewnewsfeed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class MovieCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_category_id", length=255, nullable = false)
    @NotEmpty
    private long movie_categoty_id;

    @NotEmpty
    @Column(name = "movie_id", length=255, nullable = false)
    private long movie_id;

    @NotEmpty
    @Column(name = "category_id", length=255, nullable = false)
    private long caterogy_id;

}
