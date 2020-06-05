package com.acme.interlab;

import static org.assertj.core.api.Assertions.assertThat;

import com.acme.interlab.controller.DocumentController;
import com.acme.interlab.controller.UserController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllersTest {

	@Autowired
	private UserController userController;

	@Autowired
	private DocumentController documentController;

	@Test
	public void contextLoadsUserController() throws Exception {
		assertThat(userController).isNotNull();
	}

	@Test
	public void contextLoadsDocumentController() throws Exception {
		assertThat(documentController).isNotNull();
	}
}
