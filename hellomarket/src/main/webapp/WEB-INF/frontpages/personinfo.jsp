<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{
		margin:0;
		padding:0;
		list-style: none;
	}
	.header {
		border-bottom: 1px solid #ddd;
		background-color: #e3e4e5;
		width: 100%;
		height: 25px;
	}
	
	.navigation li {
		float: left;
		margin-left: 20px;
	}
	
	.navigation {
		float: right;
		margin-right: 150px;
	}
	
	.navigation li a {
		text-decoration: none;
		color: #999;
	}
	
	.navigation li a:hover {
		color: #e33333;
	}
	
	#headUsername {
		color: #EE30A7;
	}
	
	#logo {
		margin-top: 10px;
		margin-left: 100px;
	}
	body{
	}
	.personInfoDiv{
		text-align:left;
		width:50%;
		margin-left:400px;
	}
	hr{
		margin-top:10px;
	}
	.content{
		margin-left:50px;
		margin-top:10px;
	}
	#username{
		border:none;
		width:150px;
		font-size:25px;
		font-style: oblique;
	}
	#phone{
		border:none;
		font-size:16px;
		width:150px;
	}
	#email{
		border:none;
		font-size:16px;
		width:200px;
	}
	button{
		margin-left:10px;
		width:100px;
		border:none;
		background-color:#e22;
		color:white;
		
	}
	#status{
		position:absolute;
		font-size:14px;
		top:122px;
		left:520px;
		font-style:oblique;
	}
	.deleteButton{
		text-decoration:none;
		color:red;
		margin-left:10px;
	}
	.labelDiv{
		margin-top:60px;
	}
	.addressDiv{
		height:100px;
		overflow: auto;
	}
	.captcha{
		display:none;
		margin-left:50px;
	}
	#countDown{
		margin-left:10px;
	}
	.addDiv{
		width:500px;
		height:200px;
		text-align:center;
		border:1px solid red;
		position:absolute;
		top:280px;
		left:400px;
		background-color:#999;
		display:none;
	}
	.addDiv table{
		margin-left:100px;
		margin-top:10px;
		border-collapse:separate;
		border-spacing:5px 20px;
	}
</style>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var count;
		$("#changeUsername").click(function(){
			if($(this).html()=="修改"){
				$("#username").attr("readonly",false);
				$("#username").css("border","1px solid blue");
				$(this).html("确定");
			}else{
				var username=$("#username").val();
				$.ajax({
					type:"post",
					url:"updateUsername",
					data:{
						"username":username
					},
					success:function(data){
						alert(data);
						if(data=="修改成功"){
							$("#username").attr("readonly",true);
							$("#username").css("border","none");
							window.location.reload();
						}
					},
					error:function(){
						
					}
				});
			}
		});
		$("#changePhone").click(function(){
			if($(this).html()=="换绑手机号"){
				$.ajax({
					type:"post",
					url:"getCaptcha",
					success:function(data){
						$("#changePhone").css("background-color","#666");
						$("#changePhone").attr("disabled", true);
						$("#countDown").html("10");
						count=setInterval(function(){
							$("#countDown").html($("#countDown").html()-1);
							if($("#countDown").html()==0){
								clearInterval(count);
								$("#countDown").html("");
								$("#changePhone").css("background-color","#e22");
								$("#changePhone").css("cursor","pointer");
								$("#changePhone").attr("disabled", false);
							}
						},1000);
						$(".captcha").show();
					}
				});
			}else{
				var phone=$("#phone").val();
				if(phone.length<11){
					alert("手机号格式错误");
				}else{
					$.ajax({
						type:"post",
						url:"updatePhone",
						data:{
							"phone":phone
						},
						success:function(data){
							alert(data);
							if(data=="修改成功"){
								$("#phone").attr("readonly",true);
								$("#phone").css("border","none");
								window.location.reload();
							}
						}
					});
				}
			}
			
		});
		$("#confirmCaptcha").click(function(){
			var inputCaptcha=$("#captcha").val();
			$.ajax({
				type:"post",
				url:"verifyCaptcha",
				data:{
					"inputCaptcha":inputCaptcha
				},
				success:function(data){
					if(data=="right"){
						$("#phone").attr("readonly",false);
						$("#phone").css("border","1px solid blue");
						$("#changePhone").html("确定");
						$(".captcha").hide();
						$("#changePhone").css("background-color","#e22");
						$("#changePhone").css("cursor","pointer");
						$("#changePhone").attr("disabled", false);
						$("#countDown").html("");
						clearInterval(count);
					}else{
						alert(data);
					}
				}
			});
		});
		$("#changeEmail").click(function(){
			if($(this).html()=="换绑邮箱"){
				$("#email").attr("readonly",false);
				$("#email").css("border","1px solid blue");
				$(this).html("确定");
			}else{
				var username=$("#email").val();
				$.ajax({
					type:"post",
					url:"updateEmail",
					data:{
						"email":username
					},
					success:function(data){
						alert(data);
						if(data=="修改成功"){
							$("#email").attr("readonly",true);
							$("#email").css("border","none");
							window.location.reload();
						}
					},
					error:function(){
						
					}
				});
			}
		});
		$(".deleteButton").click(function(){
			var addressId=$(this).attr("addressId");
			$.ajax({
				type:"post",
				url:"deleteAddress",
				data:{
					"addressId":addressId
				},
				success:function(data){
					alert(data);
					window.location.reload();
				}
			});
		});
		$("#add").click(function(){
			$(".addDiv").show();
		});
		$("#cancel").click(function(){
			$(".addDiv").hide();
		});
		$("#confirm").click(function(){
			var addressName=$("#addName").val();
			var addressPhone=$("#addPhone").val();
			var addressAddress=$("#addAddress").val();
			if(addressPhone.length<11){
				alert("手机号格式错误");
			}else{
					$.ajax({
						type:"post",
						url:"addAddressee",
						data:{
							"addressee_name":addressName,
							"addressee_phone":addressPhone,
							"addressee_address":addressAddress
						},
						success:function(data){
							if(data=="添加成功"){
								alert(data);
								window.location.reload();
							}else{
								alert(data);
							}
						}
					});
				}
		});
	});
