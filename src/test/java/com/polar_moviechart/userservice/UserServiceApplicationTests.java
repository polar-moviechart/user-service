package com.polar_moviechart.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@EnableJpaAuditing
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
