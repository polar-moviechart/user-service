package com.polar_moviechart.userservice.domain.service.movie.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddReviewRes {
    private Long reviewId;

    @Builder
    public AddReviewRes(Long reviewId) {
        this.reviewId = reviewId;
    }
}
