<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
var bar1 = new Q6MenuBar("bar1", "宠物商城");
function load() {
	bar1.colorStyle = 2;
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";
	bar1.config.radioButton=false;
	bar1.add("用户管理", "查看用户", "${pageContext.request.contextPath}/user?method=findAllUser", "body");
	
	bar1.add("分类管理", "查看分类", "${pageContext.request.contextPath}/category?method=findAlladminCategory", "body");
	bar1.add("分类管理", "添加分类", "<c:url value='/adminjsps/admin/category/add.jsp'/>", "body");

	bar1.add("商品管理", "查看商品", "${pageContext.request.contextPath}/goods?method=findAlladmin", "body");
	bar1.add("商品管理", "添加商品", "${pageContext.request.contextPath}/goods?method=disAddGoods", "body");

	bar1.add("订单管理", "所有订单", "${pageContext.request.contextPath}/order?method=findAllOrderAdmin", "body");
	bar1.add("订单管理", "未付款订单", "${pageContext.request.contextPath}/order?method=findOrderByState&state=1", "body");
	bar1.add("订单管理", "已付款订单", "${pageContext.request.contextPath}/order?method=findOrderByState&state=2", "body");

	var d = document.getElementById("menu");
	d.innerHTML = bar1.toString();
}
</script>

</head>

<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">
<div id="menu"></div>

</body>
</html>
