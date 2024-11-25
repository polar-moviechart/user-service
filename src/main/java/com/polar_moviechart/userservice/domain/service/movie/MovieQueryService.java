package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.dto.MovieReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieQueryService {

    private final MovieReviewQueryService movieReviewQueryService;
    private final MovieRatingQueryService movieRatingQueryService;
    private final MovieLikeQueryService movieLikeQueryService;

    public Double getUserMovieRating(int code, Long userId) {
        return movieRatingQueryService.getUserMovieRating(code, userId);
    }

    public MovieReviewRes getReview(Long userId, int code) {
        return movieReviewQueryService.getReview(userId, code);
    }

    public MovieLikeRes getLike(Long userId, int code) {
        return movieLikeQueryService.getLikeRes(userId, code);
    }

    public List<MovieReviewRes> getUserMovieReviews(Long userId) {
        return movieReviewQueryService.getUserMovieReviews(userId);
    }
}
