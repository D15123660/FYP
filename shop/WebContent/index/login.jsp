<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>User login</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/global.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="css/login.css">
	<script src="js/jquery.min.js" charset="UTF-8"></script>
	<script src="js/bootstrap.min.js" charset="UTF-8"></script>
	<script src="js/jquery.form.js" charset="UTF-8"></script>
	<script src="js/global.js" charset="UTF-8"></script>
	<script src="js/login.js" charset="UTF-8"></script>
</head>
<body>

	<div class="public-head-layout container">
		<a class="logo" href="index"><img src="images/icons/logo.jpg" alt="" class="cover"></a>
	</div>
	
	<div style="background:url(images/login_bg.jpg) no-repeat center center; ">
		<div class="login-layout container">
			<div class="form-box login">
				<div class="tabs-nav">
					<h2>User login</h2>
				</div>
				<div class="tabs_container">
					<form class="tabs_form" action="login" method="post" id="login_form">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</div>
								<input class="form-control phone" name="username" id="login_phone" required placeholder="username" autocomplete="off" type="text">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</div>
								<input class="form-control password" name="password" id="login_pwd" placeholder="Please enter password" autocomplete="off" type="password">
								<div class="input-group-addon pwd-toggle" title="show password"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></div>
							</div>
						</div>
						
	                    <button class="btn btn-large btn-primary btn-lg btn-block submit" id="login_submit" data-loading-text="logging in..." type="button">Log in</button><br>
	                    
						<div class="form-group">
							<div class="error_msg" id="login_error"></div>
						</div>
	                    <p class="text-center">No account？<a href="register">Free registration</a></p>
                    </form>
                </div>
			</div>

			<script>
				$(document).ready(function() {
					// Sign of successful registration
					if($.getUrlParam('flag')=="-1"){
						$("#login_error").html(msgtemp('<strong>Registration successed</strong> Please sign in', 'alert-success'));
					}
					// Background information
					if("${msg}"!=""){
						$("#login_error").html(msgtemp('<strong>${msg}</strong>', 'alert-danger'));
					}
					$('.submit').click(function() {
						var that = this;
						var form = $(this).parents('form')
						var phone = form.find('input.phone');
						var pwd = form.find('input.password');
						var error = form.find('.error_msg');
						// Verify mobile phone number
						switch(phone.validatemobile()) {
							case 1: error.html(msgtemp('<strong>Username is empty</strong> Please enter username',    'alert-warning')); return; break;
						}
						// Verify password complexity
						switch(pwd.validatepwd()) {
							case 1: error.html(msgtemp('<strong>Password can not be blank</strong> Please enter password',    'alert-warning')); return; break;
						}
						error.empty();
						form.submit();
						return false;
					});
				});
			</script>
		</div>
	</div>
	
</body>
</html>