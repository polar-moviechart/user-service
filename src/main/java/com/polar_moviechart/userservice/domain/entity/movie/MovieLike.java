package com.polar_moviechart.userservice.domain.entity.movie;

import com.polar_moviechart.userservice.domain.service.movie.UpdateMovieLikeRes;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "movie_likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MovieLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "code", nullable = false)
    private Integer code;

    @Column(name = "isLike", nullable = false)
    private Boolean isLike;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public MovieLike(Long userId, Integer code, Boolean isLike) {
        this.userId = userId;
        this.code = code;
        this.isLike = isLike;
    }

    public void setIsLike(Boolean isLike) {
        this.isLike = isLike;
    }

    public UpdateMovieLikeRes toDto() {
        return UpdateMovieLikeRes.builder()
                .userId(userId)
                .code(code)
                .isLike(isLike)
                .build();
    }
}
