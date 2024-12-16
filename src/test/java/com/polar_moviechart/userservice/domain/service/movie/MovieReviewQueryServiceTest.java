package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.MovieReviewTestConfig;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieReviewRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieReviewQueryServiceTest extends MovieReviewTestConfig {

    @Autowired MovieReviewQueryService movieReviewQueryService;
    private int reviewCnt = 2;

    @BeforeEach
    void setUp() {
        initUsers(1);
        initMovieReviews(reviewCnt, List.of(1), getUsers());
    }

    @DisplayName("유저가 작성한 리뷰를 조회할 수 있다.")
    @Test
    void getUserMovieReviews_successCase() {
        // given // when
        PageRequest pageable = PageRequest.of(0, 10);
        List<MovieReviewRes> userMovieReviews = movieReviewQueryService.getUserMovieReviews(getUserId(0), pageable).getContent();
        // then
        assertEquals(reviewCnt, userMovieReviews.size());
    }
}