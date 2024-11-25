package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.entity.MovieRating;
import com.polar_moviechart.userservice.domain.repository.MovieRatingRepository;
import com.polar_moviechart.userservice.domain.service.MovieValidationService;
import com.polar_moviechart.userservice.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieRatingCommandService {

    private final UserQueryService userQueryService;
    private final MovieRatingRepository movieRatingRepository;
    private final MovieValidationService movieValidationService;

    @Transactional
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
