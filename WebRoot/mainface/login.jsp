<%@ page language="java"  pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
   <title>login.jsp</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css">
  </head>
  <body>
  
	<div id="sky"></div>
	<div id="head"></div>
	<div id="middle">
		
		<form action="${pageContext.request.contextPath }/user" target="_parent" method="post">
			<input type="hidden" name="method" value="login"/>
			<ul style="text-align: center;">
				<li style="font-size: 48px">欢迎登陆!</li>
				<p style="color: red; font-weight: 900">${msg }</p>
				<li>
					<input class="name" name="username" placeholder="请输入用户名">
				</li>
				<li>
					<input type="password" name="password" class="pwd" placeholder="请输入密码">
				</li>
				<li>
					<input class="code" name="codename" placeholder="请输入验证码">
				</li>
				<span>
					<img id="img1" src="${pageContext.request.contextPath }/code"/>
					<a href="#" onclick="loadCode();">看不清，换一张</a>
				</span>
				
				<li><button id="login">立即登录</button></li>
			</ul>
		</form>
	</div>
	<div id="footer">
		<a>西安邮电大学</a>
	</div>
	<div id="cloud"></div>
  </body>
  <script type="text/javascript">
  	function loadCode() {
  		var img1 = document.getElementById("img1");
  		img1.src = "/petstore/code?"+new Date().getTime();
  	}
  </script>
</html>
