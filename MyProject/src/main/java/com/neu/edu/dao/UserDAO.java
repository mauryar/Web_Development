package com.neu.edu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Email;
import com.neu.edu.pojo.Supplier;
import com.neu.edu.pojo.User;



public class UserDAO extends DAO {

    public UserDAO() {
    }

    public User get(String username)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from User where name = :username");
            q.setString("username", username);
            User user = (User) q.uniqueResult();
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + username, e);
        }
    }
    
    public void save(User user) throws AdException {
        try {
            begin();
            getSession().update(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the category", e);
        }
    }
    
    public User searchById(long personID)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from User where personID = :personID");
            q.setLong("personID", personID);
            
            User user = (User) q.uniqueResult();
            //String role = user.getRole();
            commit();
            
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + personID, e);
        }
    }
    
    public User search(String username, String password)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from User where name = :username and password = :password");
            q.setString("username", username);
            q.setString("password", password);
            User user = (User) q.uniqueResult();
            //String role = user.getRole();
            commit();
            
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + username, e);
        }
    }

    public User create(String username, String password,String emailId, String firstName, String lastName, String address, String postalCode, String cellPhone)
            throws AdException {
        try {
            begin();
            System.out.println("inside DAO");
            
            Email email=new Email(emailId);
            User user=new User(username,password);
            
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAddress(address);
            user.setEmail(email);
            user.setCellPhone(cellPhone);
            user.setPostalCode(postalCode);
            user.setRole("User");
            email.setUser(user);
            
            getSession().save(user);
            
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }

    public void delete(User user)
            throws AdException {
        try {
            begin();
            getSession().delete(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete user " + user.getName(), e);
        }
    }
}