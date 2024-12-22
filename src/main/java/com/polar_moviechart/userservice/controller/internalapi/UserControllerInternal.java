package com.polar_moviechart.userservice.controller.internalapi;

import com.polar_moviechart.userservice.controller.internalapi.dtos.UserMoviesLikeReq;
import com.polar_moviechart.userservice.domain.dtos.Category;
import com.polar_moviechart.userservice.domain.dtos.UserActivityInfo;
import com.polar_moviechart.userservice.domain.service.UserQueryService;
import com.polar_moviechart.userservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieLikesRes;
import com.polar_moviechart.userservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @GetMapping("/{userId}/movies/{code}/likes")
    public ResponseEntity<CustomResponse<Boolean>> userMovieLikeExists(
            @PathVariable("userId") Long userId,
            @PathVariable("code") Integer code) {
        Boolean userMovieLike = movieQueryService.getUserMovieLike(userId, code);
        return ResponseEntity.ok(new CustomResponse(userMovieLike));
    }

    @PostMapping("/movies/likes")
    public ResponseEntity<Page<MovieLikesRes>> getUserMoviesLike(
            @RequestBody UserMoviesLikeReq userMoviesLikeReq,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<MovieLikesRes> userMoviesLike = movieQueryService.getUserMoviesLike(userMoviesLikeReq, pageable);

        return ResponseEntity.ok(userMoviesLike);
    }

    @GetMapping("/{userId}/movies/{code}/ratings")
    public ResponseEntity<CustomResponse<Boolean>> getUserMovieRating(
            @PathVariable("userId") Long userId,
            @PathVariable("code") Integer code) {
        Double userMovieRating = movieQueryService.getUserMovieRating(code, userId);
        return ResponseEntity.ok(new CustomResponse(userMovieRating));
    }

    @GetMapping("/{category}/{userId}/activities/{code}")
    public ResponseEntity<CustomResponse<UserActivityInfo>> getUserActivity(
            @PathVariable("category") Category category,
            @PathVariable("userId") Long userId,
            @PathVariable("code") Integer code) {
        userQueryService.getUser(userId);
        if (category == Category.MOVIE) {
            UserActivityInfo userMovieActivity = movieQueryService.getUserMovieActivity(userId, code);
            return ResponseEntity.ok(new CustomResponse(userMovieActivity));
        } else {
            throw new IllegalArgumentException("준비중 입니다.");
        }
    }
}
