package com.polar_moviechart.userservice.domain;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.repository.movie.MovieReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class MovieReviewTestConfig extends UserTestConfig {
    @Autowired
    protected MovieReviewRepository movieReviewRepository;
    private Integer reviewCnt;
    private List<Integer> movieCodes;

    protected void initMovieReviews(Integer reviewCnt, List<Integer> movieCodes, List<User> users) {
        this.reviewCnt = reviewCnt;
        this.movieCodes = movieCodes;
        setUpMovieReviews(users);
    }

    private void setUpMovieReviews(List<User> users) {
        for (Integer movieCode : movieCodes) {
            for (User user : users) {
                for (int i = 1; i <= reviewCnt; i++) {
                    MovieReview movieReview = MovieReview.builder()
                            .code(movieCode)
                            .content("review" + i)
                            .user(user)
                            .build();
                    movieReviewRepository.save(movieReview);
                }
            }
        }
    }
}
