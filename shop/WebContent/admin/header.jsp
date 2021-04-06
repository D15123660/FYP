<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<style>
	ul#myul > li{
		border-left: 1px solid #fff;
		width:91px;
		text-align:center;
	}
	ul#myul > li  > a {
		color:#fff;
	}
	ul#myul > li  > a:hover {
		background-color:#666;
	}
</style>
</head>
<body>

	<nav class="navbar" role="navigation" style="width: 1200px;margin: 0 auto;background: #FFA500;">
		<div class="container-fluid">
			<div class="navbar-header">
				<span class="navbar-brand" href="javascript:" style="font-weight:bold;font-size:21px;">Backend management</span>
			</div>
			<div>
				<ul class="nav navbar-nav" id="myul">
					<li <c:if test="${flag==1}">class="active"</c:if>><a href="typeList">Category</a></li>
					<li <c:if test="${flag==2}">class="active"</c:if>><a href="skuList">Attributes</a></li>
					<li <c:if test="${flag==3}">class="active"</c:if>><a href="goodList">Product</a></li>
					<li <c:if test="${flag==4}">class="active"</c:if>><a href="orderList">Order</a></li>
					<li <c:if test="${flag==5}">class="active"</c:if>><a href="userList">User</a></li>
					<li <c:if test="${flag==6}">class="active"</c:if>><a href="adminList">Admin</a></li>
					<li><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

</body>
</html>