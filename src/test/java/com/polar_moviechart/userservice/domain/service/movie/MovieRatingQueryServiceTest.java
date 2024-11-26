package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieRating;
import com.polar_moviechart.userservice.domain.repository.movie.MovieRatingRepository;
import com.polar_moviechart.userservice.domain.service.MovieValidationService;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class MovieRatingQueryServiceTest {
    @Mock
    private MovieValidationService movieValidationService;
    @InjectMocks
    private MovieRatingQueryService ratingQueryService;
    @Mock
    private MovieRatingRepository ratingRepository;

    private final double expectedRating = 4.5;
    private final int movieCode = 1;
    private final long userId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
    }

    @DisplayName("유저, 영화가 존재하면 영화 평점을 매길 수 있다.")
    @Test
    void validateUserExists_userExists_movieExists() {
        // given
        MovieRating movieRating = new MovieRating(userId, movieCode, expectedRating);

        doNothing()
                .when(movieValidationService)
                .validateMovieExists(movieCode);
        when(ratingRepository.findByCodeAndUserId(movieCode, userId))
                .thenReturn(Optional.of(movieRating));
        // when
        Double rating = ratingQueryService.getUserMovieRating(movieCode, userId);
        // then
        assertEquals(expectedRating, rating);
    }

    @DisplayName("유저가 존재하지 않으면 예외가 발생한다.")
    @Test
    void validateUserExists_userNotExists() {
        // given
        doNothing().when(movieValidationService).validateMovieExists(movieCode);
        when(ratingRepository.findByCodeAndUserId(movieCode, userId)).thenReturn(Optional.empty());
        // when then
        UserBusinessException exception = assertThrows(UserBusinessException.class,
                () -> ratingQueryService.getUserMovieRating(movieCode, userId)
        );
        assertEquals(ErrorCode.RATING_NOT_EXISTS.getCode(), exception.getCode());
    }

    @DisplayName("영화가 존재하지 않으면 예외가 발생한다.")
    @Test
    void validateUserExists_movieNotExists() {
        // given
        BDDMockito.doThrow(new UserBusinessException(ErrorCode.MOVIE_NOT_EXISTS))
                        .when(movieValidationService)
                        .validateMovieExists(movieCode);
        // when then
        UserBusinessException exception = assertThrows(UserBusinessException.class,
                () -> ratingQueryService.getUserMovieRating(movieCode, userId)
        );
        assertEquals("U103", exception.getCode());
    }
}
