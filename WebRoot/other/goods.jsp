<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
   <title>goods.jsp</title>
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
</style>
  </head>
  <body>
   	<c:forEach var="list2" items="${list2 }" >
	  <div class="icon">
	    <a href="${pageContext.request.contextPath }/goods?method=findGoods&gid=${list2.gid }"><img width="160" height="150" src="${pageContext.request.contextPath }/${list2.image}" border="0"/></a>
	     <br/>
	   	<a href="${pageContext.request.contextPath }/goods?method=findGoods&gid=${list2.gid }">${list2.gname }</a>
	  </div>
	</c:forEach>
  </body>
</html>
