<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Customer modification</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container">

	<%@include file="header.jsp"%>

	<br><br>
	
	<form class="form-horizontal" action="userUpdate" method="post">
		<input type="hidden" name="id" value="${user.id}">
		<input type="hidden" name="username" value="${user.username}">
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">Username</label>
			<div class="col-sm-5">${user.username}</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">Phone</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="phone" value="${user.phone}">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">Address</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="address" value="${user.address}">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">Submit</button>
			</div>
		</div>
	</form>
	
	<span style="color:red;">${msg}</span>
	
</div>	
</body>
</html>