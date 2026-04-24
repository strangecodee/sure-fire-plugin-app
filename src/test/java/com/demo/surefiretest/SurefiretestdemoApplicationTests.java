package com.demo.surefiretest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Default Spring Boot Application Test
 * 
 * This test verifies that the Spring application context loads correctly.
 * @SpringBootTest loads the full application context for integration testing.
 */
@SpringBootTest(classes = SurefiretestdemoApplication.class)
class SurefiretestdemoApplicationTests {

	@Test
	void contextLoads() {
		// This test ensures the Spring context loads without errors
	}

}
