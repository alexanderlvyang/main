$(function(){
	$("#getCaptcha").click(function(){
		var phone=$("#phone").val();
		if(phone!=""){
		$.ajax({
			type:"post",
			url:"verifyPhone",
			data:{
				"phone":phone
			},
			success:function(data){
				if(data=="手机号已被注册"){
					alert(data);
				}else{
					$.ajax({
						type:"post",
						url:"getCaptcha",
						success:function(data){
							alert(data);
							$("#getCaptcha").css("background-color","#666");
							$("#getCaptcha").css("cursor","not-allowed");
							$("#getCaptcha").attr("disabled", true);
							$("#countDown").html("10");
							var count=setInterval(function(){
								$("#countDown").html($("#countDown").html()-1);
								if($("#countDown").html()==0){
									clearInterval(count);
									$("#countDown").html("");
									$("#getCaptcha").css("background-color","white");
									$("#getCaptcha").css("cursor","pointer");
									$("#getCaptcha").attr("disabled", false);
								}
							},1000);
						},
						error:function(){
							alert("请求出错");
						}
					});
				}
			},
			error:function(){
				alert("请求出错");
			}
		});
		}else{
			alert("手机号不能为空");
		}
	});
	$("#nextButton").click(function(){
		if($("#phone").val()!="" && $("#captcha").val()!="" && $("#captcha").val().length==6 && $("#phone").val().length==11){
			$.ajax({
				type:"post",
				url:"verifyCaptcha",
				data:{
					"inputCaptcha":$("#captcha").val()
				},
				success:function(data){
					if(data=="right"){
						$(".phoneCaptchaDiv").css("display","none");
						$(".registerDiv").css("display","block");
					}else{
						alert(data);
					}
				},
				error:function(){
					alert("验证错误");
				}
			});
		}else{
			alert("请输入手机号或验证码");
		}
	});
	$("#getEmailCaptcha").click(function(){
		var email=$("#email").val();
		if(email!=""){
			if(email.indexOf("@")>0&&email.indexOf(".com")>0){
				$("#emailCountDown").html("10");
				$("#getEmailCaptcha").css("background-color","#666");
				$("#getEmailCaptcha").css("cursor","not-allowed");
				$("#getEmailCaptcha").attr("disabled", true);
				var count=setInterval(function(){
					$("#emailCountDown").html($("#emailCountDown").html()-1);
					if($("#emailCountDown").html()==0){
						clearInterval(count);
						$("#emailCountDown").html("");
						$("#getEmailCaptcha").css("background-color","white");
						$("#getEmailCaptcha").css("cursor","pointer");
						$("#getEmailCaptcha").attr("disabled", false);
					}
				},1000);
				$.ajax({
					type:"post",
					url:"sendEmail",
					data:{
						"emailAddress":email,
					},
					success:function(data){
						alert(data);
					},
					error:function(){
						alert("请求错误");
					}
				});
			}else{
				alert("邮箱格式错误，请重新输入");
			}
		}else{
			alert("邮箱不能为空");
		}
	});
	$("#registerButton").click(function(){
		var phone=$("#phone").val();
		var username=$("#username").val();
		var password=$("#password").val();
		var email=$("#email").val();
		var repassword=$("#repassword").val();
		var emailCaptcha=$("#emailCaptcha").val();
		if(emailCaptcha==""||username==""||password==""||repassword==""||email==""){
			alert("不能有空内容");
		}else{
			if(password!=repassword){
				alert("两次密码不相同请重新输入");
			}else{
				$.ajax({
					type:"post",
					url:"verifyUsername",
					data:{
						"username":username
					},
					success:function(data){
						if(data=="用户名已被注册"){
							alert(data);
						}else{
							$.ajax({
								type:"post",
								url:"verifyEmailCaptcha",
								data:{
									"inputEmailCaptcha":emailCaptcha
								},
								success:function(data){
									if(data=="验证成功"){
										$.ajax({
											type:"post",
											url:"register",
											data:{
												"phone":phone,
												"username":username,
												"password":password,
												"email":email
											},
											success:function(data){
												alert(data);
												if(data=="注册成功"){
													window.location="loginfront";
												}else{
													window.location="errorpage";
												}
											},
											error:function(){
												alert("请求出错");
											}
										});
									}else{
										alert("验证码不正确，请重新输入");
									}
								},
								error:function(){
									alert("请求出错");
								}
							});
						}
					},
					error:function(){
						alert("请求出错");
					}
				});
			}
		}
	});
	
});