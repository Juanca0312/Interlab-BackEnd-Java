package com.acme.interlab;

import static org.assertj.core.api.Assertions.assertThat;

import com.acme.interlab.controller.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//Asserts that controllers were loaded correctly into the context
@SpringBootTest
public class ControllersTest {

	@Autowired
	private CompanyController companyController;

	@Autowired
	private DocumentController documentController;

	@Autowired
	private InternshipController internshipController;

	@Autowired
	private ProfileController profileController;

	@Autowired
	private QualificationController qualificationController;

	@Autowired
	private RequestController requestController;

	@Autowired
	private RequirementController requirementController;

	@Autowired
	private UserController userController;


	@Test
	public void contextLoadsCompanyController() throws Exception {
		assertThat(companyController).isNotNull();
	}

	@Test
	public void contextLoadsDocumentController() throws Exception {
		assertThat(documentController).isNotNull();
	}

	@Test
	public void contextLoadsInternshipController() throws Exception {
		assertThat(internshipController).isNotNull();
	}

	@Test
	public void contextLoadsProfileController() throws Exception {
		assertThat(profileController).isNotNull();
	}

	@Test
	public void contextLoadsQualificationController() throws Exception {
		assertThat(qualificationController).isNotNull();
	}

	@Test
	public void contextLoadsRequestController() throws Exception {
		assertThat(requestController).isNotNull();
	}

	@Test
	public void contextLoadsRequirementController() throws Exception {
		assertThat(requirementController).isNotNull();
	}

	@Test
	public void contextLoadsUserController() throws Exception {
		assertThat(userController).isNotNull();
	}
}
