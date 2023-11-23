package com.sparta.salaryonetrillionmoviereviewnewsfeed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoty_id;

    @Column(name = "category_country", length = 30, nullable = false)
    private String category_country;

    @Column(name = "category_genre", length = 20, nullable = false)
    private String category_genre;

}