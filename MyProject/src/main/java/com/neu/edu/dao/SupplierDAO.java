package com.neu.edu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Supplier;
import com.neu.edu.pojo.User;




public class SupplierDAO extends DAO {

    public SupplierDAO() {
    }

    public Supplier get(String username)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Supplier where name = :username");
            q.setString("username", username);
            Supplier supplier = (Supplier) q.uniqueResult();
            commit();
            return supplier;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + username, e);
        }
    }
    
    public Supplier getSupplierFromId(long supplierId)
            throws AdException { 
        try {
            begin();
            Query q = getSession().createQuery("from Supplier where personID = :personID");
            q.setLong("personID", supplierId);
            Supplier supplier = (Supplier) q.uniqueResult();
            commit();
            return supplier;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + supplierId, e);
        }
    }
    
    public void save(Supplier supplier) throws AdException {
        try {
            begin();
            getSession().update(supplier);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the category", e);
        }
    }
    
    public Supplier search(String username, String password)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Supplier where name = :username and password = :password");
            q.setString("username", username);
            q.setString("password", password);
            Supplier supplier = (Supplier) q.uniqueResult();
            //String role = supplier.getRole();
            commit();
            return supplier;
        //    if(supplier != null){
          //      return supplier;}
            //    else return "Invalid Supplier";
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + username, e);
        }
    }

    public Supplier create(String username, String password,String email, String company, String address, String postalCode, String cellPhone)
            throws AdException {
        try {
            begin();
            System.out.println("inside DAO");
            
           
            Supplier supplier=new Supplier(username,password);
            
            supplier.setCompany(company);
            supplier.setEmail(email);
            supplier.setAddress(address);
            supplier.setPassword(password);
            supplier.setName(username);
            supplier.setCellPhone(cellPhone);
            supplier.setPostalCode(postalCode);
            supplier.setRole("Supplier");
            
            getSession().save(supplier);
            
            commit();
            return supplier;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }

    public void delete(Supplier supplier)
            throws AdException {
        try {
            begin();
            getSession().delete(supplier);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete user " + supplier.getName(), e);
        }
    }
}