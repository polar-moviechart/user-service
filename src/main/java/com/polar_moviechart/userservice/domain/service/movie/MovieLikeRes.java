package com.polar_moviechart.userservice.domain.service.movie;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MovieLikeRes {
    private Long userId;
    private Integer code;
    private Boolean isLike;

    @Builder
    public MovieLikeRes(Long userId, Integer code, Boolean isLike) {
        this.userId = userId;
        this.code = code;
        this.isLike = isLike;
    }
}
