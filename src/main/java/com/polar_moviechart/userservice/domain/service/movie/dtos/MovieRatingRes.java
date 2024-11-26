package com.polar_moviechart.userservice.domain.service.movie.dtos;

import com.polar_moviechart.userservice.domain.entity.movie.MovieRating;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MovieRatingRes {
    private double rating;

    @Builder
    public MovieRatingRes(double rating) {
        this.rating = rating;
    }

    public static List<MovieRatingRes> listFrom(List<MovieRating> movieRatings) {
        return movieRatings.stream()
                .map(MovieRatingRes::from)
                .toList();
    }

    private static MovieRatingRes from(MovieRating movieRating) {
        return MovieRatingRes.builder()
                .rating(movieRating.getRating())
                .build();
    }
}
