package com.example.springthymeleaf;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class TestAuth extends AbstractTest {

	@Test
	public void testAccessSecuredPage() throws Exception {
		mockMvc.perform(get("/user"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(unauthenticated())
			.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	public void testWithValidData() throws Exception {
		mockMvc.perform(
					post("/login")
						.param("username", "admin")
						.param("password", "123")

				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(authenticated())
				.andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void testWithInvalidData() throws Exception {
		mockMvc.perform(
					post("/login")
					.param("username", "admin")
					.param("password", "1234")
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(unauthenticated())
				.andExpect(redirectedUrl("/login"));
	}
}
