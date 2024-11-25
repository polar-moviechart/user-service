package com.polar_moviechart.userservice.domain;

import com.polar_moviechart.userservice.domain.entity.MovieRating;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.repository.movie.MovieRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class MovieRatingTestConfig extends UserTestConfig {

    @Autowired
    protected MovieRatingRepository movieRatingRepository;
    private List<Double> ratingValues;

    protected void initRating(List<Double> ratingValues, List<User> users) {
        this.ratingValues = ratingValues;
        setUpMovieRatings(users);
    }

    private void setUpMovieRatings(List<User> users) {
        for (User user : users) {
            for (int idx = 0; idx < ratingValues.size(); idx++) {
                movieRatingRepository.save(
                        MovieRating.builder()
                                .userId(user.getId())
                                .movieCode(idx + 1)
                                .rating(ratingValues.get(idx))
                                .build());
            }
        }
    }
}
