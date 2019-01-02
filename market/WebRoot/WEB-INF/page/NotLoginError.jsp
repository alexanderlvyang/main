<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'NotLoginError.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		.warning{
			margin-top:200px;
			text-align:center;
		}
		.warning p{
			font-size:50px;
			margin-top:30px;
			
		}
		.warning a{
			text-decoration:none;
			color:blue;
			margin-top:30px;
			font-size:30px;
		}
	</style>
  </head>
  
  <body>
  	<div class='warning'>
	    <p>请登录后重试</p>
	    <a href='customerLogin.do'>前往登录</a>
  	</div>
  </body>
</html>
