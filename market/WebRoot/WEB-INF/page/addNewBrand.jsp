<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addNewBrand.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		.inputtext{
			width:250px;
			height:30px;
			
		}
		.addBox{
			position:absolute;
			top:220px;
			left:370px;
			width:500px;
			height:300px;
			background-color:#999;
			text-align:center;
			
		}
		#brandName{
			width:250px;
			height:30px;
			margin-top:100px;
			margin-left:31px;
		}
		#brandEnglishName{
			width:250px;
			height:30px;
			margin-top:20px;
		}
		#addButton{
			width:300px;
			height:35px;
			font-size:24px;
			background-color:#e22;
			color:white;
			border:none;
			margin-top:20px;
		}
	</style>
  </head>
  
  <body>
  <div class='addBox'>
    <form action='addNewBrandOperation.do' method='post'>
    	<label>品牌名称：</label><input type='text' name='brandName'  id='brandName'><br>
    	<label>品牌英文名称：</label><input type='text' name='brandEnglishName' id='brandEnglishName'><br>
    	<input type='submit' value='添加' id='addButton'>
    </form>
   </div> 
  </body>
</html>
