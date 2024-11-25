package com.polar_moviechart.userservice.domain.controller.secureapi;

import com.polar_moviechart.userservice.domain.service.AddReviewRes;
import com.polar_moviechart.userservice.domain.service.MovieRatingCommandService;
import com.polar_moviechart.userservice.domain.service.MovieReviewQueryService;
import com.polar_moviechart.userservice.domain.service.MovieValidationService;
import com.polar_moviechart.userservice.utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secure/api/v1/users/movies")
public class MovieControllerSecure {
    private final MovieReviewQueryService movieReviewQueryService;
    private final MovieValidationService movieValidationService;
    private final MovieRatingCommandService movieRatingCommandService;


    @PostMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> updateRating(HttpServletRequest request,
                                                               @PathVariable(name = "code") int code,
                                                               @RequestBody UpdateRatingRequest updateRatingRequest) {
        String userIdString = request.getHeader("X-User-Id");
        Long userId = Long.valueOf(userIdString);
        double ratingValue = movieRatingCommandService.updateRating(code, userId, updateRatingRequest);

        return ResponseEntity.ok(new CustomResponse<>(ratingValue));
    }

    @PostMapping("/{code}/reviews")
    public ResponseEntity addReview(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code,
            @Valid @RequestBody AddReviewReq req) {
        movieValidationService.validateMovieExists(code);
        AddReviewRes addReviewRes = movieReviewQueryService.addReview(code, getUserId(servletRequest), req);

        return ResponseEntity.ok(new CustomResponse<>(addReviewRes));
    }

    private Long getUserId(HttpServletRequest servletRequest) {
        return Long.parseLong(servletRequest.getHeader("X-User-Id"));
    }
}
