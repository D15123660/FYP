<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Administrator list</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container">

	<%@include file="header.jsp"%>
	
	<div class="text-right"><a class="btn btn-warning" href="adminAdd">Add Administrator</a></div>
		
	<br>
	
	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="10%">Username</th>
		<th width="10%">Operation</th>
	</tr>
	
	<c:forEach var="admin" items="${adminList}">
         <tr>
         	<td><p>${admin.id}</p></td>
         	<td><p>${admin.username}</p></td>
			<td>
				<c:if test="${admin.id==1}"><p>System protected users</p></c:if>
				<c:if test="${admin.id>1}">
					<a class="btn btn-info" href="adminRe?id=${admin.id}">Reset password</a>
					<a class="btn btn-danger" href="adminDelete?id=${admin.id}">Delete</a>
				</c:if>
			</td>
       	</tr>
     </c:forEach>
     
</table>

<br>${pageTool}<br>
</div>
</body>
</html>