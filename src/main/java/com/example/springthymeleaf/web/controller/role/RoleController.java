package com.example.springthymeleaf.web.controller.role;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springthymeleaf.domain.Role;
import com.example.springthymeleaf.service.role.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@ModelAttribute("role")
	public Role emptyRole() {
	   return new Role();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("roles", roleService.findAll());
		return "role/index";
	}
	
	@RequestMapping(value= "register", method = RequestMethod.GET)
	public String getAddForm() {
		return "role/register";
	}
	
	@RequestMapping(value= "register", method = RequestMethod.POST)
	public String register(@ModelAttribute @Valid Role role, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "role/register";
		}
		
		roleService.save(role);
		
		return "redirect:/role";
	}
	
	@RequestMapping(value= "view/{userId}", method = RequestMethod.GET)
	public String view(@PathVariable("userId") Long roleId, Model model) {
		model.addAttribute("role", roleService.findOne(roleId));
		return "role/view";
	}
	
	@RequestMapping(value= "update", method = RequestMethod.POST)
	public String update(@ModelAttribute @Valid Role role, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user/view";
		}
		
		Role dbRole = roleService.findOne(role.getId());
		dbRole.setName(role.getName());
		dbRole.setDescription(role.getDescription());
		
		roleService.save(dbRole);
		
		return "redirect:/role";
	}
	
	@RequestMapping(value= "delete/{roleId}", method = RequestMethod.GET)
	public String delete(@PathVariable("roleId") Long roleId) {
		roleService.delete(roleId);
		
		return "redirect:/role";
	}
}
