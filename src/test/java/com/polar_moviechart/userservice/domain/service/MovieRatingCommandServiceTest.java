package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.MovieRatingTestConfig;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.entity.MovieRating;
import com.polar_moviechart.userservice.domain.repository.MovieRatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieRatingCommandServiceTest extends MovieRatingTestConfig {

    @Autowired
    private MovieRatingRepository movieRatingRepository;
    @Autowired
    private MovieRatingCommandService movieRatingCommandService;
    @MockBean
    private MovieValidationService movieValidationService;

    private final List<Integer> movieCodes = List.of(1);
    private final List<Double> ratingValues = List.of(1.0);


    @BeforeEach
    void setUp() {
        initUsers(2);
        initRating(ratingValues, List.of(getUser(1)));
        for (Integer movieCode : movieCodes) {
            BDDMockito.willDoNothing().given(movieValidationService).validateMovieExists(movieCode);
        }
    }

    @DisplayName("기존 평점이 없을 때 영화 평점을 매길 수 있다.")
    @Test
    void updateRatingTest_whenRatingDoesNotExists() {
        // given
        Long userId = getUserId(0);
        int movieCode = movieCodes.get(0);
        double ratingValue = 5.5;
        UpdateRatingRequest updateRatingRequest = new UpdateRatingRequest(ratingValue);

        // when
        movieRatingCommandService.updateRating(movieCode, userId, updateRatingRequest);
        // then
        Optional<MovieRating> savedRating = movieRatingRepository.findByCodeAndUserId(movieCode, userId);
        assertTrue(savedRating.isPresent());
        assertEquals(ratingValue, savedRating.get().getRating());
    }

    @DisplayName("기존 평점이 있을 때 새로운 평점을 매기면 기존의 평점이 업데이트 된다.")
    @Test
    void updateRatingTest_whenRatingExists() {
        // given
        Long userId = getUserId(1);

        MovieRating existingMovieRating = movieRatingRepository
                .findByCodeAndUserId(movieCodes.get(0), userId).get();
        Integer existingMovieCode = existingMovieRating.getCode();

        double newRatingValue = 2.0;
        UpdateRatingRequest updateRatingRequest = new UpdateRatingRequest(newRatingValue);
        // when
        movieRatingCommandService.updateRating(existingMovieCode, userId, updateRatingRequest);
        MovieRating updatedMovieRating = movieRatingRepository
                .findByCodeAndUserId(existingMovieCode, userId).get();
        // then
        assertEquals(existingMovieRating.getId(), updatedMovieRating.getId());
        assertEquals(newRatingValue, updatedMovieRating.getRating());
    }
}
