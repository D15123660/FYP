<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Category</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
<div class="container">

	<%@include file="header.jsp"%>

	<br><br>
	<form class="form-horizontal" action="typeSave" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">Image</label> 
			<div class="col-sm-4">
				<input type="file" name="file"  id="input_file" required="required">Recommended size: 300 * 560
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">Name</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="input_name" name="name"  required="required" placeholder="Category name">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">Number</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="input_name" name="num" placeholder="Integer type">
			</div>
		</div>
			<p class="col-sm-offset-1" style="color:#666;">The number determines the display order of the front page category, from small to large, it can be a negative number</p>
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">Submit</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>