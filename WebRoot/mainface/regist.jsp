<%@ page language="java"  pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
  <head>
   <title>regist.jsp</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css">
  </head>
  <body>
   <div id="sky"></div>
	<div id="head"></div>
	<div id="middle">
		<form action="${pageContext.request.contextPath }/user" method="post">
			<input type="hidden" name="method" value="regist"/>
			<ul style="text-align: center;">
				<li style="font-size: 48px">欢迎注册!</li>
				<p style="color: red; font-weight: 900">${msg }</p>
				<li>
					<input class="name" name="username" placeholder="请输入用户名">
				</li>
				<li>
					<input type="password" name="password" class="pwd" placeholder="请输入密码">
				</li>
				<li>
					<input class="addr" name="addrname" placeholder="请输入家庭地址">
				</li>
				<li><button id="login">立即注册</button></li>
			</ul>
		</form>
	</div>
	<div id="footer">
		<a>西安邮电大学</a>
	</div>
	<div id="cloud"></div>
  </body>
</html>
