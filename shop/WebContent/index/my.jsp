<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>My Order</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/global.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="css/swiper.min.css">
	<link rel="stylesheet" href="css/styles.css">
	<script src="js/jquery.min.js" charset="UTF-8"></script>
	<script src="js/bootstrap.min.js" charset="UTF-8"></script>
	<script src="js/swiper.min.js" charset="UTF-8"></script>
	<script src="js/global.js" charset="UTF-8"></script>
	<script src="js/alert.min.js" charset="UTF-8"></script>
	<script src="layer/layer.js" charset="UTF-8"></script>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	
	<div class="content clearfix bgf5">
		<section class="user-center inner clearfix">
			<div class="pull-left bgf">
				<!-- <a href="" class="title">欢迎页</a> -->
				<dl class="user-center__nav">
					<dt>Personal center</dt>
					<a href="my"><dd <c:if test="${flag==11}">class="active"</c:if>>Personal information</dd></a>
				</dl>
				<dl class="user-center__nav">
					<dt>Order center</dt>
					<a href="order"><dd <c:if test="${flag==12}">class="active"</c:if>>My order</dd></a>
				</dl>
			</div>
			
			<div class="pull-right">
				<div class="user-content__box clearfix bgf">
					<div class="title">Delivery information</div>
					<div class="modify_div">
						<form action="updateUser" class="user-addr__form form-horizontal" role="form" method="post">
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Name：</label>
								<div class="col-sm-6">
									<input class="form-control" name="name" value="${user.name}" placeholder="Please enter your name" type="text" required="required">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Phone：</label>
								<div class="col-sm-6">
									<input class="form-control" name="phone" value="${user.phone}" placeholder="Please enter your phone number" type="text" required="required">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Address：</label>
								<div class="col-sm-6">
									<input class="form-control" name="address" value="${user.address}" placeholder="Please enter your address" type="text" required="required">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-6">
									<button type="submit" class="but">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="user-content__box clearfix bgf">
					<div class="title">Change Password</div>
					<div class="modify_div">
						<form action="updatePassword" class="user-addr__form form-horizontal" role="form" method="post">
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Old password：</label>
								<div class="col-sm-6">
									<input class="form-control" name="password" type="password" required="required">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">New password：</label>
								<div class="col-sm-6">
									<input class="form-control" name="passwordNew" type="password" required="required">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-6">
									<button type="submit" class="but">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>

<script type="text/javascript">
$(function(){
	if("${msg}"!=""){
		layer.msg("${msg}");
	}
});
</script>
</body>
</html>