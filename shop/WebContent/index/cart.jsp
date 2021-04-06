<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Shopping cart</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	<!--cart-items-->
	<div class="cart-items">
		<div class="container">
		
		<c:if test="${msg!=null}"><div class="alert alert-success">${msg}</div></c:if>
		<c:if test="${order!=null}">
			<h2>My cart</h2>
			
			<c:forEach var="item" items="${order.itemList}">
				<div class="cart-header col-md-6">
					<div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							<a href="detail?goodid=${item.good.id}">
								<img src="../${item.good.cover}" class="img-responsive">
							</a>
						</div>
						<div class="cart-item-info">
							<h3><a href="detail?goodid=${item.good.id}">${item.good.name}</a></h3>
							<h3><span>Price: € ${item.good.price}</span></h3>
							<h3><span>Quantity: ${item.amount}</span></h3>
							<a class="btn btn-info" href="javascript:buy(${item.good.id});">Add</a>
							<a class="btn btn-warning" href="javascript:lessen(${item.good.id});">Reduce</a>
							<a class="btn btn-danger" href="javascript:deletes(${item.good.id});">Delete</a>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</c:forEach>
			
			<div class="cart-header col-md-12">
				<hr>
				<h3>Total amount: € ${order.total}</h3>
				<a class="btn btn-success btn-lg" style="margin-left:74%" href="save">Submit Order</a>
			</div>
			</c:if>
			<c:if test="${order==null}"><div class="alert alert-info">Empty</div></c:if>
			
		</div>
	</div>
	<!--//cart-items-->	
	
	<jsp:include page="footer.jsp"/>

</body>
</html>