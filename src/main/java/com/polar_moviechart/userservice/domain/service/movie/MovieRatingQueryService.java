package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieRating;
import com.polar_moviechart.userservice.repository.movie.MovieRatingRepository;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieRatingRes;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieRatingQueryService {
    private final MovieRatingRepository movieRatingRepository;

    public Double getUserMovieRating(int code, Long userId) {
        return movieRatingRepository.findByCodeAndUserId(code, userId)
                .map(MovieRating::getRating)
                .orElse(0.0);
    }

    public List<MovieRatingRes> getUserMovieRatings(Long userId, PageRequest pageable) {
        return MovieRatingRes.listFrom(
                movieRatingRepository.findByUserId(userId, pageable));
    }
}
