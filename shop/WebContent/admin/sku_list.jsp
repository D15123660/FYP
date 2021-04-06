<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Product list</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container">

	<%@include file="header.jsp" %>
		
	<br>
	
	<div>
		<form class="form-inline" method="post" action="skuSave">
			<input type="hidden" name="status" value="${status}">
			<c:if test="${status == 0}">
				<input type="text" class="form-control" id="input_name" name="name" placeholder="Input color" required="required" style="width: 500px">
				<input type="submit" class="btn btn-warning" value="Add color"/>
			</c:if>
			<c:if test="${status > 0}">
				<input type="text" class="form-control" id="input_name" name="name" placeholder="Input size" required="required" style="width: 500px">
				<input type="submit" class="btn btn-warning" value="Add size"/>
			</c:if>
		</form>
	</div>

	<br>
		
	<ul role="tablist" class="nav nav-tabs">
        <li <c:if test='${status==0}'>class="active"</c:if> role="presentation"><a href="skuList">Colour</a></li>
        <li <c:if test='${status==1}'>class="active"</c:if> role="presentation"><a href="skuList?status=1">Size</a></li>
    </ul>
    
	<br>

	<table class="table table-bordered table-hover">

		<tr>
			<th width="5%">ID</th>
			<th width="10%">Name</th>
			<th width="10%">Operation</th>
		</tr>
		
		<c:forEach var="sku" items="${skuList}">
	         <tr>
	         	<td><p>${sku.id}</p></td>
	         	<td><p>${sku.name}</p></td>
				<td>
					<a class="btn btn-danger" href="skuDelete?id=${sku.id}&status=${status}">Delete</a>
				</td>
	       	</tr>
	     </c:forEach>
	     
	</table>

</div>

</body>
</html>