package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.AddReviewReq;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.entity.dto.MovieReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieReviewQueryService movieReviewQueryService;
    private final MovieReviewCommandService movieReviewCommandService;
    private final MovieValidationService movieValidationService;
    private final MovieRatingCommandService movieRatingCommandService;
    private final MovieRatingQueryService movieRatingQueryService;

    public double updateRating(int code, Long userId, UpdateRatingRequest updateRatingRequest) {
        return movieRatingCommandService.updateRating(code, userId, updateRatingRequest);
    }

    public Double getUserMovieRating(int code, Long userId) {
        return movieRatingQueryService.getUserMovieRating(code, userId);
    }

    public void validateMovieExists(int code) {
        movieValidationService.validateMovieExists(code);
    }

    public AddReviewRes addReview(int code, Long userId, AddReviewReq req) {
        return movieReviewCommandService.addReview(code, userId, req);
    }

    public MovieReviewRes getReview(Long userId, int code) {
        return movieReviewQueryService.getReview(userId, code);
    }
}
