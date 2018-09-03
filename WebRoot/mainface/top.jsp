<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
   <title>top.jsp</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/top.css">
  </head>
  <body style="background:#f5f5f5">
   <div class="top-nav">
		<div class="container clear-float">
			<div class="fl">
				<a class="item" href="#">首页</a>
				<a class="item" href="#">宠物状态</a>
			</div>
			<div class="fr">
			<c:if test="${empty user }">
				<a class="item" href="${pageContext.request.contextPath }/mainface/login.jsp" target="_parent">登录</a>
				<a class="item" href="${pageContext.request.contextPath }/mainface/regist.jsp" target="_parent">注册</a>
			</c:if>
			<c:if test="${not empty user }">
				您好:${user.username }
				<a class="item" href="${pageContext.request.contextPath }/order?method=findAllOrder" target="_top">我的订单</a>
				<a class="item" href="${pageContext.request.contextPath }/user?method=exit" target="_parent">退出</a>
			</c:if>
				
				
			</div>
		</div>
	</div>
	<div class="header clear-float">
		<div class="container ">
			<span><div class="col-2 logo"><img src="${pageContext.request.contextPath }/image/welcome.jpg" width="110px" height="50px"></div></span>

			<span>
				<div class="search-bar col-5 ">
				<form action="${pageContext.request.contextPath }/goods" method="post">
					<input type="hidden" name="method" value="findBySimple"/>
					<input type="text" name="name"/>
					<button type="submit">搜索</button>
				</form>
					
				</div></span>
			<span><div class=" cart col-3 "><a class="item" href="${pageContext.request.contextPath }/other/cart.jsp" target="_parent">我的购物车</a></div></span>
		</div>

	</div>
  </body>
</html>
