<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<link href="css/login.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
	<div class="logindiv">
		<table class="table">
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" placeholder="手机号/用户名" id="username"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td>
					<input type="text" name="captcha" maxlength="4" id="captcha">
				</td>
				<td>
					<img src="getImgCaptcha" width="100" height="40" id="captchaImage">
					<a href="javascript:void(0)" id="changeCaptcha">看不清，换一张</a>
				</td>
			</tr>
		</table>	
		<input type="button" value="登陆" id="loginButton">
		<a href="javascript:void(0)" id="getBackPassword">忘记密码？</a>
	</div>
</body>
</html>