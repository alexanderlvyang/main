<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>欢迎登录</title>
	<style type="text/css">
		header{
			background-color: white;
			height: 100px;
			width: 100%;
			line-height: 100px;

		}
		header a img{
			width: 170px;
			height: 60px;
			margin-left: 150px;
			margin-top: 10px;
		}
		header span{
			width: 110px;
			height: 40px;
			display:inline-block;
			font-size: 25px;
			margin-left: 20px;
			margin-bottom: 30px;
		}
		.login{
			background-color: red;
			width: 100%;
			height: 479px;
		}
		.loginbg{
			width: 990px;
			height: 479px;
			margin-left: 200px;
		}
		.logindiv{
			background-color: white;
			width: 346px;
			height: 450px;
			border: 1px solid red;
			position: absolute;
			top: 125px;
			left: 850px;
		}
		.footul{
			width: 100%;
			height: 20px;
			text-align: center;
		}
		.footul ul{
			list-style: none;
			margin-left: 150px;
		}
		.footul ul li{
			float: left;
			margin-left: 10px;
		}
		.footul ul li a{
			text-decoration: none;
			color: black;
		}
		footer{
			position:absolute;
    		bottom:2px;
   	 		left:0px;
   	 		height: 20px;
   	 		padding-left: 500px;
		}
		#scanlogin{
			display: block;
			width: 173px;
			height: 55px;
			color: red;
			line-height: 55px;
			text-align: center;
			float: left;
			text-decoration: none;
		}
		#accountlogin{
			display: block;
			width: 173px;
			height: 55px;
			color: black;
			line-height: 55px;
			text-align: center;
			text-decoration: none;
			margin-left: 173px;
		}
		.loginchange a:hover{
			font-weight: 700;
			color:red;
		}
		.scanlogindiv img{
			width: 147px;
			height: 147px;
			margin-top:30px;
		}
		.scanlogindiv{
			text-align: center;
			width: 306px;
			height: 243px;
			margin-left: 20px;
			margin-top: 10px;

		}
		.accountlogindiv{
			text-align: center;
			width: 306px;
			height: 243px;
			margin-left: 20px;
			margin-top: 60px;
			display: none;
		}
		.accountlogindiv input{
			width: 265px;
			height: 38px;
			margin-top: 20px;
			margin-left: 38px;
		}
		.loginbtn{
			margin-top: 20px;
			display: block;
			width: 304px;
			height: 33px;
			background-color: red;
			text-decoration: none;
			line-height: 33px;
			color: black;
		}
		.forgetpassword{
			text-decoration: none;
			margin-left: 200px;
			margin-top: 5px;
			color: black;
			font-size: 16px;
		}
		.accountlabel{
			width: 38px;
			height: 38px;
			position: absolute;
			top:135px;
			left:18px;
		}
		label img{
			width: 38px;
			height: 38px;

		}
		.passwordlabel{
			width: 38px;
			height: 38px;
			position: absolute;
			top:193px;
			left:18px;
		}
	</style>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src='js/customerlogin.js'></script>
</head>
<body>
	<header><a href="homepage.jsp"><img src="image/logo-201305-b.png"></a><span>欢迎登录</span></header>
	<div class="login">
		<img src="image/5b6ce564N9215f95b.jpg" class="loginbg">
		<div class="logindiv">
				<div class="loginchange">
					<a href="javascript:void(0)" id="scanlogin" >扫码登陆</a>
					<a href="javascript:void(0)" id="accountlogin">账号登陆</a>
				</div>
				<div class="scanlogindiv">
					<img src="image/show.png">
					<p>打开手机京东 扫描二维码</p>
				</div>
				<div class="accountlogindiv">
					<form action="customerLoginOperation.do" method="post">
					<label class="accountlabel"><img src="image/account.png"></label>
					<input type="text" name="username" id="loginname"  placeholder="邮箱/用户名/已验证手机"><br>
					<label class="passwordlabel"><img src="image/password.png"></label>
					<input type="password" name="password" id="loginpwd" placeholder="密码"><br>
					<input type="submit" value="登陆" class="loginbtn">
					</form>
					<!-- <label class="accountlabel"><img src="image/account.png"></label><input id="loginname" type="text" class="itxt" name="loginname" tabindex="1" autocomplete="off" value="" placeholder="邮箱/用户名/已验证手机"><br>
					<label class="passwordlabel"><img src="image/password.png"></label>
					<input type="password" id="loginpwd" name="loginpwd" class="itxt itxt-error" tabindex="2" autocomplete="off" placeholder="密码"><br>
					<a href="javascript:;" class="forgetpassword">忘记密码</a>
					<a href="javascript:;" class="loginbtn">登&nbsp;&nbsp;&nbsp;&nbsp;录</a> -->
				</div>
		</div>
		
	</div>
	<div class="footul">
			<ul>
				<li><a href="#">关于我们</a></li>
				<li>|</li>
				<li><a href="#">联系我们</a></li>
				<li>|</li>
				<li><a href="#">人才招聘</a></li>
				<li>|</li>
				<li><a href="#">商家入驻</a></li>
				<li>|</li>
				<li><a href="#">广告服务</a></li>
				<li>|</li>
				<li><a href="#">手机京东</a></li>
				<li>|</li>
				<li><a href="#">友情链接</a></li>
				<li>|</li>
				<li><a href="#">销售联盟</a></li>
				<li>|</li>
				<li><a href="#">京东社区</a></li>
				<li>|</li>
				<li><a href="#">京东公益</a></li>
				<li>|</li>
				<li><a href="#">English Site</li>
			</ul>
	</div>
	<footer>Copyright &copy; 2004-2018  京东JD.com 版权所有</footer>
</body>
</html>