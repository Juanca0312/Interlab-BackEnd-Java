package com.acme.interlab;

import com.acme.interlab.model.User;
import com.acme.interlab.resource.UserResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUserByIdShouldReturnUser() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "api/users/1", User.class));
	}

	@Test
	public void getAllUsersShouldReturnUsers() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "api/users", User.class));
		//Page<UserResource> getAllUsers
	}
}
