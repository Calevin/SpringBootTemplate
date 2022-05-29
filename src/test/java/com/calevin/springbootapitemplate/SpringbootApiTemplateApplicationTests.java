package com.calevin.springbootapitemplate;

import com.calevin.springbootapitemplate.controllers.EntityExampleController;
import com.calevin.springbootapitemplate.repositories.CategoryExampleRepository;
import com.calevin.springbootapitemplate.repositories.EntityExampleRepository;
import com.calevin.springbootapitemplate.services.EntityExampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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
