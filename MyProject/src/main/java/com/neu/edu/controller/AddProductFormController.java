package com.neu.edu.controller;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.neu.edu.dao.CategoryDAO;
import com.neu.edu.dao.DAO;
import com.neu.edu.dao.ProductDAO;
import com.neu.edu.dao.SupplierDAO;
import com.neu.edu.dao.UserDAO;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Product;
import com.neu.edu.pojo.Supplier;



@Controller
@RequestMapping("/addproduct.htm")
public class AddProductFormController implements ServletContextAware {

	
	
	private ServletContext servletContext;

	@RequestMapping(method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("product")Product product,BindingResult result ,
    		@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request) throws Exception{

		HttpSession session = request.getSession();
		long supplierId  = (Long) session.getAttribute("supplierId");
		SupplierDAO supplierDao = new SupplierDAO();
		Supplier supplier = (Supplier)supplierDao.getSupplierFromId(supplierId);
		
		
		
		System.out.println("product controller reached");
		String productName = product.getProductName();   
		String productDescription = product.getProductDescription();   
//		String productImage = product.getProductImage();     
		String productPrice = product.getProductPrice();
		String productQuantity = product.getProductQuantity();
		
		System.out.println("Saving Image...");
		saveImage(product.getProductName() + ".jpg", image);
		product.setProductImage(product.getProductName() + ".jpg");
		
		
		
			
		
		
		
		
		String categoryName = product.getCategoryName();
		
		
        try {
            
            CategoryDAO categories = new CategoryDAO();
            ProductDAO products = new ProductDAO();

            
            Category category = categories.get(categoryName);

            
            System.out.println("Product going to create "+productName);
            Product prod = products.create(productName, product.getProductImage(),  productDescription, productPrice,productQuantity,  category.getCategoryId(), category.getCategoryName());
            System.out.println("Product created "+productName);
          
            category.addProduct(prod);
            categories.save(category);
            supplier.getProducts().add(prod);	
            supplierDao.save(supplier);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("before return addedProduct");
        return "addedProduct";
    }
    
	

	private void saveImage(String filename, MultipartFile productImg){
		try {
		File file = new File(servletContext.getRealPath("/")+"/resources/img/dashboard/"
		+ filename);	//Creates a new File
		 
		FileUtils.writeByteArrayToFile(file, productImg.getBytes());  //Transfers Byte by Byte to location
		System.out.println("Go to the location:  " + file.toString()
		+ " on your computer and verify that the image has been stored.");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
}



	@RequestMapping(method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("product")Product product, BindingResult result, HttpServletRequest request) { 
   HttpSession session = request.getSession();
		Supplier supplier = (Supplier)session.getAttribute("supplier");
   System.out.println("Reached get of add product form controller");
		System.out.println(supplier.getPersonID()+"value of supplierId");
		 request.setAttribute("supplier", supplier);
        return "createViewProduct"; 
    }

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	} 
}