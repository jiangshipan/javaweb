<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
   <title></title>
  </head>
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
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  <body>
   	<h1>我的订单<a href="${pageContext.request.contextPath }/index.jsp" style="float:right">返回</a></h1>

<table border="1" width="100%" cellspacing="0" background="black">

<c:forEach var="order" items="${order }">
	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="7">
			订单编号：<a href="${pageContext.request.contextPath }/order?method=findOrderItemByOid&oid=${order.oid }">${order.oid }</a>　
			
			成交时间：${order.ordertime }　
			金额：<font color="red"><b>${order.total }元</b></font>　
			<c:if test="${order.state==1 }">
				<a href="${pageContext.request.contextPath }/order?method=finish&oid=${order.oid }">去付款</a>
				<a href="${pageContext.request.contextPath }/order?method=deleteOrder&oid=${order.oid }" style="float:right">删除订单</a>
			</c:if>
			<c:if test="${order.state==2 }">
				已付款
				<a href="${pageContext.request.contextPath }/order?method=deleteOrder&oid=${order.oid }" style="float:right">删除订单</a>
			</c:if>
		</td>
	</tr>
</c:forEach>



<c:forEach var="orderitem" items="${orderitem }">
	<tr bordercolor="gray" align="center">
		<td width="15%">
			<div><img src="${pageContext.request.contextPath }/${orderitem.image }" height="75"/></div>
		</td>
		<td>商品名：${orderitem.gname }</td>
		<td>单价：${orderitem.price }元</td>
		<td>店铺名：${orderitem.storename }</td>
		<td>数量：${orderitem.count }</td>
		<td>小计：${orderitem.subtotal }元</td>
		<td><a href="${pageContext.request.contextPath }/other/addJudge.jsp?gid=${orderitem.gid}" style="text-decoration: none">评价</a></td>
	</tr> 
</c:forEach>

</table>
  </body>
</html>