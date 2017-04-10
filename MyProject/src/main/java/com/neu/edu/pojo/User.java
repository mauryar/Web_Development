package com.neu.edu.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity

@Table(name="usertable", uniqueConstraints={
		
		@UniqueConstraint(columnNames = "name")
})
@PrimaryKeyJoinColumn(name="personID")
public class User extends Person{
	
	@Column(name="name" , unique = true, nullable = false)
    private String name;
	
	@Column(name="password")
    private String password;
	
	@Column(name="address")
	private String address;
	
	@Column(name="postalCode")
	private String postalCode;

	@Column(name="cellPhone")
	private String cellPhone;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
    private Email email;
	

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //default constructor
    User() {
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

//    protected long getId() {
//        return id;
//    }
//
//    protected void setId(long id) {
//        this.id = id;
//    }

	public Email getEmail() {
		return email;
		
	}

	public void setEmail(Email email) {
		this.email = email;
	}
//	
//	public Person getPerson() {
//		return person;
//	}
//
//	public void setPerson(Person person) {
//		this.person = person;
//	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_product", joinColumns = { 
			@JoinColumn(name = "personID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "productId", 
					nullable = false, updatable = false) })
    private Set<Product> productsCart = new HashSet<Product>();


	public Set<Product> getProductsCart() {
		return productsCart;
	}

	public void setProductsCart(Set<Product> productsCart) {
		this.productsCart = productsCart;
	}
	
	
	
	
}