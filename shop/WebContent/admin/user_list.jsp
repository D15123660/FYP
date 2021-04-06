<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Customer list</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container">

	<%@include file="header.jsp"%>
	
	<div class="text-right"><a class="btn btn-warning" href="userAdd">Add customer</a></div>
		
	<br>
	
	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="10%">Username</th>
		<th width="10%">Phone</th>
		<th width="10%">Address</th>
		<th width="10%">Operation</th>
	</tr>
	
	<c:forEach var="user" items="${userList}">
         <tr>
         	<td><p>${user.id}</p></td>
         	<td><p>${user.username}</p></td>
         	<td><p>${user.phone}</p></td>
         	<td><p>${user.address}</p></td>
			<td>
				<a class="btn btn-info" href="userRe?id=${user.id}">Reset Password</a>
				<a class="btn btn-primary" href="userEdit?id=${user.id}">Submit</a>
				<a class="btn btn-danger" href="userDelete?id=${user.id}">Delete</a>
			</td>
       	</tr>
     </c:forEach>
     
</table>

<br>${pageTool}<br>
</div>
</body>
</html>