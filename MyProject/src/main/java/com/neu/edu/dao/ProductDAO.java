package com.neu.edu.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Product;
import com.neu.edu.pojo.Supplier;


public class ProductDAO extends DAO {

    public Product create(String productName, String productImage,String productDescription, String productPrice, String productQuantity, long categoryId, String categoryName)
            throws AdException {
        try {
            begin();
            Product product = new Product( productName,  productImage,  productDescription,productPrice,productQuantity,  categoryId,  categoryName);
            getSession().save(product);
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create product", e);
            throw new AdException("Exception while creating product: " + e.getMessage());
        }
    }

    
    public Product getProductrFromId(long productId)
            throws AdException { 
        try {
            begin();
            System.out.println("in getProductrFromId method");
            Query q = getSession().createQuery("from Product where productId = :productId");
            q.setLong("productId", productId);
            Product product = (Product) q.uniqueResult();
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + productId, e);
        }
    }
    
    
    public void delete(Product product)
            throws AdException {
        try {
            begin();
            getSession().delete(product);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete product", e);
        }
    }
    
    
    public  ArrayList<Product> search(String categoryName)
            throws AdException {
        try {
            begin();
            ArrayList<Product> productList = new ArrayList<Product>();
            System.out.println("in search product dao");
            Query q = getSession().createQuery("from Product where categoryName=:categoryName)");
            q.setString("categoryName", categoryName);
            
            productList = (ArrayList<Product>) q.list();
            System.out.println("got productlist"+productList.hashCode());
            commit();
           return productList;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get products for " + categoryName, e);
        }
    }
    
    public List list() throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Product");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the products", e);
        }
    }
    
    
}