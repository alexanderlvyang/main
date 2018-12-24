<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
<link href="https://cdn.bootcss.com/flexslider/2.7.1/flexslider.min.css"
	rel="stylesheet">
<script type="text/javascript">
	$(function() {
		$('.flexslider').flexslider({
			directionNav : true,
			pauseOnAction : false,
			slideshowSpeed : 3000,
			slideshow : true,
			animationLoop : true
		});
		$('.advertise').flexslider({
			animation : "fade",
			controlNav : false,
			slideshowSpeed : 4000,
			animationLoop : true
		});
		$("#myorder").click(function(){
			var username=$("#username").html();
			if(typeof(username)=="undefined"){
				alert("请登录");
				window.location="loginfront";
			}else{
				$(this).attr("href","myOrder");
			}
		});

	});
</script>

<style type="text/css">
	* {
		margin: 0;
		padding: 0;
	}
	body{
		background:#f0f3ef;
	}
	a{
		text-decoration:none;
	}
	li {
		list-style: none;
	}
	
	.menu {
		width: 1000px;
		height: 460px;
		position: absolute;
		top: 204px;
		left: 100px;
		/* margin: 30px auto; */
	}
	
	.menu .menuTop {
		background-color: #F10215;
		color: #fff;
		width: 140px;
		height: 40px;
		padding-left: 20px;
		line-height: 40px;
		cursor: pointer;
	}
	
	.menu ul {
		width: 160px;
		background-color: #fff;
		border: 2px solid #F10215;
		box-sizing: border-box;
		position: relative;
	}
	
	.menu ul li {
		height: 30px;
		padding-left: 8px;
		text-align: left;
		line-height: 30px;
		font-size: 13px;
		background: url(image/1.png) no-repeat right;
		z-index: 2;
	}
	
	.menu ul li a {
		color: #7070770;
	}
	
	.menu ul li a:hover {
		color: red;
		text-decoration: underline;
		cursor: pointer;
	}
	
	.menu ul li:hover {
		border: 1px solid #DDD;
		border-right: 0;
		background-image: none;
	}
	
	.menu ul li:hover .submenu {
		display: block;
	}
	
	.menu ul li:hover span {
		width: 30px;
		height: 30px;
		display: inline-block;
		background-color: #FFF;
		float: right;
		z-index: 100;
		position: relative;
	}
	
	.menu ul li .submenu {
		position: absolute;
		left: 156px;
		top: 0;
		width: 520px;
		height: 330px;
		border: 1px solid #DDD;
		box-shadow: 0 0 8px #DDD;
		-moz-box-shadow: 0 0 8px #DDD;
		-webkit-box-shadow: 0 0 8px #DDD;
		background-color: #FFF;
		z-index: 3;
		display: none;
	}
	
	.menu ul li .submenu .subleft {
		margin-left: 0px;
		width: 400px;
		height: 300px;
		float: left;
		padding: 5px;
	}
	
	.menu ul li .submenu .subleft dl {
		overflow: hidden;
		border-bottom: 1px solid #D1D1D1;
		padding: 10px 0;
	}
	
	.menu ul li .submenu .subleft dl dt {
		float: left;
		height: 22px;
		line-height: 22px;
		margin-right: 10px;
		font-weight: bold;
		color: #707070;
		font-size: 12px;
		cursor: pointer;
	}
	
	.menu ul li .submenu .subleft dl dd a {
		display: block;
		float: left;
		border-left: 1px solid #707070;
		padding: 0 5px;
		color: #707070;
		height: 14px;
		line-height: 14px;
		margin: 3px 0;
		font-size: 11px;
	}
	
	.menu ul li .submenu .subright {
		width: 310px;
		height: 300px;
		background-color: blue;
		float: left;
	}
	
	.subleft dt a {
		color: black;
		text-decoration: none;
	}
	
	.subleft dd a {
		text-decoration: none;
	}
	
	a, img {
		border: 0;
	}
	
	.flexslider {
		position: relative;
		width: 800px;
		height: 398px;
		position: absolute;
		top: 200px;
		left: 280px;
		overflow: hidden;
	}
	
	.slides {
		position: relative;
		z-index: 1;
	}
	
	.slides li {
		height: 400px;
	}
	
	.textAdvertise .slides li {
		height: 20px;
	}
	
	.flex-control-nav {
		position: absolute;
		bottom: 10px;
		z-index: 2;
		width: 100%;
		text-align: center;
	}
	
	.flex-control-nav li {
		display: inline-block;
		width: 14px;
		height: 14px;
		margin: 0 5px;
		*display: inline;
		zoom: 1;
	}
	
	.flex-control-nav a {
		display: inline-block;
		width: 14px;
		height: 14px;
		line-height: 40px;
		overflow: hidden;
		cursor: pointer;
	}
	
	.flex-control-nav .flex-active {
		background-position: 0 0;
	}
	
	.flex-direction-nav {
		position: absolute;
		z-index: 3;
		width: 100%;
		top: 45%;
	}
	
	.flex-direction-nav li a {
		display: block;
		width: 50px;
		height: 50px;
		overflow: hidden;
		cursor: pointer;
		position: absolute;
	}
	
	.flex-direction-nav li a.flex-prev {
		left: 40px;
	}
	
	.flex-direction-nav li a.flex-next {
		right: 40px;
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
	
	#username {
		color: #EE30A7;
	}
	
	#logo {
		margin-top: 10px;
		margin-left: 100px;
	}
	
	.advertiseDiv {
		width: 200px;
		height: 400px;
		position: absolute;
		top: 204px;
		right: 45px;
	}
	
	.searchDiv {
		position: absolute;
		top: 50px;
		left: 400px;
	}
	
	#condition {
		width: 450px;
		height: 25px;
	}
	
	#searchButton {
		width: 100px;
		height: 28px;
		background: #e22;
		color: white;
		border: none;
		margin-left: 10px;
	}
	
	.textAdvertise {
		position: absolute;
		top: 100px;
		left: 400px;
	}
	.advertise {
		font-size:12px;
		width: 130px;
		height: 20px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		float:left;
	}
	.advertise a{
		color:#e22;
	}
	.content{
		color:#666;
	}
	.content:hover{
		color:#e22;
	}
	#register{
		color:#f10215;
	}
	.parentCategory{
		color:black;
	}
