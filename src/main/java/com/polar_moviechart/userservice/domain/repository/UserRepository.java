package com.polar_moviechart.userservice.domain.repository;

import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByAuthTypeAndExternalId(AuthType authType, Long kakaoId);

    Optional<User> findByAuthTypeAndUserId(AuthType authType, Long userId);
}
