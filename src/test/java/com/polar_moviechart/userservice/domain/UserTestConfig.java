package com.polar_moviechart.userservice.domain;

import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.repository.UserRepository;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public abstract class UserTestConfig {
    @Autowired
    protected UserRepository userRepository;

    private List<User> users = new ArrayList<>();
    private Integer userCnt;

    protected void initUsers(Integer userCnt) {
        this.userCnt = userCnt;
        setUpUsers();
    }

    private void setUpUsers() {
        for (int i = 1; i <= userCnt; i++) {
            User user = userRepository.save(
                    User.builder()
                            .nickname("user" + i)
                            .authType(AuthType.KAKAO)
                            .externalId((long) i)
                            .build()
            );
            users.add(user);
        }
    }

    public List<User> getUsers() {
        return new ArrayList<>(this.users);
    }

    public User getUser(int idx) {
        return this.users.get(idx);
    }

    public Long getUserId(int idx) {
        return getUsers().get(idx).getId();
    }
}
