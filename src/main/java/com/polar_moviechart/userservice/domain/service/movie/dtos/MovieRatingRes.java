package com.polar_moviechart.userservice.domain.service.movie.dtos;

import com.polar_moviechart.userservice.domain.entity.movie.MovieRating;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MovieRatingRes {
    private Integer movieCode;
    private double movieRating;
    private LocalDateTime createdAt;

    @Builder
    public MovieRatingRes(Integer movieCode, double movieRating, LocalDateTime createdAt) {
        this.movieCode = movieCode;
        this.movieRating = movieRating;
        this.createdAt = createdAt;
    }

    public static List<MovieRatingRes> listFrom(List<MovieRating> movieRatings) {
        return movieRatings.stream()
                .map(MovieRatingRes::from)
                .toList();
    }

    private static MovieRatingRes from(MovieRating movieRating) {
        return MovieRatingRes.builder()
                .movieRating(movieRating.getRating())
                .movieCode(movieRating.getCode())
                .createdAt(movieRating.getCreatedAt())
                .build();
    }
}
