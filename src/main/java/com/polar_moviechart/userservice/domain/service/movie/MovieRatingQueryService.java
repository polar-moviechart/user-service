package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.MovieRating;
import com.polar_moviechart.userservice.domain.repository.movie.MovieRatingRepository;
import com.polar_moviechart.userservice.domain.service.MovieValidationService;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieRatingQueryService {
    private final MovieRatingRepository movieRatingRepository;
    private final MovieValidationService movieValidationService;

    public Double getUserMovieRating(int code, Long userId) {
        movieValidationService.validateMovieExists(code);
        return movieRatingRepository.findByCodeAndUserId(code, userId)
                .map(MovieRating::getRating)
                .orElseThrow(() -> new UserBusinessException(ErrorCode.RATING_NOT_EXISTS));
    }
}
