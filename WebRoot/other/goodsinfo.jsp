<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
   <title>goodsinfo.jsp</title>
  <style type="text/css">
	body {
		font-size: 15pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 400px;
		height: 400px;
		text-align: center;
	}
	img{
		width: 350px;
		height: 380px;
	}
	ul{
		position: absolute;
		left: 430px;
		top:0px;
	}
	li {
		margin: 15px;
	}
	#mybutton1{
		width:130px;
		background:#dd182b;
		color:#fff;
		height:40px;
		position: absolute;
		top:300px;
	}
	iframe{
		position: absolute;
		left:800px;
		top:10px;
		width:450px;
		height:500px;
	}
	
</style>
  </head>
  <body>
  	<div>
   		<img src="${pageContext.request.contextPath }/${goods.image}" border="0"/>
 	</div>
  <ul>
    <li>商品名:${goods.gname }</li>
    <li>店铺名:${goods.storename }</li>
    <li>单价:${goods.price }元</li>
   	<li> <form action="${pageContext.request.contextPath }/cart?method=addCart&gid=${goods.gid}" method="post">
  		数量:    <input type="text" size="3" name="count"/><br/>
  		<button type="submit"  id="mybutton1">加入购物车</button>
  		 </form>
  	</li>
  </ul>
  
  <iframe  frameborder="0" src="${pageContext.request.contextPath }/judge?method=findJudge&gid=${goods.gid}"></iframe>
  
  </body>	
</html>
