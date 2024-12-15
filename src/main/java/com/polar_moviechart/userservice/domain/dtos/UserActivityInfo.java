package com.polar_moviechart.userservice.domain.dtos;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieReviewRes;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserActivityInfo {
    private Boolean likeStatus;
    private Double rating;

    @Builder
    public UserActivityInfo(Boolean likeStatus, Double rating) {
        this.likeStatus = likeStatus;
        this.rating = rating;
    }
}
