<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<style type="text/css">
		header{
			width: 100%;
			height: 76px;
		}
		header img{
			width: 160px;
			height: 50px;
			float: left;
			margin-left: 30px;
			margin-top: 14px;
			}
		header .welcome{
			height: 34px;
			margin-top: 34px;
			color: #333;
			float: left;
			font-size: 24px;
			line-height: 34px;
			margin-left: 10px;
		}
		header .gologin{
			margin-top: 44px;
			float: right;
			color: #999;
			margin-right: 50px;

		}
		.gologin a{
			color:#e22;
		}
		hr{
			width: 100%;
			height: 3px;
			background-color: #999;
		}
		.registerdiv{
			width: 100%;
			height: 450px;
		}
		.vrifydiv{
			float: left;
			height: 55px;
			width: 85px;
			font-size: 14px;
			text-align: center;
		}
		.vrifydiv span{
			display: block;
			width: 24px;
			height: 24px;
			border-radius: 12px;
			line-height: 24px;
			background-color: #666;
			color: white;
			text-align: center;
			margin-left: 30px;
		}
		.vrifydiv p{
			display: block;
		}
		.sign{
			float: left;
			color: 14px;

		}
		.registerheader{
			width: 400px;
			margin: 80px auto 0;
			height: 55px;
		}
		#areacode{
			display: block;
			text-decoration: none;
			color: #666;
			border: 1px solid red;
			width: 108px;
			height: 52px;
			line-height: 52px;
			text-align: center;
			float: left;
			font-size: 16px;
		}
		#phonenumber{
			width: 290px;
			height: 48px;
			padding-left:15px;
			font-size: 20px;

		}
		#verifycode{
			width: 180px;
			height: 48px;
			float: left;
		}
		#verifyimg{
			width: 118px;
			height: 54px;
			float: left;
		}
		#changecaptcha{
			text-decoration: none;
			color: blue;
			position: absolute;
			top: 430px;
		}
		.phonenumberdiv{
			margin-left: 430px;
			margin-top: 50px;
		}
		#next{
			display: block;
			width: 390px;
			height: 45px;
			background-color: #e22;
			color: white;
			text-decoration: none;
			text-align: center;
			line-height: 45px;
			margin-top: 70px;
			margin-left: 436px;

		}
		.vrifycodediv{
			width: 419px;
			height: 54px;
			margin-top: 60px;
			margin-left: 428px;
		}
		#first{
			background-color:lightgreen;
		}
		.cotentdiv{
			display:block;
		}
		.phonecategory{
			width:100%;
			height:60px;
			margin-top:80px;
			text-align:center;
			display:none;
		}
		#phonecategory{
			width:250px;
			height:30px;
			font-size:24px;
			padding-left:5px;
		}
		lable{
			font-size:24px;
		}
		#sure{
			display:block;
			width:250px;
			height:30px;
			font-size:24px;
			text-align:center;
			line-height:30px;
			background-color:#e22;
			color:white;
			margin-top: 70px;
			margin-left: 520px;
			text-decoration:none;
		}
		#resend{
			display:none;
			text-decoration:none;
		}
		.completeAccountMessage{
			width:100%;
			height:300px;
			text-align:center;
			margin-top:80px;
			display:none;
		}
		.completeAccountMessage label{
			font-size:24px;
		}
		#userName{
			width:250px;
			height:30px;
			margin-left:24px;
			font-size:24px;
			margin-top:20px;
		}
		#password{
			width:250px;
			height:30px;
			font-size:24px;
			margin-top:15px;
		}
		#confirmPassword{
			width:250px;
			height:30px;
			font-size:24px;
			margin-top:15px;
		}
		#immediatelyRegister{
			width:200px;
			height:35px;
			font-size:24px;
			color:white;
			background-color:#e22;
			border:none;
			margin-top:30px;
		}
	</style>
	<script type="text/javascript" src='js/jquery-3.3.1.min.js'></script>
	<script type="text/javascript">
   $(function(){
   	$("#verifyimg").attr("src","getCaptcha.do?"+new Date().getMilliseconds());
   })
	function changecaptcha(){
		$("#verifyimg").attr("src","getCaptcha.do?"+new Date().getMilliseconds());
	}
	function getcode(){
		var captcha=$("#verifycode").val();
		var phonenumber=$("#phonenumber").val();
		var captcha=$("#verifycode").val();
		if(captcha==null||phonenumber==null||phonenumber.length<11){
			alert("手机号或验证码为空");
		}else{
			$.ajax({
				type:'post',
				url:'CaptchaVrify.do',
				data:{
					"captcha":captcha
				},
				success:function(data){
					if(data=='正确'){
						$.ajax({
								type:'post',
								url:'judgmentPhoneNumber.do',
								data:{
									"phoneNumber":phonenumber
									},
								success:function(data){
									if(data=="存在"){
										alert("手机号已注册，请登录");
										window.location.href='customerLogin.do';
									}else{
											$.ajax({
													type:'post',
													url:'getCode.do',
													data:{
														"phonenumber":phonenumber,
														"captcha":captcha
													},
													success:function(data){
															$("#resend").hide();
															$("#timing").show();
															$(".cotentdiv").css("display","none");
															$(".phonecategory").css("display","block");
															var time=60;
															var settime=setInterval(function() {
																$("#timing").html(time);
																time=time-1;
																if(time==0){
																	clearInterval(settime);
																	$("#timing").hide();
																	$("#resend").show();
																}
															}, 1000);
													},
													error:function(){
														alert("wrong");
													}
													
												});
									
									}
								},
								error:function(){
									alert("wrong");
								}
							});
					}else{
						alert("验证码错误请重新输入");
						$("#verifycode").val("");
					}
				}
			})
			
	
		}
	}
	function judgmentcaptcha(){
		$.ajax({
			type:'post',
			url:'judgmentCaptcha.do',
			data:{
				"phoneCaptcha":$("#phonecategory").val()
			},
			success:function(data){
				if(data=="success"){
					$(".phonecategory").hide();
					$(".completeAccountMessage").show();
					$("#second").css("background-color","lightgreen");
					$("#first").css("color","white");
					$("#first").css("background-color","#666");
				}else{
					alert("验证码错误");
				}
			},
			error:function(){
				alert("wrong");
			}
		})
	}
	function judgmentPhone(){
		var phoneNumber=$("#phonenumber").val();
		if(isNaN(phoneNumber)||phoneNumber.length<11){
			alert("手机号不合法");
		}else{
			$.ajax({
				type:'post',
				url:'judgmentPhoneNumber.do',
				data:{
					"phoneNumber":phoneNumber
					},
				success:function(data){
					if(data=="存在"){
						alert("手机号已注册，请登录");
						window.location.href='customerLogin.do';
					}
				},
				error:function(){
					alert("wrong");
				}
			})
		}
	}
		
	</script>
