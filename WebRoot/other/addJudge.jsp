<%@ page language="java"  pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
  <head>
   <title></title>
  </head>
  <body> 
    <%  
        //获得gid  
        String gid = request.getParameter("gid");  
        request.setAttribute("gid", gid);
     %> 
   	<form action="${pageContext.request.contextPath }/judge?method=addJudge&gid=${gid }" method="post"> 
   	 请输入对该商品的评价: <br/>
   	<textarea cols="20" rows="10" name="textarea"> </textarea>
   	<input type="submit" value="提交"/>
   	</form>
  </body>
</html>
