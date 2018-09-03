<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
   <title></title>
  </head>
  <body>
  <h2>评价:</h2>
  <%int n=1; %>
  <c:forEach var="list" items="${list }">
	第<%out.print(n); n++;%>条评价:${list.judge }<br/>
  </c:forEach>
  </body>
</html>
