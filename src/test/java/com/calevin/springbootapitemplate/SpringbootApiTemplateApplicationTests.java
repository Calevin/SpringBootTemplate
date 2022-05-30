package com.calevin.springbootapitemplate;

import com.calevin.springbootapitemplate.controllers.EntityExampleController;
import com.calevin.springbootapitemplate.repositories.CategoryExampleRepository;
import com.calevin.springbootapitemplate.repositories.EntityExampleRepository;
import com.calevin.springbootapitemplate.services.EntityExampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SpringbootApiTemplateApplicationTests {

	@Autowired
	private EntityExampleController entityExampleController;

	@Autowired
	private EntityExampleRepository entityExampleRepository;

	@Autowired
	private CategoryExampleRepository categoryExampleRepository;

	@Autowired
	private EntityExampleService entityExampleService;
	@Test
	void contextLoads() {
		assertThat(entityExampleController).isNotNull();
		assertThat(entityExampleRepository).isNotNull();
		assertThat(categoryExampleRepository).isNotNull();
		assertThat(entityExampleService).isNotNull();
	}

}
