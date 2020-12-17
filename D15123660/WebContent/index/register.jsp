<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>User registration</title>
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
		
	</div>
	
	<div style="background:white" >
		<div class="login-layout container">
			<div class="form-box register">
  				<div class="tabs-nav">
  					<h2>User registration</h2>
  				</div>
  				<div class="tabs_container">
					<form class="tabs_form" method="post" action="register" id="register_form">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</div>
								<input class="form-control phone" name="username" id="register_phone" required placeholder="Please enter username" autocomplete="off" type="text">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</div>
								<input class="form-control password" name="password" id="register_pwd" placeholder="Please enter password" autocomplete="off" type="password">
								<!--  <div class="input-group-addon pwd-toggle" title="Show password"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></div>  -->
							</div>
						</div>
	                    <button class="btn btn-large btn-primary btn-lg btn-block submit" id="register_submit" data-loading-text="Registered..." type="button">Registered</button><br>

                    </form>
                </div>
			</div>

		</div>
	</div>
	
</body>
</html>