package com.polar_moviechart.userservice.domain.entity;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String password;

    @Column(nullable = false)
    private Long externalId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @OneToMany(mappedBy = "user")
    private List<MovieReview> movieReviews;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public User(String nickname, Long externalId, AuthType authType) {
        this.nickname = nickname;
        this.externalId = externalId;
        this.authType = authType;
    }

}
