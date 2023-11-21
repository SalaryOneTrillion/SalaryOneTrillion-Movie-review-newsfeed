package com.sparta.salaryonetrillionmoviereviewnewsfeed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;


    @Column(name="movie_title",nullable = false,length = 30)
    private String movietitle;

    @Column(name="movie_derector", nullable = false,length = 20)
    private String moviederector;

    @Column(name = "movie_actor", nullable = false,length = 100)
    private String movieactor;

    @Column(name = "movie_release" ,nullable = false)
    private LocalDateTime movieRelease;


    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    @OneToMany(mappedBy = "movie")
    private List<moivecategory> moivecategories;

    @OneToMany(mappedBy = "movie")
    private List<reviewcomment> reviewcomments;




}
