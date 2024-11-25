package com.polar_moviechart.userservice.domain.controller.secureapi;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.AddReviewReq;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.entity.dto.MovieReviewRes;
import com.polar_moviechart.userservice.domain.service.*;
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

    private final MovieService movieService;

    @PostMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> updateRating(HttpServletRequest request,
                                                               @PathVariable(name = "code") int code,
                                                               @RequestBody UpdateRatingRequest updateRatingRequest) {
        String userIdString = request.getHeader("X-User-Id");
        Long userId = Long.valueOf(userIdString);

        double ratingValue = movieService.updateRating(code, userId, updateRatingRequest);

        return ok(new CustomResponse<>(ratingValue));
    }

    @GetMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> getMovieRating(HttpServletRequest request,
                                                                 @PathVariable(name = "code") int code) {
        Long userId = (Long) request.getAttribute("userId");
        Double movieRating = movieService.getUserMovieRating(code, userId);

        return ok(new CustomResponse<>(movieRating));
    }

    @PostMapping("/{code}/reviews")
    public ResponseEntity<CustomResponse<AddReviewRes>> addReview(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code,
            @Valid @RequestBody AddReviewReq req) {
        movieService.validateMovieExists(code);
        AddReviewRes addReviewRes = movieService.addReview(code, getUserId(servletRequest), req);

        return ok(new CustomResponse<>(addReviewRes));
    }

    @GetMapping("/{code}/reviews")
    public ResponseEntity<CustomResponse<MovieReviewRes>> getReview(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code) {

        MovieReviewRes res = movieService.getReview(getUserId(servletRequest), code);

        return ok(new CustomResponse<>(res));
    }

    private Long getUserId(HttpServletRequest servletRequest) {
        return Long.parseLong(servletRequest.getHeader("X-User-Id"));
    }
}
