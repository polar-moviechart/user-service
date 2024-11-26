package com.polar_moviechart.userservice.domain.repository.movie;

import com.polar_moviechart.userservice.domain.MovieRatingTestConfig;
import com.polar_moviechart.userservice.domain.entity.movie.MovieRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieRatingRepositoryTest extends MovieRatingTestConfig {

    private List<Double> ratingValues = new ArrayList<>();

    @BeforeEach
    void setUp() {
        initUsers(1);
        for (int count = 0; count < 3; count++) {
            for (int i = 1; i <= 5; i++) {
                ratingValues.add((double) i);
            }
        }

        initRating(ratingValues, List.of(getUser(0)));
    }

    @DisplayName("특정 유저가 평점을 매긴 영화 목록을 조회할 수 있다.")
    @Test
    void getUserRatings() {
        // given
        Long userId = getUserId(0);
        int pageSize = 11;
        PageRequest pageable = PageRequest.of(0, pageSize);
        // when
        List<MovieRating> ratings = movieRatingRepository.findByUserId(userId, pageable);
        // then
        assertEquals(pageSize, ratings.size());
    }
}