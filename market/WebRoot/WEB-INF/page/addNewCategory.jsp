<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addnewcategory.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		body{
			background-color:#666;
		}
		.addcategory{
			position:absolute;
			top:220px;
			left:370px;
			width:500px;
			height:300px;
			background-color:#999;
			text-align:center;
		}
		#categoryname{
			width:250px;
			height:40px;
			font-size:26px;
		}
		label{
			font-size:24px;
		}
		#form{
			margin-top:80px;
			
		}
		#submitbtn{
			width:300px;
			height:35px;
			font-size:24px;
			background-color:#e22;
			color:white;
			border:none;
			margin-top:40px;
		}
		#attribute{
			width:250px;
			height:40px;
			margin-left:44px;
			margin-top:10px;
		}
		p{
			margin-left:40px;
			font-size:10px;
			color:red;
			
		}
	</style>
  </head>
  
  <body>
  	<div class='addcategory'>
  	<form action="addCategory.do" method="post" id='form'>
    	<label>分类名称：</label><input type='text' name='category_name' id='categoryname'> <br>
    	<input type='submit' value='添加' id='submitbtn'> 
    </form>	
    </div>	
  </body>
</html>
