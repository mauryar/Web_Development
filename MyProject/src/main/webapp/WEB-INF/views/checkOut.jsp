<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
     <!-- <script type="text/javascript">history.forward();</script> -->
<title>Checkout</title>
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
<body>
<% double total = (Double)session.getAttribute("cartTotal");
pageContext.setAttribute("total", total);
%>
</br></br></br></br>
<h1>Checkout was successful!!</h1>
<h2>Your Bill of $ ${total} are mailed to you at ${user.email.emailId}</h2>
<h3>Thank you for shopping with us!!!!!</h3>
<h4>Please Login again to continue shopping with us.. </h4>
</body>
</html>