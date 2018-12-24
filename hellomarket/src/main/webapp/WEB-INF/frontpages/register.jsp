<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/register.js"></script>
<link rel="stylesheet" href="css/register.css">
</head>
<body>
	<div class="phoneCaptchaDiv">
		<label>手机号：</label><input type="text" name="phone" maxlength="11" id="phone"><br>
		<label>验证码：</label><input type="text" name="captcha" maxlength="6" id="captcha">
		<span id="countDown"></span><input type="button" id="getCaptcha" value="获取验证码"><br>
		<input type="button" value="下一步" id="nextButton">
	</div>		
	<div class="registerDiv">
		<table class="registerTable">
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="username" id="username"></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>重复密码:</td>
				<td><input type="password" name="repassword" id="repassword"></td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td>
					<input type="email" name="email" id="email">
					<span id="emailCountDown"></span><input type="button" value="发送验证码" id="getEmailCaptcha">
				</td>
			</tr>
			<tr>
				<td>验证码:</td>
				<td><input type="text" name="emailCaptcha" id="emailCaptcha"></td>
			</tr>
		</table>
		<input type="button" value="注册" id="registerButton">
	</div>
</body>
</html>