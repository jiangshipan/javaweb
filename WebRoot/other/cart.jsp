<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
   <title>cart.jsp</title>
  </head>
  <style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buttonid{
		width:130px;
		background:#dd182b;
		color:#fff;
		height:50px;
	}
	
</style>
  <body>
   	 <h1>购物车</h1>

	<table border="1" width="100%" cellspacing="0" background="black">
		<tr>
			<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				<a href="${pageContext.request.contextPath }/cart?method=clearCart">清空购物车</a>
				<a href="${pageContext.request.contextPath }/index.jsp">继续购买</a>
			</td>
		</tr>
		<tr>
			<th>图片</th>
			<th>商品</th>
			<th>店铺</th>
			<th>单价</th>
			<th>数量</th>
			<th>小计</th>
			<th>操作</th>
		</tr>
	
	<c:forEach var="items" items="${cart.cartItems }">
		<tr>
			<td><div><img src="${pageContext.request.contextPath }/${items.goods.image }"/></div></td>
			<td>${items.goods.gname }</td>
			<td>${items.goods.storename }</td>
			<td>${items.goods.price }元</td>
			<td>${items.count }</td>
			<td>${items.subtotal }</td>
			<td><a href="${pageContext.request.contextPath }/cart?method=removeCart&gid=${items.goods.gid}">删除</a></td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				合计：${cart.total }元
			</td>
		</tr>
		<tr>
			<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				<a href="${pageContext.request.contextPath }/order?method=addOrder"><input type="submit" value="立即结算" id="buttonid"/></a>
			</td>
		</tr>
	</table>
  </body>
</html>
