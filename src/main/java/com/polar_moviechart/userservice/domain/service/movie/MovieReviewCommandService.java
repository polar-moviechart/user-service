package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.AddReviewReq;
import com.polar_moviechart.userservice.domain.entity.MovieReview;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.repository.movie.MovieReviewRepository;
import com.polar_moviechart.userservice.domain.service.movie.dtos.AddReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieReviewCommandService {
    private final MovieReviewRepository movieReviewRepository;

    @Transactional
    public AddReviewRes addReview(int code, User user, AddReviewReq req) {
        MovieReview movieReview = movieReviewRepository.save(
                MovieReview.builder()
                        .code(code)
                        .userId(user.getId())
                        .content(req.getContent())
                        .build());
        return AddReviewRes.builder().reviewId(movieReview.getId()).build();
    }
}
