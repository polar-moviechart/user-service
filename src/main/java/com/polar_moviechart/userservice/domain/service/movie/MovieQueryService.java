package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieRatingRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieReviewRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieLikeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    public List<MovieReviewRes> getUserMovieReviews(Long userId, PageRequest pageable) {
        return movieReviewQueryService.getUserMovieReviews(userId, pageable);
    }

    public List<MovieLikeRes> getUserMovieLikes(Long userId, PageRequest pageable) {
        return movieLikeQueryService.getUserMovieLikes(userId, pageable);
    }

    public List<MovieRatingRes> getUserMovieRatings(Long userId, PageRequest pageable) {
        return movieRatingQueryService.getUserMovieRatings(userId, pageable);
    }

    public Integer getMovieLikes(int code) {
        return movieLikeQueryService.getMovieLikes(code);
    }

    public Boolean getUserMovieLike(Long userId, Integer code) {
        MovieLike like = movieLikeQueryService.getLike(userId, code);
        return like.getIsLike();
    }
}
