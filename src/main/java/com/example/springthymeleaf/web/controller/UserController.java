package com.example.springthymeleaf.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springthymeleaf.domain.User;
import com.example.springthymeleaf.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User emptyUser() {
	   return new User();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user/index";
	}
	
	@RequestMapping(value= "register", method = RequestMethod.GET)
	public String getAddForm() {
		return "user/register";
	}
	
	@RequestMapping(value= "register", method = RequestMethod.POST)
	public String register(@ModelAttribute @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user/register";
		}
		
		userService.save(user);
		
		return "redirect:/user";
	}
	
	@RequestMapping(value= "view/{userId}", method = RequestMethod.GET)
	public String view(@PathVariable("userId") Long userId, Model model) {
		model.addAttribute("user", userService.findOne(userId));
		return "user/view";
	}
	
	@RequestMapping(value= "update", method = RequestMethod.POST)
	public String update(@ModelAttribute @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user/view";
		}
		
		userService.save(user);
		
		return "redirect:/user";
	}
	
	@RequestMapping(value= "delete/{userId}", method = RequestMethod.GET)
	public String delete(@PathVariable("userId") Long userId) {
		userService.delete(userId);
		
		return "redirect:/user";
	}
}
