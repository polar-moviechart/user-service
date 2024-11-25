package com.polar_moviechart.userservice.domain.controller.secureapi;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateMovieLikeReq;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.AddReviewReq;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.entity.dto.MovieReviewRes;
import com.polar_moviechart.userservice.domain.service.movie.MovieLikeRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.AddReviewRes;
import com.polar_moviechart.userservice.domain.service.movie.MovieCommandService;
import com.polar_moviechart.userservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.userservice.utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secure/api/v1/users/movies")
public class MovieControllerSecure {

    private final MovieQueryService movieQueryService;
    private final MovieCommandService movieCommandService;

    @PostMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> updateRating(HttpServletRequest servletRequest,
                                                               @PathVariable(name = "code") int code,
                                                               @RequestBody UpdateRatingRequest updateRatingRequest) {

        double ratingValue = movieCommandService.updateRating(code, getUserId(servletRequest), updateRatingRequest);

        return ok(new CustomResponse<>(ratingValue));
    }

    @GetMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> getMovieRating(HttpServletRequest request,
                                                                 @PathVariable(name = "code") int code) {
        Long userId = (Long) request.getAttribute("userId");
        Double movieRating = movieQueryService.getUserMovieRating(code, userId);

        return ok(new CustomResponse<>(movieRating));
    }

    @PostMapping("/{code}/reviews")
    public ResponseEntity<CustomResponse<AddReviewRes>> addReview(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code,
            @Valid @RequestBody AddReviewReq req) {
        AddReviewRes addReviewRes = movieCommandService.addReview(code, getUserId(servletRequest), req);

        return ok(new CustomResponse<>(addReviewRes));
    }

    @GetMapping("/{code}/reviews")
    public ResponseEntity<CustomResponse<MovieReviewRes>> getReview(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code) {

        MovieReviewRes res = movieQueryService.getReview(getUserId(servletRequest), code);

        return ok(new CustomResponse<>(res));
    }

    @PostMapping("/{code}/likes")
    public ResponseEntity<CustomResponse<MovieLikeRes>> updateLike(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code,
            @RequestBody UpdateMovieLikeReq req) {
        MovieLikeRes movieLikeRes = movieCommandService.updateLike(getUserId(servletRequest), code, req);

        return ok(new CustomResponse<>(movieLikeRes));
    }

    @GetMapping("/{code}/likes")
    public ResponseEntity<CustomResponse<MovieLikeRes>> getLike(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code) {
        MovieLikeRes movieLikeRes = movieQueryService.getLike(getUserId(servletRequest), code);

        return ok(new CustomResponse<>(movieLikeRes));
    }

    private Long getUserId(HttpServletRequest servletRequest) {
        return Long.parseLong(servletRequest.getHeader("X-User-Id"));
    }
}
