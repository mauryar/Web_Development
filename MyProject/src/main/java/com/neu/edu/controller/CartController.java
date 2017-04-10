package com.neu.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.pojo.Product;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/cart.htm")
public class CartController {

	
	
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request) {

		
		
		
		return "addUserForm";
	}
	
	
}
