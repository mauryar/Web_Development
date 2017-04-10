package com.neu.edu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neu.edu.dao.CategoryDAO;
import com.neu.edu.dao.ProductDAO;
import com.neu.edu.dao.UserDAO;
import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Product;
import com.neu.edu.pojo.User;

@Controller
// @RequestMapping("/getproduct.htm")
public class UserAddViewProductController {

	@Autowired
	   JavaMailSender javaMailSender;
	   
	   
	   public JavaMailSender getJavaMailSender() {
	       return javaMailSender;
	   }

	   public void setJavaMailSender(JavaMailSender javaMailSender) {
	       this.javaMailSender = javaMailSender;
	   }

	   
	   private SimpleMailMessage message;
	
	@RequestMapping(value = "/checkOut.htm", method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("product")Product product,BindingResult result ,
    		 HttpServletRequest request) throws Exception{

		
		HttpSession session = request.getSession();
		System.out.println("entered post method of useraddviewproductcont"+session.getAttribute("userEmailId"));
		String sentToEmailId = (String)session.getAttribute("userEmailId");
		double total = (Double)session.getAttribute("cartTotal");
		message = new SimpleMailMessage();
        
        
        message.setFrom("mauryarajani89@gmail.com");
        message.setTo(sentToEmailId);
        System.out.println("email id is: "+(String)session.getAttribute("userEmailId"));
        message.setSubject("Billing Details");
        message.setText("Your Bill for shopping at our site is: "+total);
        getJavaMailSender().send(message);
		
		
		
		return "checkOut";
	}
	String categoryName = "";

	// @RequestMapping(value ="/fetchproduct.htm" ,method=RequestMethod.GET )
	// public @ResponseBody ArrayList<Product>
	// fetchProduct(@ModelAttribute("product")Product product,BindingResult
	// result,@PathVariable("categoryName") String categoryName) throws
	// Exception{

	@RequestMapping(value = "/getproduct.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request) {

		HttpSession session = request.getSession();
User user = (User)session.getAttribute("user");
if(user != null){
request.setAttribute("user", user);

		return "selectProduct";}
else return "logoutPage";
	}

	@RequestMapping(value = "/getproduct1.htm", method = RequestMethod.GET)
	public String initializeForm1(@ModelAttribute("product") Product product, BindingResult result,
			HttpServletRequest as) {
		HttpSession session = as.getSession();
		User user = (User)session.getAttribute("user");
		if(user != null){
		categoryName = as.getParameter("categoryName");
		System.out.println(as.getParameter("categoryName") + "value of got it");
		// return "selectProduct";
		ProductDAO productDao = new ProductDAO();
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			productList = productDao.search(categoryName);
			System.out.println("after get product");
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		as.setAttribute("productList", productList);
		return "selectProduct";}else return "logoutPage"; 
	}

	@RequestMapping(value = "/getproduct2.htm", method = RequestMethod.GET)
	public String initializeForm2(@ModelAttribute("product") Product product, BindingResult result,
			HttpServletRequest req) throws AdException {
		HttpSession session = req.getSession();
		User user1 = (User)session.getAttribute("user");
		if(user1 != null){
		long userId  = (Long) session.getAttribute("userId");
		UserDAO userDao = new UserDAO();
		User user = (User)userDao.searchById(userId);
		System.out.println("entered getproduct2.htm ");
		String[] productIdList= req.getParameterValues("productId");
			
		if(productIdList != null){
		for (String prodId : productIdList) {
		    	System.out.println("product id of checked checkbox is: "+prodId);	
		    	System.out.println("product id of checked checkbox is: "+Long.parseLong(prodId));
		    	ProductDAO productDao = new ProductDAO();
		    	try {
					Product prod = (Product)productDao.getProductrFromId(Long.parseLong(prodId));
				System.out.println("reached useraddviewproductcontroller : before user.getProductsCart().add(prod)");
				System.out.println("get something from user "+user.getFirstName());
				user.getProductsCart().add(prod);
					System.out.println(user.getProductsCart().add(prod));
				userDao.save(user);
		    	} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		}else return "NoProductSelected";
		System.out.println("retrieve the total of product");
		double total = 0;
		Iterator iterator = user.getProductsCart().iterator();
		while (iterator.hasNext())
        { 
             Product prod=(Product)iterator.next();
             total = total + Long.parseLong(prod.getProductPrice());
             System.out.println("prod.getProductPrice() is the: "+Long.parseLong(prod.getProductPrice()));
        }
		req.setAttribute("userProductCart", user.getProductsCart());
		req.setAttribute("total", total);
		session.setAttribute("cartTotal", total);
		if(total<1){return "NoProductSelected";}else
		return "viewCart";
		}else{return "logoutPage";}
	}

	
	@RequestMapping(value = "/getproduct3.htm", method = RequestMethod.GET)
	public String initializeForm3(@ModelAttribute("product") Product product, BindingResult result,
			HttpServletRequest req) throws AdException{
	
		HttpSession session = req.getSession();
		User user1 = (User)session.getAttribute("user");
		if(user1 != null){
		long userId  = (Long) session.getAttribute("userId");
		UserDAO userDao = new UserDAO();
		User user = (User)userDao.searchById(userId);
		System.out.println("entered getproduct3.htm ");
		req.setAttribute("userProductCart", user.getProductsCart());
		session.setAttribute("userEmailId", user.getEmail().getEmailId());
		//System.out.println("before if:total from viewfinalcart mrthod is: "+(Double)session.getAttribute("cartTotal"));

		if((Double)session.getAttribute("cartTotal")!=null && (Double)session.getAttribute("cartTotal")>0){
			System.out.println("total from viewfinalcart mrthod is: "+(Double)session.getAttribute("cartTotal"));
		return "viewFinalCart";
		}else 
		{return "NoProductSelected";}
		
		}else{return "logoutPage";}
	}
	
	@RequestMapping(value = "/logoutPage.htm", method = RequestMethod.GET)
	public String logoutMethod(	HttpServletRequest req, HttpServletResponse res) throws AdException{
		if(req.getAttribute("user")!=null || req.getSession().getAttribute("user")!= null || req.getAttribute("supplier")!=null || req.getSession().getAttribute("supplier")!=null){
		req.getSession().invalidate();
		return "logoutPage";
		}else { return "noUserLogout";}
	}
	
	
	
}