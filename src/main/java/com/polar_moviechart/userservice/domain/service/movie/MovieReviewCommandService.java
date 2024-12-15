package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateMovieReviewReq;
import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.repository.movie.MovieReviewRepository;
import com.polar_moviechart.userservice.domain.service.movie.dtos.UpdateReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieReviewCommandService {
    private final MovieReviewRepository movieReviewRepository;

    @Transactional
    public UpdateReviewRes addReview(int code, User user, UpdateMovieReviewReq req) {
        MovieReview movieReview = MovieReview.builder()
                .code(code)
                .user(user)
                .content(req.getContent())
                .build();
        movieReviewRepository.save(movieReview);
        return UpdateReviewRes.from(movieReview);
    }
}
