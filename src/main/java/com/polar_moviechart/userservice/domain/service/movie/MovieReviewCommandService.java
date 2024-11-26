package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateMovieReviewReq;
import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.repository.movie.MovieReviewRepository;
import com.polar_moviechart.userservice.domain.service.movie.dtos.UpdateReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieReviewCommandService {
    private final MovieReviewRepository movieReviewRepository;

    @Transactional
    public UpdateReviewRes updateReview(int code, User user, UpdateMovieReviewReq req) {
        Optional<MovieReview> existingReview = movieReviewRepository.findByUserIdAndCode(user.getId(), code);
        MovieReview movieReview = existingReview
                .map(review -> {
                    review.setContent(req.getContent());
                    return review;
                })
                .orElseGet(() -> MovieReview.builder()
                        .code(code)
                        .userId(user.getId())
                        .content(req.getContent())
                        .build());
        movieReviewRepository.save(movieReview);
        return UpdateReviewRes.builder().reviewId(movieReview.getId()).build();
    }
}
