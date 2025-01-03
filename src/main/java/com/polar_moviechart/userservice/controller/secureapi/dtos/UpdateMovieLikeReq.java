package com.polar_moviechart.userservice.controller.secureapi.dtos;

import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMovieLikeReq {
    private Boolean isLike;

    @Builder
    public UpdateMovieLikeReq(boolean isLike) {
        this.isLike = isLike;
    }

    public MovieLike toEntity(int code, User user) {
        return MovieLike.builder()
                .code(code)
                .userId(user.getId())
                .likeStatus(isLike)
                .build();
    }
}
