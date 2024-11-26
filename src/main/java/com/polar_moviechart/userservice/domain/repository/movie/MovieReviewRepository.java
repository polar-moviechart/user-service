package com.polar_moviechart.userservice.domain.repository.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {
    List<MovieReview> findByUserId(Long userId, PageRequest pageable);

    Optional<MovieReview> findByUserIdAndCode(Long userId, int code);
}
