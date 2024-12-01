package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.entity.movie.MovieRating;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.repository.movie.MovieRatingRepository;
import com.polar_moviechart.userservice.domain.service.MovieValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieRatingCommandService {
    private final MovieRatingRepository movieRatingRepository;
    private final MovieValidationService movieValidationService;

    @Transactional
    public double updateRating(int code, User user, UpdateRatingRequest updateRatingRequest) {
        double ratingValue = updateRatingRequest.getRating();

        Optional<MovieRating> movieRatingOptional = movieRatingRepository.findByCodeAndUserId(code, user.getId());

        if (movieRatingOptional.isPresent()) {
            MovieRating movieRating = movieRatingOptional.get();
            movieRating.setRating(ratingValue);
        } else {
            // TODO: movieValidationService.validateMovieExists(code); 삭제할 것
            movieValidationService.validateMovieExists(code);
            MovieRating movieRating = new MovieRating(user.getId(), code, ratingValue);
            movieRatingRepository.save(movieRating);
        }
        return ratingValue;
    }
}
