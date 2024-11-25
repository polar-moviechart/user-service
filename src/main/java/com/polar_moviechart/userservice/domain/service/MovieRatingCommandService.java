package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.entity.MovieRating;
import com.polar_moviechart.userservice.domain.repository.MovieRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieRatingCommandService {

    private final UserQueryService userQueryService;
    private final MovieRatingRepository movieRatingRepository;
    private final MovieValidationService movieValidationService;

    public double updateRating(int code, Long userId, UpdateRatingRequest updateRatingRequest) {
        double ratingValue = updateRatingRequest.getRating();

        userQueryService.validateExist(userId);
        Optional<MovieRating> movieRatingOptional = movieRatingRepository.findByCodeAndUserId(code, userId);

        if (movieRatingOptional.isPresent()) {
            MovieRating movieRating = movieRatingOptional.get();
            movieRating.setRating(ratingValue);
        } else {
            movieValidationService.validateMovieExists(code);
            MovieRating movieRating = new MovieRating(userId, code, ratingValue);
            movieRatingRepository.save(movieRating);
        }
        return ratingValue;
    }
}
