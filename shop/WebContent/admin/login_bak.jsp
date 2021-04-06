<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
</head>

<!-- Set background image -->
<body style="background-image:url(img/back.jpg);background-size:50%;">

	<div class="container">

		<c:if test="${msg!=null}"><div class="alert alert-danger text-center">${msg}</div></c:if>

		<form class="form-horizontal"action="login" method="post" style="margin-top:15%;" >
			<h2 class="text-center" style="color:#FFF">Log in to the backend</h2>
			<div class="form-group">
				<div class="col-md-4 col-md-offset-4">
					<input type="text" class="form-control" style="height:auto;padding:10px;" placeholder="Please enter username" name="username" value="admin">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-4 col-md-offset-4">
					<input type="password" class="form-control" style="height:auto;padding:10px;" placeholder="Please enter password" name="password" value="admin">
				</div>
			</div>
			<div class="col-md-4 col-md-offset-4">			
				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
			</div>
		</form>

	</div>

</body>
</html>
