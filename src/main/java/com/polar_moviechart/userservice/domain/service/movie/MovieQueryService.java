package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.controller.internalapi.dtos.UserMoviesLikeReq;
import com.polar_moviechart.userservice.domain.dtos.UserActivityInfo;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import com.polar_moviechart.userservice.domain.service.UserQueryService;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieLikesRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieRatingRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieReviewRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieLikeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public Page<MovieReviewRes> getReviews(Long userId, int code, PageRequest pageRequest) {
        return movieReviewQueryService.getReviews(userId, code, pageRequest);
    }

    public MovieLikeRes getLike(Long userId, int code) {
        return movieLikeQueryService.getLikeRes(userId, code);
    }

    public Page<MovieReviewRes> getUserMovieReviews(Long userId, PageRequest pageable) {
        return movieReviewQueryService.getUserMovieReviews(userId, pageable);
    }

    public Page<MovieLikeRes> getUserMovieLikes(Long userId, PageRequest pageable) {
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
        return like.getLikeStatus();
    }

    public Page<MovieLikesRes> getUserMoviesLike(UserMoviesLikeReq userMoviesLikeReq, PageRequest pageable) {
        return movieLikeQueryService.getUserMoviesLike(userMoviesLikeReq, pageable);
    }

    public Page<MovieRatingRes> getUserMoviesRating(UserMoviesLikeReq userMoviesLikeReq, PageRequest pageable) {
        return movieRatingQueryService.getUserMoviesRating(userMoviesLikeReq, pageable);
    }

    public UserActivityInfo getUserMovieActivity(Long userId, Integer code) {
        Boolean userMovieLike = movieLikeQueryService.getUserMovieLike(userId, code);
        Double userMovieRating = movieRatingQueryService.getUserMovieRating(code, userId);
        return new UserActivityInfo(userMovieLike, userMovieRating);
    }
}
