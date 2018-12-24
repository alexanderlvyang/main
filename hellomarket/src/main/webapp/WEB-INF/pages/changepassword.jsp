<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#changeButton").click(function(){
			var newPassword=$("#newPassword").val();
			var reNewPassword=$("#reNewPassword").val();
			if(newPassword!=null&&reNewPassword!=null){
				if(newPassword==reNewPassword){
					$.ajax({
						type:"post",
						url:"updatePassword",
						data:{
							"newPassword":newPassword
						},
						success:function(data){
							if(data=="修改成功"){
								alert(data);
								window.location="loginfront"
							}else{
								alert(data);
							}
						},
						error:function(){
							alert("请求出错");
						}
					});
				}else{
					alert("密码不相同，请重新输入");
				}
			}else{
				alert("不能有空内容");
			}
		});
	});
</script>
<style>
	*{
		margin:0;
		padding:0;
	}
	body{
		background:url(images/changepassword-background.jpg) no-repeat;
		background-size:100% 100%;
		background-attachment:fixed;
	}
	.changePasswordTable{
		border-collapse: separate;
		border-spacing: 0px 20px;
	}
	.changePasswordDiv{
		font-size:20px;
		position:absolute;
		top:250px;
		left:450px;
	}
	input[type="password"]{
		width:250px;
		height:20px;
	}
	#changeButton{
		width:150px;
		height:25px;
		background-color:#e22;
		color:white;
		border:none;
		margin-top:50px;
	}
</style>
</head>
<body>
	<div class="changePasswordDiv">
		<table class="changePasswordTable">
			<tr>
				<td>新密码：</td>
				<td><input type="password" name="newPassword" id="newPassword"></td>
			</tr>
			<tr>
				<td>重复密码：</td>
				<td><input type="password" name="reNewPassword" id="reNewPassword"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="确定修改" id="changeButton"></td>
			</tr>
		</table>
	</div>
</body>
</html>