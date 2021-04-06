<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Mall system-backend login</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="css/mycss.css" type="text/css"></link>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrapValidator.min.js"></script>
   <script>
        $(function(){
            $('#frmLogin').bootstrapValidator({
                feedbackIcons:{
                    valid:'glyphicon glyphicon-ok',
                    invalid:'glyphicon glyphicon-remove',
                    validating:'glyphicon glyphicon-refresh'
                },
                fields:{
                		userName:{
                        validators:{
                            notEmpty:{
                                message:'Username can not be empty'
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: 'Password can not be blank'
                            }
                        }
                    }
                }
            });
        });
    </script>    
    
  </head>
  <body>
  	<!-- Use custom css style div-signin to complete element centering-->
    <div class="container div-signin">
      <div class="panel panel-primary div-shadow">
      	<!-- Load custom styles for h3 tags, complete text centering and up and down spacing adjustments -->
	    <div class="panel-heading">
	    	<h3>Happy Buy</h3>
	    	<span>Shopping Mall System</span>
	    </div>
	    <div class="panel-body">
	      <!-- login form start -->
	      <form action="login" class="form-horizontal" method="post" id="frmLogin">
		     <div class="form-group">
		       <label class="col-sm-3 control-label">Username：</label>
		       <div class="col-sm-9">
		         <input class="form-control" type="text" placeholder="Please enter username" name="username">
		       </div>
		    </div>
		     <div class="form-group">
		       <label class="col-sm-3 control-label">Password：</label>
		       <div class="col-sm-9">
		         <input class="form-control" type="password" placeholder="Please enter password" name="password">
		       </div>
		    </div>
		    <div class="form-group">
		       <div class="col-sm-3">
		       </div>
		       <div class="col-sm-9 padding-left-0">
		       	 <div class="col-sm-4">
			         <button type="submit" class="btn btn-primary btn-block">Submit</button>
		       	 </div>
		       	 <div class="col-sm-4">
			         <button type="reset" class="btn btn-primary btn-block">Reset</button>
		       	 </div>
		       	 <div class="col-sm-4" style="padding:0;margin-left:-10px;width:auto;padding-top: 5px;">
		       	 	<c:if test="${msg!=null }">
			       		<span class="text-danger">${msg}</span>
			       </c:if>
		       	 </div>
		       </div>
		    </div>
	      </form>
	      <!-- login form end -->
	    </div>
	  </div>
    </div>
  </body>
</html>
