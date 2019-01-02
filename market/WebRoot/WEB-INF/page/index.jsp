<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<title>登陆</title>
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script src="js/jquery-3.3.1.min.js"></script>
	<script>
	function formsubmit(){
		var username=$("#username").val();
		var password=$("#password").val();
		if(username==""||password==""){
			alert("用户名或密码不能为空");
			$("form").attr("action","#");
		}
		else{
			$("form").attr("action","login.do");
			}
		
	}
	function vrifying(id){
		var value=$("#"+id).val();
		if( value.match( /^[\u4E00-\u9FA5]{1,}$/) ){
		    alert("不能输入汉字，请重新输入");
		    $("#"+id).val("");
		}
	}
</script>
</head>
<header><img src="image/logintop.jpg" width="250" height="80"></header>
<body>
	<div class="logintable">
		<form action="login.do" method="post">
			用户名:<input type="text" name="username" id="username" onchange="vrifying('username')"><br>
			密&nbsp;&nbsp;&nbsp;码:<input type="password" name="password" id="password" onchange="vrifying('password')"><br>
			<input type="submit" value="登陆" id="submitbtn" onclick="formsubmit()">
		</form>
</div>
</body>
</html>