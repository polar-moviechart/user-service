package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.UserTestConfig;
import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateMovieLikeReq;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import com.polar_moviechart.userservice.repository.movie.MovieLikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieLikeQueryServiceTest extends UserTestConfig {

    @Autowired private MovieLikeQueryService movieLikeQueryService;
    @Autowired private MovieLikeRepository movieLikeRepository;

    @BeforeEach
    void setUp() {
        initUsers(1);
    }

    @DisplayName("user, code로 영화 좋아요 정보를 가져올 수 있다.")
    @Test
    void getLike_success() {
        // given
        User user = getUser(0);
        UpdateMovieLikeReq req = UpdateMovieLikeReq.builder()
                .isLike(true)
                .build();
        int code = 1;
        // when
        movieLikeRepository.save(req.toEntity(code, user));
        // then
        MovieLike movieLike = movieLikeQueryService.getLike(user.getId(), code);
        assertEquals(user.getId(), movieLike.getUserId());
    }

    @DisplayName("user로 영화 좋아요 정보를 가져올 수 있다.")
    @Test
    void getUserMovieLikes_success() {
        // given
        User user = getUser(0);
        UpdateMovieLikeReq req = UpdateMovieLikeReq.builder()
                .isLike(true)
                .build();
        List<Integer> movieCodes = List.of(1, 2);
        // when
        for (Integer movieCode : movieCodes) {
            movieLikeRepository.save(req.toEntity(movieCode, user));
        }
        // then
        PageRequest pageable = PageRequest.of(0, 10);
        assertEquals(2, movieLikeQueryService.getUserMovieLikes(user.getId(), pageable).size());
    }
}