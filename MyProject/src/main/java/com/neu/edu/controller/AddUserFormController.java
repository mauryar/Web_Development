package com.neu.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.dao.SupplierDAO;
import com.neu.edu.dao.UserDAO;
import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.User;


@Controller
@RequestMapping("/adduser.htm")
public class AddUserFormController {
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest req) throws Exception {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "addUserForm";
		}

		try {
			System.out.print("test");

			com.neu.edu.dao.CategoryDAO categorydao = new com.neu.edu.dao.CategoryDAO();
	        java.util.List categoryList = categorydao.list();
	        
	        com.neu.edu.dao.ProductDAO productDAO = new com.neu.edu.dao.ProductDAO();
	        java.util.List productList = productDAO.list();
	        
			UserDAO userDao = new UserDAO();
			SupplierDAO supplierDao = new SupplierDAO();
			System.out.print("test1");
			//req.getParameter("name");
			//System.out.println("Username given by user is:"+req.getParameter("name"));
			System.out.println("Username given by user is:"+user.getName());
			if(categoryList.size()!= 0 && productList.size()!=0 && userDao.get(user.getName())== null && supplierDao.get(user.getName())==null){
userDao.create(user.getName(), user.getPassword(), user.getEmail().getEmailId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getCellPhone(), user.getPostalCode());
			}else return "home";			
			// DAO.close();
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "addedUser";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result) {

		return "addUserForm";
		//return "index";
	}
}