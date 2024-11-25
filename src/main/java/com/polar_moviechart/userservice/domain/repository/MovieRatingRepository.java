package com.polar_moviechart.userservice.domain.repository;

import com.polar_moviechart.userservice.domain.entity.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {
    Optional<MovieRating> findByCodeAndUserId(Integer code, Long userId);
}
