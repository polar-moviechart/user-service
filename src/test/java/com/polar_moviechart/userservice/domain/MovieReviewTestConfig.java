package com.polar_moviechart.userservice.domain;

import com.polar_moviechart.userservice.domain.entity.MovieReview;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.repository.MovieReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class MovieReviewTestConfig extends UserTestConfig {
    @Autowired
    protected MovieReviewRepository movieReviewRepository;
    private Integer reviewCnt;
    private List<Integer> movieCodes;

    protected void initMovieReviews(Integer reviewCnt) {
        this.reviewCnt = reviewCnt;
        List<User> users = getUsers();
        setUpMovieReviwes(users);
    }

    private void setUpMovieReviwes(List<User> users) {
        for (User user : users) {
            for (int i = 1; i <= reviewCnt; i++) {
                MovieReview movieReview = MovieReview.builder()
                        .code(i)
                        .content("review" + i)
                        .userId(user.getId())
                        .build();
                movieReviewRepository.save(movieReview);
            }
        }
    }
}
