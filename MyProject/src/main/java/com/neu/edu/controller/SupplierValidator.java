package com.neu.edu.controller;

import com.neu.edu.pojo.Supplier;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

public class SupplierValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Supplier.class);
    }

    public void validate(Object obj, Errors errors)
    {
    	Supplier supplier = (Supplier) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "error.invalid.supplier", "Company Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.supplier", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.supplier", "Email Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.invalid.supplier", "Address Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postalCode", "error.invalid.supplier", "Postal Code Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellPhone", "error.invalid.supplier", "Cell Phone Number Required");
    }
}
