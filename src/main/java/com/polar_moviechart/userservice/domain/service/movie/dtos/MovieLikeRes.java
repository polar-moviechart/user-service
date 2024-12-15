package com.polar_moviechart.userservice.domain.service.movie.dtos;

import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MovieLikeRes implements UserActionRes {
    private Long userId;
    private String title;
    private Integer code;
    private Boolean isLike;

    @Builder
    public MovieLikeRes(Long userId, Integer code, Boolean isLike) {
        this.userId = userId;
        this.code = code;
        this.isLike = isLike;
    }

    public static MovieLikeRes from(MovieLike movieLike) {
        return MovieLikeRes.builder()
                .userId(movieLike.getUserId())
                .code(movieLike.getCode())
                .isLike(movieLike.getIsLike())
                .build();
    }

    public static List<MovieLikeRes> listFrom(List<MovieLike> movieLikes) {
        return movieLikes.stream()
                .map(MovieLikeRes::from)
                .toList();
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
