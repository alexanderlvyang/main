<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style>
			body{
				background: lightgreen;
			}
			a{
				text-decoration: none;
				display: block;
				width: 100%;
				height: 30px;
				margin-top: 50px;
				text-align: center;
				line-height: 30px;
				color: black;
			}
			#logout{
				text-decoration: none;
				display: block;
				text-align: center;
				color:black;
				margin-top:20px;
				width:100px;
				height:50px;
				line-height:50px;
				margin-left:5%;
			}
			#productmanager{
				margin-top: 40%;
			}
			#productmanager{
				background: orange;
			}
			#ordermanager{
				background: greenyellow;
			}
			#report{
				background:hotpink;
			}
			#categorymanager{
				background:blue;
			}
			#brandmanager{
				background:purple;
			}
			#imageManager{
				background:#e22;
			}
			#sensitiveManager{
				background:orange;
			}
			#commentManager{
				background:yellow;
			}
		</style>
	
	</head>
	<body>
		<a href="logout.do" id='logout' target='_parent'>登出</a>
		<a href="find.do" id="productmanager" target="right">商品管理</a>
		<a href="categoryManager.do" id='categorymanager' target='right'>分类管理</a>
		<a href="brandManager.do" id='brandmanager' target='right'>品牌管理</a>
		<a href='imageManagerPage.do' id='imageManager' target='right'>图片管理</a>
		<a href='commentManager.do' id='commentManager' target='right'>评论管理</a>
		<a href='sensitiveManager.do' id='sensitiveManager' target='right'>敏感词管理</a>
		<a href="orderManager.do" id="ordermanager" target="right">订单管理</a>
		<a href="report.jsp" id="report" target="right">报表</a>
	</body>
</html>
