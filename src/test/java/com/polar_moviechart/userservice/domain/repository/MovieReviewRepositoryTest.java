package com.polar_moviechart.userservice.domain.repository;

import com.polar_moviechart.userservice.domain.MovieReviewTestConfig;
import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import com.polar_moviechart.userservice.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MovieReviewRepositoryTest extends MovieReviewTestConfig {

    private User user;

    @BeforeEach
    void setUp() {
        initUsers(1);
        this.user = getUsers().get(0);
        initMovieReviews(1, List.of(1), getUsers());
    }

    @DisplayName("유저 아이디로 영화 리뷰를 조회할 수 있다.")
    @Test
    void findByUserId() {
        // given // when
        List<MovieReview> movieReview = movieReviewRepository.findByUserId(user.getId());
        // then
        assertThat(movieReview.size()).isNotNull();
    }
}