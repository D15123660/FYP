<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Category list</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container">

	<%@include file="header.jsp"%>
	
	<div class="text-right"><a class="btn btn-warning" href="typeAdd">Add Category</a></div>
	
	<br>

	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="10%">Name</th>
		<th width="10%">Image</th>
		<th width="5%">Number</th>
		<th width="10%">Operation</th>
	</tr>
	
	<c:forEach var="type" items="${typeList}">
         <tr>
         	<td><p>${type.id}</p></td>
         	<td><p>${type.name}</p></td>
         	<td><p><img src="${type.cover}" width="100px"></p></td>
         	<td><p>${type.num}</p></td>
			<td>
				<a class="btn btn-primary" href="typeEdit?id=${type.id}">Submit</a>
				<a class="btn btn-danger" href="typeDelete?id=${type.id}">Delete</a>
			</td>
       	</tr>
     </c:forEach>
     
</table>

</div>
</body>
</html>