</style>
</head>
<body>
	<div class="header">
		<ul class="navigation">
			<c:if test="${empty sessionScope.user }">
				<li><a href="loginfront">请登录</a></li>
				<li><a href="registerfront" id="register">请注册</a></li>
			</c:if>
			<c:if test="${not empty sessionScope.user}">
				<li><a href="personInfo" id="username">${sessionScope.user.username }</a></li>
				<li><a href="outLogin">退出登陆</a></li>
			</c:if>
			<li><a href="shoppingCartPage">购物车</a></li>
			<li><a href="javascript:void(0)" id="myorder">我的订单</a></li>
		</ul>
	</div>
	<div id="logo">
		<a href="homePage"><img src="images/logo.jpg" width="150"
			height="150"></a>
	</div>
	<div class="searchDiv">
		<form action="search" method="post">
			<input type="text" name="condition" id="condition"><input
				type="submit" id="searchButton" value="搜索">
		</form>
	</div>
	<div class="textAdvertise">
		<div class="advertise">
			<ul class="slides">
				<c:forEach items="${textAdvertiseList }" var="advertise" begin="0"
					end="2">
					<li><a href="${advertise.advertise_link }" title="${advertise.advertise_content }"  target="_blank">${advertise.advertise_content }</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:forEach items="${textAdvertiseList }" var="advertise" begin="3"
			end="15">
			<a href="#"  class="content" title="${advertise.advertise_content }"  target="_blank">${advertise.advertise_content }</a>
		</c:forEach>
	</div>
	<div class="menu">
		<div class="menuTop">全部商品分类</div>
		<ul>
			<c:forEach items="${categoryList }" var="parentcategory">
				<c:if test="${parentcategory.grade==0 }">
					<li><a href="search?condition=${parentcategory.name }" class="parentCategory">${parentcategory.name }</a> <span></span>
						<div class="submenu">
							<div class="subleft">
								<c:forEach items="${categoryList }" var="childcategory">
									<c:if test="${childcategory.parentId==parentcategory.id }">
										<dl>
											<dt>
												<a href="search?condition=${childcategory.name }">${childcategory.name }</a>
											</dt>
											<c:forEach items="${categoryList }" var="grandchildcategory">
												<c:if
													test="${grandchildcategory.parentId==childcategory.id }">
													<dd>
														<a href="search?condition=${grandchildcategory.name }">${grandchildcategory.name }</a>
													</dd>
												</c:if>
											</c:forEach>
										</dl>
									</c:if>
								</c:forEach>
							</div>
						</div></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<!-- 轮播图 flexslider插件-->
	<div class="flexslider">
		<ul class="slides">
			<c:forEach items="${rotationList }" var="rotation">
				<%-- 	<li style="background:url(files/${rotation.chart_thumbnail}) 50% 0 no-repeat;"></li> --%>
				<li><a href="${rotation.chart_link }"><img
						src="files/${rotation.chart_thumbnail}"></a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="advertiseDiv">
		<c:forEach items="${imageAdvertiseList }" var="imageAdvertise">
			<a href="${imageAdvertise.advertise_link }"  target="_blank"><img
				src="files/${imageAdvertise.advertise_thumbnail }" width="150px"
				height="130px"></a>
		</c:forEach>
	</div>
</body>
</html>