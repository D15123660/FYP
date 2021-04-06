<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>My order</title>
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
					<div class="title">My order</div>
					<div class="order-list__box bgf">
						<div class="order-panel">
							<ul class="nav user-nav__title" role="tablist">
								<li class="nav-item active"><a href="order">All orders</a></li>
								
							</ul>

							<div class="tab-content">
								<div role="tabpanel" class="tab-pane fade in active" id="all">
									<table class="table text-center">
										<tbody>
											<c:if test="${orderList==null}">
												<tr class="order-empty">
													<td colspan="6">
														<div class="empty-msg">There are no recent orders!<br><a href="index">Want to check it out?</a></div>
													</td>
												</tr>
											</c:if>
											<c:if test="${orderList!=null}">
												<tr>
													<th width="550">Product information</th>
													<th width="120">Rrder amount</th>
													<th width="120">Trading status</th>
													<th width="120">Trading operations</th>
												</tr>
												<c:forEach var="order" items="${orderList}">
													<tr class="order-item" id="tr319">
														<td>
															<label>
																<span>Order number: ${order.id}</span>
																<c:forEach var="item" items="${order.itemList}">
																	<div class="card">
																		<div class="img"><a href="detail?goodid=${item.good.id}"><img src="${item.good.cover}" alt="" class="cover"></a></div>
																		<div class="name ep2"><a href="detail?goodid=${item.good.id}">${item.good.name}</a></div>
																		<div class="format">${item.color.name} ${item.size.name}</div>
																		<div class="favour"> € ${item.price}  x  ${item.amount}</div>
																	</div>
																</c:forEach>
																<span>Rrder time: <fmt:formatDate value="${order.systime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
															</label>
														</td>
														<td>€${order.total}</td>
														<td class="state">
															<c:if test="${order.status==1}">Unpaid</c:if>
															<c:if test="${order.status==2}">Paid</c:if>
															<c:if test="${order.status==3}">Shipping</c:if>
															<c:if test="${order.status==4}">Completed</c:if>
														</td>
														<td class="order">
															<c:if test="${order.status==1}">
																<a href="topay?orderid=${order.id}" class="but but-primary">Pay now</a>
															</c:if>
															
														</td>
													</tr>
												</c:forEach>
											</c:if>
											
										</tbody>
									</table>
								
								</div>
							</div>
						</div>
					</div>
				
				</div>
			</div>
		</section>
	</div>

</body>
</html>