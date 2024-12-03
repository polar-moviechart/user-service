package com.polar_moviechart.userservice.controller.secureapi.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRatingRequest {
    private Double rating;

    @Builder
    public UpdateRatingRequest(Double rating) {
        this.rating = rating;
    }
}
