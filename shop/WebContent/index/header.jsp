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
    <link rel="stylesheet" href="iconfont/iconfont.css">
    <!-- <script src="js/jquery.min.js"></script> -->
    <!-- <script src="js/bootstrap.js"></script> -->
    <script src="js/zshop.js"></script>
</head>
<body>
	<div class="navbar navbar-default clear-bottom">
	<div class="container">
         
         <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
             <ul class="nav navbar-nav">
                 <li class="active">
                     <a href="index" style="background: #FFA500;">Happy Buy</a>
                 </li>
                 <c:if test="${sessionScope.user!=null}">
                 <li>
                     <a href="order">My Order</a>
                 </li>
                 <li>
                     <a href="shopcart">Shopping cart</a>
                 </li>
                 <li class="dropdown">
                     <a href="my">Member Centre</a>
                 </li>
                 </c:if>
             </ul>
             <ul class="nav navbar-nav navbar-right">
            		 <c:if test="${sessionScope.user==null}">
	                 <li>
	                     <a href="#" data-toggle="modal" data-target="#loginModal">Sign in</a>
	                 </li>
	                 <li>
	                     <a href="#" data-toggle="modal" data-target="#registModal">Registration</a>
	                 </li>
	             </c:if>
	             <c:if test="${sessionScope.user!=null}">    
	                 <li class="userName">
	                    Hello! ：${user.username}
	                 </li>
	                 <li class="dropdown">
	                     <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
	                         <img class="img-circle" src="images/user.jpeg" height="30" />
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li>
	                             <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
	                                 <i class="glyphicon glyphicon-cog"></i>Change Password
	                             </a>
	                         </li>
	                         <li>
	                             <a href="logout">
	                                 <i class="glyphicon glyphicon-off"></i> Log out
	                             </a>
	                         </li>
	                     </ul>
	                 </li>
                 </c:if>
             </ul>
         </div>
     </div>
	</div>
	
	<!-- Home navigation bar -->
	<div class="top-nav" style="width: 1200px;margin: 0 auto;background: #FFA500;">
		<div class="nav-box inner">
			<ul class="nva-list">
				<a href="index"><li <c:if test="${flag==-1}">class="active"</c:if> style="border-right: 1px solid #fff;">Home</li></a>
				<c:forEach var="type" items="${typeList}">
					<a href="goods?typeid=${type.id}"><li <c:if test="${flag==type.id}">class="active"</c:if> style="border-right: 1px solid #fff;">${type.name}</li></a>
				</c:forEach>
			</ul>
			<ul class="nva-list" style="float:right;">	
			</ul>
		</div>
	</div>

	 <!-- Change password -->
    <div class="modal fade" id="modifyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Change Password</h4>
                </div>
                <form action="updatePassword" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Old password：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">New password：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" name="passwordNew">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Re-enter Password：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" name="repassword">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="text-align:center;">
                        <button type="submit" class="btn btn-warning">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    

    <!-- Login  -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <!-- Login with username and password start -->
            <div class="modal-content" id="login-account">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">User login</h4>
                </div>
                <form action="login" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Username：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text" placeholder="Please enter username" name="username">
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
                        <button type="submit" class="btn btn-warning">Submit</button> &nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    

    <!-- Registration -->
    <div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">User registration</h4>
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
                            <label class="col-sm-3 control-label">Confirm password:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="text-align: center">
                        <button type="submit" class="btn btn-warning">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>