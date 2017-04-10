package com.neu.edu.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//this class is not required to be saved in the database
//@Entity
//@Table(name="login")
public class Login{
	
	//@Column(name="name")
    private String name;
	
	//@Column(name="password")
    private String password;
	
	
    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //default constructor
    Login() {
    }
    
    // getter setters required

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	
}