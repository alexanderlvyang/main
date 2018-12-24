	$(function(){
		$("#changeCaptcha").click(function(){
			$("#captchaImage").attr("src","getImgCaptcha?"+new Date().getTime());
		});
		$("#loginButton").click(function(){
			var username=$("#username").val();
			var password=$("#password").val();
			var captcha=$("#captcha").val();
			if(username!=""&&password!=""&&captcha!=""){
				$.ajax({
					type:"post",
					url:"verifyUser",
					data:{
						"username":username,
						"password":password,
						"captcha":captcha
					},
					success:function(data){
						if(data=="登陆成功"){
							window.location="homePage";
						}else{
							alert(data);
							window.location.reload();
						}
					},
					error:function(){
						alert("请求出错");
						window.location.reload();
					}
				});
			}else{
				alert("不能有空内容");
			}
		});
	$("#getBackPassword").click(function(){
		var username=$("#username").val();
		if(username!=""){
			$.ajax({
				type:"post",
				url:"verifyUsername",
				data:{
					"username":username
				},
				success:function(data){
					if(data=="用户名已被注册"){
						/*$.ajax({
							type:"post",
							url:"changePassword",
							data:{
								"username":username
							},
							success:function(data){
								
							},
							error:function(){
								alert("请求出错");
							}
						});*/
						alert("修改链接已发送至邮箱请查看，登陆后可修改密码");
						$.ajax({
							type:"post",
							url:"getBackPassword",
							data:{
								"username":username
							},
							success:function(data){
								
							},
							error:function(){
								alert("请求出错");
							}
						});
					}else{
						alert("账号不存在，请重新输入");
					}
				},
				error:function(){
					alert("请求出错");
				}
			});
			
		}else{
			alert("请输入用户名找回密码");
		}
		
	});
	

});