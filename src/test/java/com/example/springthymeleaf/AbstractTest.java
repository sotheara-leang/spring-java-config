package com.example.springthymeleaf;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.springthymeleaf.conf.AppConfig;
import com.example.springthymeleaf.conf.MvcConfig;
import com.example.springthymeleaf.conf.SecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class, MvcConfig.class, SecurityConfig.class })
public class AbstractTest {

	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	protected Filter springSecurityFilterChain;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilters(springSecurityFilterChain).build();
	}
}
