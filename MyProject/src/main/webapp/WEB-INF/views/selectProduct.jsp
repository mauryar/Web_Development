<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.util.List"%>
<%@page import="com.neu.edu.pojo.Product"%>
<%@page import="com.neu.edu.pojo.User"%>
<%@page import="java.util.ArrayList"%>
<%
//get the category list
        com.neu.edu.dao.CategoryDAO categorydao = new com.neu.edu.dao.CategoryDAO();
        java.util.List categoryList = categorydao.list();
        pageContext.setAttribute("categories", categoryList);
       
        
%>
<!DOCTYPE html>
<html>
    <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- <script type="text/javascript">history.forward();</script> -->
        <title>View Product</title>
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
      <div class="container-fluid">

            <div class="row">

                <div align="left" class="col-sm-2">
                
    
<form:form action="getproduct.htm" commandName="product" method="post" >

<table>

<tr>
                    <td>Product Category:</td></tr>
                    <tr><td>
                        <form:label path="categoryName">
                            <c:forEach var="categ" items="${categories}">
                            <% /*   <form:option value="${categ.categoryName}"/> */ %>
  <a href="getproduct1.htm?categoryName=${categ.categoryName}">${categ.categoryName}</a></br>
                            </c:forEach>
                        </form:label>
                    </td></tr>
                
                
                <tr>
    <td colspan="2">
    <!--  <input type="submit" value="View Cart" />-->
  
    </td>
</tr>

</table>

</form:form>

</div>
                 <div class="col-sm-10">
                 
 
                     
                 <form action="getproduct2.htm" method="get">
                 
                 <c:forEach items="${productList}" var="item">
                           
                       </br>
<input type="checkbox" name="productId" class="group1" value=<c:out value="${item.productId}"></c:out> />
 ${item.productName}</br>
 <img src="resources/img/dashboard/${item.productImage}" width="150px" height ="150px"/>
 </br>${item.productDescription}</br>
${item.productPrice}</br>${item.productQuantity}</br>                       
                    </c:forEach>
                    
                 <%  if(request.getAttribute("user")!=null){
                 User user = (User)request.getAttribute("user");
        long userId = user.getPersonID();
        session.setAttribute("userId",userId );} %>
        
                     </br></br><button id="merge_button" type="submit" class="btn btn-success">Add to Cart</button>
                       
                     </form>
                     </br></br><h3>View Your Cart to Buy Products</h3>
                     <form action="getproduct3.htm" method="get"></br>
                     <button type="submit" class="btn btn-success">View Cart</button>
                     </form>
                 </div>
                
            </div>
        </div>

<!--  
<script>
$("#merge_button").click(function(event){
	  event.preventDefault();

	  var searchIDs = [];

	  var values = $('input:checkbox:checked.group1').map(function () {
		  return this.value;
		}).get(); 
	  searchIDs = values;
	  console.log(searchIDs);
	});
	

</script>
-->
</body>
</html>
