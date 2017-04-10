package com.neu.edu.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Category;

public class CategoryDAO extends DAO {

    public Category get(String categoryName) throws AdException {
        try {
            begin();
            Query q=getSession().createQuery("from Category where categoryName= :categoryName");
            q.setString("categoryName",categoryName);
            Category category=(Category)q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not obtain the named category " + categoryName + " " + e.getMessage());
        }
    }

    public List list() throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the categories", e);
        }
    }

    public Category create(String categoryName) throws AdException {
        try {
            begin();
            Category cat = new Category(categoryName);
            getSession().save(cat);
            commit();
            return null;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new AdException("Exception while creating category: " + e.getMessage());
        }
    }

    public void save(Category category) throws AdException {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the category", e);
        }
    }

    public void delete(Category category) throws AdException {
        try {
            begin();
            getSession().delete(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete the category", e);
        }
    }
}