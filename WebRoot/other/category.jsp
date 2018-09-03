<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
   <title></title>
   <style>
   	a{
   		text-decoration: none;
   		font-size: 24px;
   		
   	}
   	.div1{
   		border: 1px solid #c8c8c8;
   		text-align: center;
   	}
   </style>
  </head>
  <body>
  <div class="div1">
  	<br/><a href="#">全部分类</a><br/><br/>
   	<c:forEach var="list" items="${list }">
		<a href="${pageContext.request.contextPath }/goods?method=findGoodsByCid1&cid=${list.cid } ">${list.cname }</a><br/><br/>
	</c:forEach>
  </div>
  	
  </body>
</html>
