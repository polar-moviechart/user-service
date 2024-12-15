package com.polar_moviechart.userservice.domain.service.movie.dtos;

import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class MovieLikesRes {
    private Integer movieCode;
    private boolean likeStatus;

    @Builder
    public MovieLikesRes(Integer movieCode, boolean likeStatus) {
        this.movieCode = movieCode;
        this.likeStatus = likeStatus;
    }

    public static List<MovieLikesRes> listFrom(List<MovieLike> movieLikes) {
        return movieLikes.stream()
                .map(MovieLikesRes::from)
                .toList();
    }

    private static MovieLikesRes from(MovieLike movieLike) {
        return MovieLikesRes.builder()
                .movieCode(movieLike.getCode())
                .likeStatus(movieLike.getIsLike())
                .build();
    }
}
