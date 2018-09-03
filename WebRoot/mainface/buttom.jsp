<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
   <title>buttom.jsp</title>
   <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/normalize/8.0.0/normalize.min.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/buttom.css"> 
  </head>
  <body>
   	<div class="container">
	<c:forEach var="list" items="${list }">
		<div class="cat-promote clear-float">
		<div class="title">${list.cname }</div>
		<iframe width="100%" height="230" frameborder="0" src="${pageContext.request.contextPath }/goods?method=findGoodsByCid&cid=${list.cid}"></iframe>	
		</div>
	</c:forEach>
	</div>
	
  </body>
</html>
