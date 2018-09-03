<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
   <title>order.jsp</title>
     <style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	#imgid{
		height: 50px;
		width:50px;
	}
	#buttonid{
		width:130px;
		background:#dd182b;
		color:#fff;
		height:50px;
		position: absolute;
		top:350px;
		left:800px;
	}
  </style>
  </head>
  <body>
   	<h1>当前订单</h1>
	<table border="1" width="100%" cellspacing="0" background="black">
		<tr bgcolor="gray" bordercolor="gray">
			<td colspan="6">
				订单编号：${order.oid }　成交时间：${order.ordertime }　金额：<font color="red"><b>${order.total }元</b></font>
			</td>
		</tr>
	<c:forEach var="items" items="${order.orderItems }">
		<tr bordercolor="gray" align="center">
			<td width="15%">
				<div><img src="${pageContext.request.contextPath }/${items.goods.image }" height="75"/></div>
			</td>
			<td>商品名：${items.goods.gname }</td>
			<td>商品单价：${items.goods.price }元</td>
			<td>店铺名：${items.goods.storename }</td>
			<td>数量：${items.count }</td>
			<td>小计：${items.subtotal }元</td>
		</tr>
	</c:forEach>
	
	</table>
	<br/>
	<form method="post" action="${pageContext.request.contextPath }/order" id="form">
		收货地址：<input type="text" name="address" size="50" value="西安邮电大学"/><br/>
		<input type="hidden" name="oid" value="${order.oid }"/>
		<input type="hidden" name="method" value="payOrder"/>
		选择支付方式：<br/>
		<input type="radio" name="zhifubao"/>支付宝
		<img id="imgid" src="${pageContext.request.contextPath }/image/zhifubao.png" align="middle"/>&nbsp;&nbsp;&nbsp;
		<input type="radio" name="weixin" />微信
		<img id="imgid" src="${pageContext.request.contextPath }/image/weixin.png" align="middle"/>
	</form>
	<a href="${pageContext.request.contextPath }/order?method=finish&oid=${order.oid }"><input type="submit" value="去付款" id="buttonid"/></a>
   	
  </body>
</html>
