package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.UserTestConfig;
import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateMovieLikeReq;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

class MovieLikeCommandServiceTest extends UserTestConfig {

    @Autowired MovieLikeCommandService movieLikeCommandService;

    private User user;

    @BeforeEach
    void setUp() {
        initUsers(1);
        this.user = getUser(0);
    }

    @DisplayName("영화 좋아요를 업데이트할 수 있다.")
    @Test
    void updateLike_when_like_notExists() {
        // given // when
        int movieCode = 1;
        UpdateMovieLikeReq req = UpdateMovieLikeReq.builder()
                .isLike(true).build();
        MovieLike movieLike = movieLikeCommandService.updateLike(movieCode, user, req);
        // then
        assertThat(movieLike.getIsLike()).isTrue();
    }

    @DisplayName("영화 좋아요 데이터가 있는 경우 업데이트를 할 수 있다.")
    @Test
    void updateLike_when_like_exists() {
        // given
        int movieCode = 1;
        UpdateMovieLikeReq firstReq = UpdateMovieLikeReq.builder()
                .isLike(true).build();
        movieLikeCommandService.updateLike(movieCode, user, firstReq);
        // when
        UpdateMovieLikeReq secondReq = UpdateMovieLikeReq.builder()
                .isLike(false).build();
        MovieLike movieLike = movieLikeCommandService.updateLike(movieCode, user, secondReq);
        // then
        assertThat(movieLike.getIsLike()).isFalse();
    }
}