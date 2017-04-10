<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        
    <title>Login</title>
</head>
<nav class="navbar navbar-static navbar-inverse navbar-fixed-top" >
  <div class="container-fluid">
  <ul class="nav navbar-nav">
  <li role="presentation"  class="active"><a href="adduser.htm">Register Customer</a></li>
  <li role="presentation"><a href="addsupplier.htm">Register Supplier</a></li>
  <li role="presentation"><a href="login.htm">Login</a></li>
    <li role="presentation"><a href="addcategory.htm">Admin</a></li>
      <li role="presentation"><a href="logoutPage.htm">Logout</a></li></ul></div></nav>
<body bgcolor = "pink">
</br></br></br></br>
<h2>Login</h2>

<form:form action="login.htm" commandName="login" method="post">

<table>


<tr>
    <td>User Name:</td>
    <td><form:input path="name" size="30" required="required"/> <font color="red"><form:errors path="name"/></font></td>
</tr>

<tr>
    <td>Password:</td>
    <td><form:password path="password" size="30" required="required"/> <font color="red"><form:errors path="password"/></font></td>
</tr>


<tr>
    <td colspan="2">
    <!-- <input type="submit" value="Submit" /> --></br>
    <button type="submit" class="btn btn-success">Submit</button>
    </td>
</tr>
</table>

</form:form>

</body>

</html>
