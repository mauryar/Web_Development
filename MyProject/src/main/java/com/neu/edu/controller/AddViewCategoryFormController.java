package com.neu.edu.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.dao.CategoryDAO;
import com.neu.edu.pojo.Category;



@Controller
@RequestMapping("/addcategory.htm")
public class AddViewCategoryFormController
{
	
	
	@RequestMapping(method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("category")Category category,BindingResult result) throws Exception
    {
		
		
        
        try
        {
            CategoryDAO categoryDAO = new CategoryDAO();
            categoryDAO.create(category.getCategoryName());
            //DAO.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "addedCategory";
    }
    
	@RequestMapping(method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("category")Category category, BindingResult result) { 
   
        return "addCategoryForm"; 
    } 
}