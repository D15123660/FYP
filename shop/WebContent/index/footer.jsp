<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<script src="js/global.js"></script>
</head>
<body>

	<div class="footer" style="margin-top: 40px;">
		<div class="footer-tags">
			<div class="copy-box clearfix">
				<p class="copyright" style="width: 1200px;margin: 0 auto;background: #ff7300;"> <a href="../admin/login" target="_blank" style="color:#fff;">Backend management </a> </p>
			</div>
		</div>
	</div>
	
	<!-- Right menu-->
	<div class="right-nav">
		<ul class="r-with-gotop">
			<li class="r-toolbar-item">
				<a href="my" class="r-item-hd">
					<i class="iconfont icon-user"></i>
					<div class="r-tip__box"><span class="r-tip-text">User Center</span></div>
				</a>
			</li>
			<li class="r-toolbar-item">
				<a href="shopcart" class="r-item-hd">
					<i class="iconfont icon-cart" data-badge="${sessionScope.total==null ? 0 : sessionScope.total}"></i>
					<div class="r-tip__box"><span class="r-tip-text">Shopping cart</span></div>
				</a>
			</li>
			<li class="r-toolbar-item to-top">
				<i class="iconfont icon-top"></i>
				<div class="r-tip__box"><span class="r-tip-text">Back to top</span></div>
				<script>
					$(document).ready(function(){ $('.to-top').toTop({position:false}) });
				</script>
			</li>
		</ul>
	</div>
		
	<script>
		$(document).ready(function(){ 
			// Page drop down fixed floor navigation
			$('.floor-nav').smartFloat();
		});
	</script>

</body>
</html>