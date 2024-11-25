package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.AddReviewReq;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateRatingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieCommandService {

    private final MovieReviewCommandService movieReviewCommandService;
    private final MovieRatingCommandService movieRatingCommandService;

    @Transactional
    public double updateRating(int code, Long userId, UpdateRatingRequest updateRatingRequest) {
        return movieRatingCommandService.updateRating(code, userId, updateRatingRequest);
    }

    @Transactional
    public AddReviewRes addReview(int code, Long userId, AddReviewReq req) {
        return movieReviewCommandService.addReview(code, userId, req);
    }
}
