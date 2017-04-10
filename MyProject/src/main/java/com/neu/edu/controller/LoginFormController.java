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
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.edu.dao.SupplierDAO;
import com.neu.edu.dao.UserDAO;
import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Login;
import com.neu.edu.pojo.Supplier;
import com.neu.edu.pojo.User;


@Controller
@RequestMapping("/login.htm")
public class LoginFormController {
	@Autowired
	@Qualifier("loginValidator")
	LoginValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("login") Login login, BindingResult result, HttpServletRequest request) throws Exception {
		validator.validate(login, result);
		if (result.hasErrors()) {
			return "login";
		}

		try {
			System.out.print("test");
if(request.getSession().getAttribute("user") == null && request.getSession().getAttribute("supplier") == null){
			UserDAO userDao = new UserDAO();
			SupplierDAO supplierDao = new SupplierDAO();
			
			User user = userDao.search(login.getName(), login.getPassword());
		//	System.out.println("user role from user object: "+ user.getRole());
			Supplier supplier = supplierDao.search(login.getName(), login.getPassword());
			//System.out.println("supplier role from supplier object: "+ supplier.getRole());
			//System.out.println("supplier Id from supplier object: "+ supplier.getPersonID());
			if(user!= null && supplier==null && user.getRole().equalsIgnoreCase("User")){
				//request.setAttribute("user", user);
				request.getSession().setAttribute("user", user);
				return "userRole";
				
			}else if(supplier!=null && user == null && supplier.getRole().equalsIgnoreCase("Supplier")){
				request.setAttribute("supplier", supplier);
				request.getSession().setAttribute("supplier", supplier);
				return "supplierRole";
			}else{
				return "errorPage";
			}
		}else return "login";
			// DAO.close();
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "home";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("login") Login login, BindingResult result) {

		return "login";
	}
}