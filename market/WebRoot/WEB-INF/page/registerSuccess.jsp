<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'registerSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.registerSuccess{
			text-align:center;
			width:100%;
			height:500px;
			margin-top:300px;
		}
		#userName{
			margin-top:80px;
			font-size:30px;
			color:red;
		}
		#registerDetail{
			margin-top:50px;
			font-size:24px;
		}
	</style>
	<script type="text/javascript" src='js/jquery-3.3.1.min.js'></script>
	<script>
		$(function(){
		setInterval(function() {
			window.location.href='homepage.do';
		}, 6000);
		})
	</script>
  </head>
  
  <body>
    <%
    	String username=(String)request.getSession().getAttribute("userName");
     %>
     <div class='registerSuccess'>
     <p id='userName'><%=username %></p>
     <p id='registerDetail'>注册成功</p>
     </div>
  </body>
</html>