</head>
<body>
	<header>
    	<img src='image/logo-201305-b.png'><div class="welcome">欢迎注册</div>
    	<div class="gologin">
    		已有账号？<a href="customerLogin.do">去登录></a>
    	</div>
    </header>
    <hr>
    <div class="registerdiv">
    	<div class="registerheader">
	    	<div class='vrifydiv'>
	    		<span id='first'>1</span>
	    		<p>验证手机号</p>
	    	</div>
	    	<div class="sign">············></div>
	    	<div class="vrifydiv">
	    		<span id='second'>2</span>
	    		<p>填写账号信息</p>
	    	</div>
	    	<div class="sign">············></div>
	    	<div class="vrifydiv">
	    		<span id='third'>3</span>
	    		<p>注册成功</p>
    		</div>
    	</div>
    	<div class="cotentdiv">
    		<div class="phonenumberdiv"><a href="javscript:void(0)" id="areacode">中国 0086</a><input type="text" name="phonenumber" id="phonenumber" onblur="judgmentPhone()" maxlength="11"></div>
    		<div class="vrifycodediv">
    			<input type="text" name="vrifynumber" id="verifycode">
    			<img src="" id="verifyimg">
    			<a href="javascript:void(0)" onclick="changecaptcha()" id="changecaptcha">看不清，换一张</a>
    		</div>
    		<a href="javascript:void(0)" id="next" onclick='getcode()'>下一步</a>
    	</div>
    	<div class="phonecategory">
    		<lable>输入您收到的验证码：</lable><input type='text' name='phonecategory' id='phonecategory'><span id='timing'></span><a href='javascript:void(0)' onclick="getcode()" id='resend'>重新发送</a><br>
    		<a href='javascript:void(0)' id='sure' onclick='judgmentcaptcha()'>确定</a>
    	</div>
    	<div class='completeAccountMessage'>
    	<form action='addNewCustomer.do' method='post'>
    		<label>用户名</label><input type='text' name='userName' id='userName'><br>
    		<label>设置密码</label><input type='password' name='password' id='password'><br>
    		<label>确认密码</label><input type='password' name='confirmPassword' id='confirmPassword'><br>
    		<input type='submit' value='立即注册' id='immediatelyRegister'>
    		</form>
    	</div>
    </div>
    
</body>
</html>