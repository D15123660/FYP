<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add product</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
<div class="container">
	<%@include file="header.jsp"%>

	<br><br>
	<form class="form-horizontal" action="goodSave" method="post" enctype="multipart/form-data" onsubmit="return check()">
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">Name</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="name" required="required">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">Price</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="price" required="required">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">Description</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="intro" required="required">
			</div>
		</div>
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">Cover</label> 
			<div class="col-sm-6">
				<input type="file" name="file"  id="input_file" required="required">Recommended size: 500 * 500
			</div>
		</div>
		<div class="form-group">
			<label for="select_topic" class="col-sm-1 control-label">Category</label>
			<div class="col-sm-6">
				<select class="form-control" id="select_topic" name="typeId">
					<c:forEach var="type" items="${typeList}">
						<option value="${type.id}">${type.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<hr>
		
		<div class="form-group">
			<label for="select_topic" class="col-sm-1 control-label">Attributes</label>
			<div class="col-sm-2">
				<select class="form-control" id="select_color">
					<c:forEach var="color" items="${colorList}">
						<option value="${color.id}">${color.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-2">
				<select class="form-control" id="select_size">
					<c:forEach var="size" items="${sizeList}">
						<option value="${size.id}">${size.name}</option>
					</c:forEach>
				</select>
			</div>
			<a class="btn btn-primary" id="sku_add">Add Attributes</a>
			
			<br><br><br>
			<div class="col-sm-offset-1 col-sm-6">
				<table class="table table-bordered table-hover" id="table_sku">
					<tr>
						<th width="10%">Colour</th>
						<th width="10%">Size</th>
						<th width="10%">Stock</th>
						<th width="5%">Operation</th>
					</tr>
				</table>
			</div>
		</div>
		
		<hr>
		
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">Submit</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
var index = 0;
$(function(){
	
	// Add sku
	$("#sku_add").on("click", function(){
		var colorId = $("#select_color").val();
		var sizeId = $("#select_size").val();
		var color = $("#select_color").find("option:selected").text();
		var size = $("#select_size").find("option:selected").text();
		// Determine whether to repeat
		var isHave = false;
		$("#table_sku").find("tr").each(function(n, v){
			if($(v).find(".color").val()==colorId && $(v).find(".size").val()==sizeId){
				isHave = true;
				return;
			}
		});
		if(isHave){
			alert("Duplicate attributes");
			return;
		}
		// Add
		var html = '<tr>' 
			+ '<td><p>' + color + '</p></td>'
			+ '<td><p>' + size + '</p></td>'
			+ '<input type="hidden" name="skuList['+index+'].colorId" value="'+colorId+'" class="color">'
			+ '<input type="hidden" name="skuList['+index+'].sizeId" value="'+sizeId+'" class="size">'
			+ '<td><input type="text" name="skuList['+index+'].stock" required="required" placeholder="Please enter stock"></td>'
			+ '<td><a class="btn btn-danger sku_delete">Delete</a></td>';
		$("#table_sku").append(html);
		index ++;
	});
	
	// Delete sku
	$(document).on("click", ".sku_delete", function(){
		$(this).parents("tr").remove();
	});
		
});

// Check before form submission
function check(){
	if($("#table_sku").find("tr").size() <= 1){
		alert("Please add attributes or stock information");
		return false;
	}
}
</script>
</body>
</html>