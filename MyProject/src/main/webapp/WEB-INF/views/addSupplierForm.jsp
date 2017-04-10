<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        
    <title>Add Supplier Form</title>
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
<h2>Register a New Supplier</h2>

<form:form action="addsupplier.htm" commandName="supplier" method="post">

<table>
<tr>
    <td>Supplier Name:</td>
    <td><form:input path="company" size="30" required="required" /> <font color="red"><form:errors path="company"/></font></td>
</tr>


<tr>
    <td>User Name:</td>
    <td><form:input path="name" size="30" required="required" /> <font color="red"><form:errors path="name"/></font></td>
</tr>

<tr>
    <td>Password:</td>
    <td><form:password path="password" size="30" required="required" /> <font color="red"><form:errors path="password"/></font></td>
</tr>

 <tr>
    <td>Email Id:</td>
    <td><form:input type="email" path="email" size="30"  required="required"/> <font color="red"><form:errors path="email"/></font></td>
</tr> 
<tr>
    <td>Cell Phone:</td>
    <td><form:input type="number" path="cellPhone" size="30" required="required"/> <font color="red"><form:errors path="cellPhone"/></font></td>
</tr>
<tr>
    <td>Address:</td>
    <td><form:input path="address" size="30" required="required"/> <font color="red"><form:errors path="address"/></font></td>
</tr>
<tr>
    <td>Postal Code:</td>
    <td><form:input type="number" path="postalCode" size="30" required="required"/> <font color="red"><form:errors path="postalCode"/></font></td>
</tr>


<tr>
    <td colspan="2">
    <!-- <input type="submit" value="Create Supplier" /> --></br>
    <button type="submit" class="btn btn-success">Create Supplier</button>
    
    </td>
</tr>
</table>

</form:form>

</body>
</html>