<%@page import="com.jsp.bean.Goods"%>
<%@page import="java.util.List"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
   <title>middle.jsp</title>
   <base target="_top">
  </head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/middle.css">
  <body>
   <div class="main-promote clear-float">
		<div class="container">
			<div class=" /*
*/cat col-2 ">
				<iframe width="200" height="450" frameborder="0" src="${pageContext.request.contextPath }/category?method=findCategory"></iframe>
			</div>
		</div>
		<% 
			String cid = request.getParameter("cid");
			if(cid!=null){
			%>
			<iframe width="900" height="450" frameborder="0" src="${pageContext.request.contextPath }/goods?method=dispatcher&cid=${cid }"></iframe>
			<% 
			}else{
				
			%>
			<iframe width="900" height="450" frameborder="0" src="${pageContext.request.contextPath }/goods?method=findByRand"></iframe>
			<% 
			}
			
		%>
		
		
			
   </div>
	
  </body>
</html>
