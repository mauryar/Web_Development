package com.neu.edu.pojo;


	import java.util.HashSet;
	import java.util.Set;

	import javax.persistence.CascadeType;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
	import javax.persistence.OneToOne;
	import javax.persistence.PrimaryKeyJoinColumn;
	import javax.persistence.Table;
	import javax.persistence.UniqueConstraint;
	import javax.persistence.Column;
	import javax.persistence.JoinColumn;

	@Entity
	@Table(name="suppliertable", uniqueConstraints={
			@UniqueConstraint(columnNames = "company"),
			@UniqueConstraint(columnNames = "name")
	})
	@PrimaryKeyJoinColumn(name="personID")

	public class Supplier extends Person {

	    @Column(name="name", unique = true, nullable = false)
	   private String name;
	    
	    @Column(name="password")
	   private String password;
	    
	    @Column(name="email")
	   private String email;
	    @Column(name="address")
	   private String address;
	    @Column(name="company" , unique = true, nullable = false)
	   private String company;


	   public Supplier(String name, String password) {
	       this.name = name;
	       this.password = password;
	   }
	   // default constructor

	   Supplier() {
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


	    
	    private String cellPhone;
	    private String postalCode;


	
		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getCellPhone() {
			return cellPhone;
		}

		public void setCellPhone(String cellPhone) {
			this.cellPhone = cellPhone;
		}
	    
		
		
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinTable(name = "supplier_product", joinColumns = { 
				@JoinColumn(name = "personID", nullable = false, updatable = false) }, 
				inverseJoinColumns = { @JoinColumn(name = "productId", 
						nullable = false, updatable = false) })
	    private Set<Product> products = new HashSet<Product>();


		public Set<Product> getProducts() {
			return products;
		}

		public void setProducts(Set<Product> products) {
			this.products = products;
		}


		
	
	}
