package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.AddReviewReq;
import com.polar_moviechart.userservice.domain.entity.MovieReview;
import com.polar_moviechart.userservice.domain.repository.MovieReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieReviewCommandService {
    private final MovieReviewRepository movieReviewRepository;

    public AddReviewRes addReview(int code, Long userId, AddReviewReq req) {
        MovieReview movieReview = movieReviewRepository.save(
                MovieReview.builder()
                        .code(code)
                        .userId(userId)
                        .content(req.getContent())
                        .build());
        return AddReviewRes.builder().reviewId(movieReview.getId()).build();
    }
}
