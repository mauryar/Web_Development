package com.neu.edu.controller;

import com.neu.edu.pojo.User;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

public class UserValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(User.class);
    }

    public void validate(Object obj, Errors errors)
    {
        User user = (User) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.user", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailId", "error.invalid.email.emailId", "Email Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.invalid.user", "Address Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postalCode", "error.invalid.user", "Postal Code Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellPhone", "error.invalid.user", "Cell Phone Number Required");
    }
}
