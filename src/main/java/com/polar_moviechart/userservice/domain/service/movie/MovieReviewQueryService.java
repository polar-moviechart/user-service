package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieReviewRes;
import com.polar_moviechart.userservice.domain.repository.movie.MovieReviewRepository;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieReviewQueryService {
    private final MovieReviewRepository movieReviewRepository;

    public MovieReviewRes getReview(Long userId, int code) {
        Optional<MovieReview> reviewOptional = movieReviewRepository
                .findByUserIdAndCode(userId, code);
        MovieReview movieReview = reviewOptional.orElseThrow(() ->
                new UserBusinessException(ErrorCode.REVIEW_NOT_EXISTS));
        return movieReview.toDto();
    }

    public List<MovieReviewRes> getUserMovieReviews(Long userId) {
        List<MovieReview> movieReviews = movieReviewRepository.findByUserId(userId);
        if (movieReviews.isEmpty()) {
            throw new UserBusinessException(ErrorCode.REVIEW_NOT_EXISTS);
        }
        return MovieReviewRes.listFrom(movieReviews);
    }
}
