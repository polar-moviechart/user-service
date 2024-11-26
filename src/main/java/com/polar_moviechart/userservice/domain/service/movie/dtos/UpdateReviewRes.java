package com.polar_moviechart.userservice.domain.service.movie.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateReviewRes {
    private Long reviewId;

    @Builder
    public UpdateReviewRes(Long reviewId) {
        this.reviewId = reviewId;
    }
}
