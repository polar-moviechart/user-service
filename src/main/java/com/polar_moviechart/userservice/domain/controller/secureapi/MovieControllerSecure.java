package com.polar_moviechart.userservice.domain.controller.secureapi;

import com.polar_moviechart.userservice.domain.service.AddReviewRes;
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
