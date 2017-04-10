package com.neu.edu.controller;

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
import com.neu.edu.pojo.Supplier;


@Controller
@RequestMapping("/addsupplier.htm")
public class AddSupplierFormController {
	@Autowired
	@Qualifier("supplierValidator")
	SupplierValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("supplier") Supplier supplier, BindingResult result) throws Exception {
		validator.validate(supplier, result);
		if (result.hasErrors()) {
			return "addSupplierForm";
		}

		try {
			
			com.neu.edu.dao.CategoryDAO categorydao = new com.neu.edu.dao.CategoryDAO();
	        java.util.List categoryList = categorydao.list();
	        
			System.out.print("test");
			SupplierDAO supplierDao = new SupplierDAO();
			UserDAO userDao = new UserDAO();
			System.out.print("test1");
			if(categoryList.size()!= 0 && supplierDao.get(supplier.getName())== null && userDao.get(supplier.getName())==null){
			supplierDao.create(supplier.getName(), supplier.getPassword(), supplier.getEmail(), supplier.getCompany(), supplier.getAddress(), supplier.getCellPhone(), supplier.getPostalCode());
			}else {return "home";}
			// DAO.close();
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "addedSupplier";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("supplier") Supplier supplier, BindingResult result) {

		return "addSupplierForm";
	}
}