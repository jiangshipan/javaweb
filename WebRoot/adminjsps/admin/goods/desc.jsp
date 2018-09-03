<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'goodsdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
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
</style>
  </head>
  
  <body>
  <div>
  <%-- 
	判断 goods里面的cid和分类集合里面的cid是否相同，如果相同selected				
--%>
    <img src="${pageContext.request.contextPath }/${goods.image}" width="130" height="150"border="0"/>
  </div>
  <form style="margin:20px;" id="form" action="${pageContext.request.contextPath }/goods" method="post">
  	<input type="hidden" name="gid" value="${goods.gid }"/>
  	<input type="hidden" name="image" value="${goods.image }"/>
  	商品名称：<input type="text" name="gname" value="${goods.gname }"/><br/>
  	商品单价：<input type="text" name="price" value="${goods.price }"/><br/>
  	商品店铺：<input type="text" name="storename" value="${goods.storename}"/><br/>

  	商品分类：<select style="width: 150px; height: 20px;" name="cid">				 
				<c:forEach var="c"  items="${category }">
					<option value="${c.cid }"  <c:if test="${goods.cid == c.cid }">selected</c:if>>${c.cname }</option>
				</c:forEach>
    	</select><br/>

  	<input type="submit" name="method" value="del"/>
  	<input type="submit" name="method" value="mod"/>
  </form>
  </body>
</html>
