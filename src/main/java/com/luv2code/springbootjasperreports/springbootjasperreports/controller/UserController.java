package com.luv2code.springbootjasperreports.springbootjasperreports.controller;

import java.io.FileNotFoundException;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springbootjasperreports.springbootjasperreports.model.User;
import com.luv2code.springbootjasperreports.springbootjasperreports.service.UserService;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	private List<User> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/export/{format}")
	private String exportReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return userService.exportReport(format);
	}
}
