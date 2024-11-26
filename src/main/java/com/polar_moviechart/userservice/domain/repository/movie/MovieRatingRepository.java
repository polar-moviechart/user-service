package com.polar_moviechart.userservice.domain.repository.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieRating;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {
    Optional<MovieRating> findByCodeAndUserId(Integer code, Long userId);

    List<MovieRating> findByUserId(Long userId, PageRequest pageable);
}

