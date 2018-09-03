<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
   <head>
   <title>hotgoods.jsp</title>
   <base target="_top">
   <style type="text/css">
	body {
		font-size: 10pt;
	}
	.icon {
		margin:10px;
		border: solid 2px gray;
		width: 160px;
		height: 180px;
		text-align: center;
		float: left;
	}
	.left{
		position: absolute;
		left:60px;
	}
	.right{
		position: absolute;
		top:10px;
	}
	.size{
		font-size: 40px;
	}
</style>
  </head>
  <body>
  <div class="left">
	 <p class="size">商</p>
	 <p class="size">品</p>
	 <p class="size">推</p>
	 <p class="size">荐</p>
  </div>
  <div class="right">
  	<c:forEach var="list" items="${list }" >
	  <div class="icon" style="float:right">
	    <a href="${pageContext.request.contextPath }/goods?method=findGoods&gid=${list.gid }"><img width="160" height="150" src="${pageContext.request.contextPath }/${list.image}" border="0"/></a>
	     <br/>
	   	<a href="${pageContext.request.contextPath }/goods?method=findGoods&gid=${list.gid }">${list.gname }</a>
	  </div>
	</c:forEach>
  </div>
   	
  </body>
</html>