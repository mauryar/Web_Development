package com.neu.edu.controller;

import com.neu.edu.pojo.Login;
import com.neu.edu.pojo.User;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

public class LoginValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Login.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Login login = (Login) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.login", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        
    }
}
