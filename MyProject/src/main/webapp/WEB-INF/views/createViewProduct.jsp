<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.neu.edu.pojo.Supplier"%>
<%
//get the category list
        com.neu.edu.dao.CategoryDAO categorydao = new com.neu.edu.dao.CategoryDAO();
        java.util.List categoryList = categorydao.list();
        pageContext.setAttribute("categories", categoryList);
        Supplier supplier = (Supplier)request.getAttribute("supplier"); 
        long supplierId = supplier.getPersonID();
        String supplierName = supplier.getName();
        session.setAttribute("supplierId", supplierId);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        
        <title>Create/View Product</title>
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
<h4>Create Product <jsp:expression>supplierName</jsp:expression>!!</h4>
    
<form:form action="addproduct.htm" modelAttribute="product" method="post" enctype="multipart/form-data">

<table>

<tr>
                    <td>Select Category:</td>
                    <td>
                        <form:select path="categoryName">
                            <c:forEach var="categ" items="${categories}">
                                <form:option value="${categ.categoryName}"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>

<tr>
    <td>Product Name:</td>
    <td><form:input path="productName" size="30" required="required" /> <font color="red"><form:errors path="productName"/></font></td>
</tr>

<tr>
    <td>Product Description:</td>
    <td><form:input path="productDescription" size="30" required="required" /> <font color="red"><form:errors path="productDescription"/></font></td>
</tr>

<tr>
    <td>Product Price:</td>
    <td><form:input type="number" path="productPrice" size="30" required="required"/> <font color="red"><form:errors path="productPrice"/></font></td>
</tr>

<tr>
    <td>Product Quantity:</td>
    <td><form:input type="number" path="productQuantity" size="30"  required="required"/> <font color="red"><form:errors path="productQuantity"/></font></td>
</tr>

<%-- <tr>
    <td>Select photo (Max size: 5 MB)</td>
    <td><form:input path="productImage"  size="30" /> <font color="red"><form:errors path="productImage"/></font></td>
</tr> --%>

<tr><td><input required = "required" type="file" name="image" class="text-center center-block well well-sm"></td></tr>


<tr>
    <td colspan="2">
    <!-- <input type="submit" value="Create Product" /> --></br>
     <button type="submit" class="btn btn-success">Create Product</button>
    </td>
</tr>


</table>

</form:form>

</body>
</html>
