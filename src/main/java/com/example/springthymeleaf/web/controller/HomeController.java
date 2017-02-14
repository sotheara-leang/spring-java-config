package com.example.springthymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	// Mapp this method to handle get request to "/"
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		// index is the view name
		// thymeleaf will resolve it as /WEB/views/index.html. see MvcConfig
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
}
