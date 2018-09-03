<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
   <title>index.jsp</title>
  </head>
  <body>
  	<iframe frameborder="0" width="100%" height="150"  src="${pageContext.request.contextPath }/mainface/top.jsp"></iframe>
   	<iframe frameborder="0" width="100%" height="480" src="${pageContext.request.contextPath }/mainface/middle.jsp"></iframe>
   	<iframe frameborder="0" width="100%" height="2000" src="${pageContext.request.contextPath }/category?method=findAllCategory"></iframe>
   	<iframe frameborder="0" width="100%" height="150" src="${pageContext.request.contextPath }/mainface/footer.jsp"></iframe>
  </body>
</html>
