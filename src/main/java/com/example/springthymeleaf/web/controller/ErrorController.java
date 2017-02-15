package com.example.springthymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping(value = "404", method = RequestMethod.GET)
	public String get404() {
		return "error/404";
	}
	
	@RequestMapping(value = "500", method = RequestMethod.GET)
	public String get500() {
		return "error/500";
	}
}
