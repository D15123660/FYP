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
	
	<div class="text-right"><a class="btn btn-warning" href="goodAdd">Add product</a></div>
	
	<br>
		
	<ul role="tablist" class="nav nav-tabs">
        <li <c:if test='${status==0}'>class="active"</c:if> role="presentation"><a href="goodList">All product</a></li>
        <li <c:if test='${status==1}'>class="active"</c:if> role="presentation"><a href="goodList?status=1">Recommend</a></li>
    </ul>
    
    <c:if test="${status == 1}"><br><p>Only displays the first [4] records</p></c:if>
	
	<br>

	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="10%">Image</th>
		<th width="10%">Name</th>
		<th width="10%">Description</th>
		<th width="10%">Price</th>
		<th width="10%">Category</th>
		<th width="10%">Stock</th>
		<th width="10%">Operation</th>
	</tr>
	
	<c:forEach var="good" items="${goodList}">
         <tr>
         	<td><p>${good.id}</p></td>
         	<td><p><a href="../index/detail?goodid=${good.id}" target="_blank"><img src="${good.cover}" width="100px"></a></p></td>
         	<td><p><a href="../index/detail?goodid=${good.id}" target="_blank">${good.name}</a></p></td>
         	<td><p>${good.intro}</p></td>
         	<td><p>${good.price}</p></td>
         	<td><p>${good.type.name}</p></td>
         	<td>
         		<c:forEach var="sku" items="${good.skuList}">
         			<p>${sku.color.name} ${sku.size.name} ${sku.stock}</p>
         		</c:forEach>
         	</td>
			<td>
				<p>
					<c:if test="${good.show}"><a class="btn btn-info topDelete" href="javascript:;" type="1" goodid="${good.id}" text="Remove">Remove</a></c:if>
					<c:if test="${!good.show}"><a class="btn btn-primary topSave" href="javascript:;" type="1" goodid="${good.id}" text="Add">Add</a></c:if>
				</p>
				<a class="btn btn-success" href="goodEdit?id=${good.id}">Submit</a>
				<a class="btn btn-danger" href="goodDelete?id=${good.id}">Delete</a>
			</td>
       	</tr>
     </c:forEach>
     
</table>

<br>${pageTool}<br>
</div>


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$(document).on("click", ".topSave", function(){
		var type = $(this).attr("type");
		var goodid = $(this).attr("goodid");
		var text = $(this).attr("text");
		var old = $(this).text();
		var obj = this;
		$.post("topSave.action", {"goodId": goodid, "type": type}, function(data){
			if(data=="ok"){
				$(obj).text(text).attr("class", "btn btn-info topDelete").attr("text", old);
			}else{
				alert("Operation failed!");
			}
		}, "text");
	});
	$(document).on("click", ".topDelete", function(){
		var type = $(this).attr("type");
		var goodid = $(this).attr("goodid");
		var text = $(this).attr("text");
		var old = $(this).text();
		var obj = this;
		$.post("topDelete.action", {"goodId": goodid, "type": type}, function(data){
			if(data=="ok"){
				$(obj).text(text).attr("class", "btn btn-primary topSave").attr("text", old);
			}else{
				alert("Operation failed!");
			}
		}, "text");
	});
});
</script>
</body>
</html>