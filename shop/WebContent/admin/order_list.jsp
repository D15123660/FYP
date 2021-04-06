<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Order List</title>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container">

	<%@include file="header.jsp" %>
	
	<br>
	
	<ul role="tablist" class="nav nav-tabs">
        <li <c:if test='${status==0}'>class="active"</c:if> role="presentation"><a href="orderList">All orders</a></li>
        <li <c:if test='${status==1}'>class="active"</c:if> role="presentation"><a href="orderList?status=1">Unpaid</a></li>
        <li <c:if test='${status==2}'>class="active"</c:if> role="presentation"><a href="orderList?status=2">Paid</a></li>
        <li <c:if test='${status==3}'>class="active"</c:if> role="presentation"><a href="orderList?status=3">Delivery</a></li>
        <li <c:if test='${status==4}'>class="active"</c:if> role="presentation"><a href="orderList?status=4">Completed</a></li>
    </ul>
    
    <br>
	
	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="5%">Total price</th>
		<th width="15%">Product details</th>
		<th width="20%">Delivery information</th>
		<th width="10%">Order Status</th>
		<th width="10%">Payment method</th>
		<th width="10%">Order user</th>
		<th width="10%">Order time</th>
		<th width="10%">Operation</th>
	</tr>
	
	<c:forEach var="order" items="${orderList}">
         <tr>
         	<td><p>${order.id}</p></td>
         	<td><p>${order.total}</p></td>
         	<td>
	         	<c:forEach var ="item" items="${order.itemList}">
		         	<p>${item.good.name}</p>
		         	<p>${item.color.name}  ${item.size.name}</p>
		         	<p>€${item.price} x ${item.amount}</p>
	         	</c:forEach>
         	</td>
         	<td>
         		<p>${order.name}</p>
         		<p>${order.phone}</p>
         		<p>${order.address}</p>
         	</td>
			<td>
				<p>
					<c:if test="${order.status==1}">Unpaid</c:if>
					<c:if test="${order.status==2}"><span style="color:red;">Paid</span></c:if>
					<c:if test="${order.status==3}">Delivery</c:if>
					<c:if test="${order.status==4}">Completed</c:if>
				</p>
			</td>
			<td>
				<p>
					<c:if test="${order.paytype==1}">PayPal</c:if>
					<c:if test="${order.paytype==2}">Apple Pay</c:if>
					<c:if test="${order.paytype==3}">Credit card</c:if>
				</p>
			</td>
			<td><p>${order.user.username}</p></td>
			<td><p><fmt:formatDate value="${order.systime}" pattern="yyyy-MM-dd HH:mm:ss" /></p></td>
			<td>
				<c:if test="${order.status==2}">
					<a class="btn btn-success" href="orderDispose?id=${order.id}&status=${status}">Ship</a>
				</c:if>
				<c:if test="${order.status==3}">
					<a class="btn btn-warning" href="orderFinish?id=${order.id}&status=${status}">Complete</a>
				</c:if>
				<a class="btn btn-danger" href="orderDelete?id=${order.id}&status=${status}">Delete</a>
			</td>
       	</tr>
	</c:forEach>
     
</table>

<br>${pageTool}<br>
</div>
</body>
</html>