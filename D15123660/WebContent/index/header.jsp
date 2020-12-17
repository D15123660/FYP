<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css" />
    <link rel="stylesheet" href="css/nav.css" />
    <!-- <script src="js/jquery.min.js"></script> -->
    <!-- <script src="js/bootstrap.js"></script> -->
    <script src="js/zshop.js"></script>
</head>
<body>
	<div class="navbar navbar-default clear-bottom">
	<div class="container">
         <div class="navbar-header">
             <a class="navbar-brand logo-style">
                 <h1> Logo </h1>
             </a>
         </div>
	</div>
	</div>
	
	<nav class="navbar" role="navigation" style="width: 1200px;margin: 0 auto;background: #ff7300;">
		<div class="container-fluid">

			<div>
				<ul class="nav navbar-nav" id="myul">
					<li><a href="#home">HOME</a></li>
  					<li><a href="#Shose">Shoes</a></li>
 					<li><a href="#Man`s">Man`s</a></li>
 					<li><a href="#Woman`s">Woman`s</a></li>
 					<li><a href="#Kid`s">Kid`s</a></li>
 					<li><a href="#Food">Food</a></li>
				</ul>
			</div>
		</div>
	</nav>

    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            
            <div class="modal-content" id="login-account">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">User Login</h4>
                </div>
                <form action="login" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Username：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text" placeholder="Please enter user name" name="username">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Password：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" placeholder="Please enter password" name="password">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="text-align: center">
                        <button type="submit" class="btn btn-warning">Submit</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Register</h4>
                </div>
                <form action="register" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Username:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text" name="username">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Password:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Enter again:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="text-align: center">
                        <button type="submit" class="btn btn-warning">Register</button>&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>