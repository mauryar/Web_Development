<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.util.List"%>
<%@page import="com.neu.edu.pojo.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
     
        <title>User Role</title>
    </head>
    <nav class="navbar navbar-static navbar-inverse navbar-fixed-top" >
  <div class="container-fluid">
  <ul class="nav navbar-nav">
  <li role="presentation"  class="active"><a href="adduser.htm">Register Customer</a></li>
  <li role="presentation"><a href="addsupplier.htm">Register Supplier</a></li>
  <li role="presentation"><a href="login.htm">Login</a></li>
    <li role="presentation"><a href="addcategory.htm">Admin</a></li>
      <li role="presentation"><a href="logoutPage.htm">Logout</a></li>
      </ul></div></nav>
    <body bgcolor = "pink">
    </br></br></br></br>
     <% User user = (User)session.getAttribute("user"); 
 long userId = user.getPersonID();
 String userName = user.getName();
 
 %>

 <h4>Welcome <jsp:expression>userName</jsp:expression>!!</h4>
 <%
session.setAttribute("user", user);
%>
        <a href="getproduct.htm" >Start Shopping Here!!!!!</a><br>
        
    </body>
</html>