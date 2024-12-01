package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateMovieReviewReq;
import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateMovieLikeReq;
import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import com.polar_moviechart.userservice.domain.service.UserQueryService;
import com.polar_moviechart.userservice.domain.service.movie.dtos.UpdateReviewRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieLikeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieCommandService {

    private final MovieReviewCommandService movieReviewCommandService;
    private final MovieRatingCommandService movieRatingCommandService;
    private final MovieLikeCommandService movieLikeCommandService;
    private final UserQueryService userQueryService;


    @Transactional
    public double updateRating(int code, Long userId, UpdateRatingRequest updateRatingRequest) {
        return movieRatingCommandService.updateRating(code, getUser(userId), updateRatingRequest);
    }

    @Transactional
    public UpdateReviewRes updateReview(int code, Long userId, UpdateMovieReviewReq req) {
        return movieReviewCommandService.updateReview(code, getUser(userId), req);
    }

    @Transactional
    public MovieLikeRes updateLike(Long userId, int code, UpdateMovieLikeReq req) {
        MovieLike movieLike = movieLikeCommandService.updateLike(code, getUser(userId), req);

        return MovieLikeRes.from(movieLike);
    }

    private User getUser(Long userId) {
        return userQueryService.getKakaoUser(AuthType.KAKAO, userId);
    }
}
