package com.polar_moviechart.userservice.domain.service.movie.dtos;

import lombok.Getter;

@Getter
public class UpdateRatingRes {
    private boolean isNew;
    private double rating;
    private Double oldRating;

    public UpdateRatingRes(boolean isNew, double rating, Double oldRating) {
        this.isNew = isNew;
        this.rating = rating;
        this.oldRating = oldRating;
    }
}
