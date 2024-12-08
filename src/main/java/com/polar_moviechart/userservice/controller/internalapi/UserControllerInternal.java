package com.polar_moviechart.userservice.controller.internalapi;

import com.polar_moviechart.userservice.domain.service.UserQueryService;
import com.polar_moviechart.userservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.userservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/api/v1/users")
public class UserControllerInternal {

    private final UserQueryService userQueryService;
    private final MovieQueryService movieQueryService;

    @GetMapping("/{userId}")
    public ResponseEntity<CustomResponse<Boolean>> userExists(@PathVariable("userId") Long userId) {
        boolean isExists = userQueryService.isExists(userId);
        return ResponseEntity.ok(new CustomResponse(isExists));
    }

    @GetMapping("/{userId}/movies/{code}/like")
    public ResponseEntity<CustomResponse<Boolean>> userMovieLikeExists(
            @PathVariable("userId") Long userId,
            @PathVariable("code") Integer code) {
        Boolean userMovieLike = movieQueryService.getUserMovieLike(userId, code);
        return ResponseEntity.ok(new CustomResponse(userMovieLike));
    }

    @GetMapping("/{userId}/movies/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> getUserMovieRating(
            @PathVariable("userId") Long userId,
            @PathVariable("code") Integer code) {
        Double userMovieRating = movieQueryService.getUserMovieRating(code, userId);
        return ResponseEntity.ok(new CustomResponse(userMovieRating));
    }
}