</script>
</head>
<body>
	<div class="header" productId="${productInfoList[0].product_id}">
		<ul class="navigation">
			<c:if test="${empty sessionScope.user }">
				<li><a href="loginfront">请登录</a></li>
				<li><a href="registerfront" id="register">请注册</a></li>
			</c:if>
			<c:if test="${not empty sessionScope.user}">
				<li><a href="javascript:void(0)" id="headUsername" >${sessionScope.user.username }</a></li>
				<li><a href="outLogin">退出登陆</a></li>
			</c:if>
			<li><a href="shoppingCartPage">购物车</a></li>
			<li><a href="javascript:void(0)">我的订单</a></li>
		</ul>
	</div>
	<div id="logo">
		<a href="homePage"><img src="images/logo.jpg" width="100"
			height="100"></a>
	</div>
	<div class="personInfoDiv">
		<input type="text" id="username" value="${user.username }" readonly="readonly"><button id="changeUsername">修改</button>
		<c:if test="${user.status=='VIP' }">
			<label id="status" style="color:gold">${user.status }</label><br>
		</c:if>
		<c:if test="${user.status!='VIP' }">
			<label id="status" >${user.status }</label><br>
		</c:if>
		<hr>
		<div class="labelDiv">
			<label>手机号：</label><p class="content"><input type="text" value="${user.phone }" id="phone" maxlength="11" readonly="readonly"><span id="countDown"></span><button id="changePhone">换绑手机号</button></p>	
			<div class="captcha"><label>输入收到的验证码:</label><input type="text" id="captcha"><button id="confirmCaptcha">确定</button></div>
		</div>
		<div class="labelDiv">	
			<label>邮箱：</label><p class="content"><input type="text" value="${user.email }" id="email" readonly="readonly"><button id="changeEmail">换绑邮箱</button></p>
		</div>
		<div class="labelDiv">
			<label>收件人信息：</label><button id="add">添加收件人</button>
			<div class="addressDiv">
				<c:forEach items="${addressList }" var="address">
					<p class="content">${address.addressee_name } ${address.addressee_phone } ${address.addressee_address } <a href="javascript:void(0)" addressId=${address.addressee_id } class="deleteButton">删除</a></p>		
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="addDiv">
		<table>
			<tr>
				<td>收件人姓名：</td>
				<td><input type="text" id="addName"></td>
			</tr>
			<tr>
				<td>收件人手机：</td>
				<td><input type="text" id="addPhone" maxlength="11"></td>
			</tr>
			<tr>
				<td>收件人地址：</td>
				<td><input type="text" id="addAddress"></td>
			</tr>
		</table>
		<button id="confirm">确定</button><button id="cancel">取消</button>
	</div>
</body>
</html>