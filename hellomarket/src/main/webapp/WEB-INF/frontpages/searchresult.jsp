<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	$(function(){
		$('.advertise').flexslider({
			animation : "fade",
			controlNav : false,
			slideshowSpeed : 4000,
			animationLoop : true
		});
	});
</script>
<style type="text/css">
	*{
		margin:0;
		padding:0;
		list-style: none;
	}
	a{
		text-decoration:none;
	}
	body{
		background:#f0f3ef;
	}
	.skuItem a{
		color:#999;
	}
	.introduction{
		width: 220px;
		height: 20px;
		/* overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		float:left; */
	}
	.product a{
		font-size:14px;
	}
	.skuItem{
		width:70%;
		margin-top:100px;
		margin-left:300px;
		overflow:hidden;
	}
	.product{
		margin-left:10px;
		float:left;
		height:300px;
	}
	.lanren a {
		-webkit-transition: all 0.2s linear;
		-mos-transition: all 0.2s linear;
		-mz-transition: all 0.2s linear;
		-o-transition: all 0.2s linear;
		transition: all 0.2s linear;
	}
	
	.lanren a:hover {
		-webkit-transition: all 0.2s linear;
		-mos-transition: all 0.2s linear;
		-mz-transition: all 0.2s linear;
		-o-transition: all 0.2s linear;
		transition: all 0.2s linear;
	}
	
	.lanren {
		width: 1200px;
		margin: 0 auto; 
		/* margin-left:400px; */
	}
	
	#nav-2015 {
		padding-left: 210px;
		background: #E4393C;
		height: 40px;
		position: relative;
		z-index: 10;
	}
	
	#category-2015 {
		width: 210px;
		height: 40px;
		position: absolute;
		left: 0;
		top: 0;
		z-index: 20;
	}
	
	#category-2015 .ld {
		position: relative;
		width: 210px;
		height: 40px;
		line-height: 40px;
		background: #CD292B;
		cursor: pointer;
	}
	
	#category-2015 .ld h2 {
		font-size: 14px;
		color: #fff;
		padding-left: 20px;
		color: #fff
	}
	
	#category-2015 .ld b {
		display: block;
		width: 20px;
		height: 20px;
		position: absolute;
		right: 10px;
		top: 10px;
		background: url(http://demo.lanrenzhijia.com/2015/nav0508/images/arrow.gif) no-repeat 0 0;
	}
	
	#category-2015 #allsort {
		display: none;
		width: 206px;
		height: 402px;
		padding: 3px 0;
		position: absolute;
		left: 0;
		top: 38px;
		border: 2px solid #CD292B;
		background-color: #fafafa;
	}
	
	#category-2015 #allsort .item {
		width: 206px;
		height: 28px;
		border-bottom: 1px solid #fff;
		float: left;
	}
	
	#category-2015 #allsort .item span {
		height: 28px;
		line-height: 28px;
		width: 208px;
		z-index: 30;
	}
	
	#category-2015 #allsort .item span h3 {
		width: 197px;
		padding-left: 10px;
		position: absolute;
		font-size: 14px;
		font-weight: normal;
		color: #333;
	}
	
	#category-2015 #allsort .item span h3 a {
		color: #333;
		text-decoration: none;
	}
	
	#category-2015 #allsort .item span h3 a:hover {
		color: #E4393C;
		font-weight: bold;
	}
	
	#category-2015 #allsort .item .i-mc {
		display: none;
		width: 680px;
		height: 388px;
		padding: 10px;
		background: #fff;
		border: 1px solid #ddd;
		position: absolute;
		left: 206px;
		top: 0;
		z-index: 25;
	}
	
	#category-2015 #allsort .on h3 {
		border: solid #ddd;
		border-width: 1px 0;
		background: #fff;
		z-index: 40;
	}
	
	#category-2015.on .ld b {
		background: url(http://demo.lanrenzhijia.com/2015/nav0508/images/arrow.gif) no-repeat 0 -20px;
	}
	
	#category-2015.on #allsort {
		display: block;
	}
	
	#category-2015.on #allsort .on .i-mc {
		display: block;
	}
	
	#navitems-2015 {
		height: 40px;
	}
	
	#navitems-2015 li {
		float: left;
	}
	
	#navitems-2015 li a {
		display: inline-block;
		padding: 0 30px;
		height: 40px;
		line-height: 40px;
		color: #fff;
		font: 700 15px/40px "microsoft yahei";
		text-decoration: none;
	}
	
	#navitems-2015 li a:hover {
		color: #fff;
		background: #a40000;
	}
	dt {
		float: left;
		height: 22px;
		line-height: 22px;
		margin-right: 10px;
		font-weight: bold;
		color: #707070;
		font-size: 12px;
		cursor: pointer;
	}
	dl {
		overflow: hidden;
		border-bottom: 1px solid #D1D1D1;
		padding: 10px 0;
	}
	dt a {
		color: black;
		text-decoration: none;
	}
	dd a {
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
	.brand{
		display:block;
		width:156px;
		height:25px;
		border:1px solid #DDD;
		text-align:center;
		line-height:25px;
		float:left;
		color:#005aa0;
	}
	.brandDiv{
		border:1px solid #AAA;
		width:1200px;
		margin-top:20px;
		margin-left:75px;
	}
	#brandLabel{
		float:left;
		margin-left:155px;
	}
	.brands{
		margin-left:205px;
		width:820px;
		height:100px;
		overflow:auto;
		background:white
	}
	.brand:hover{
		border:1px solid #e22;
	}
	.advertiseDiv{
		position:absolute;
		top:400px;
		left:80px;
		width:200px;
		height:750px;
		border-right:1px solid #666;
		padding-right:10px;
	}
	.pageDiv{
		float:right;
		margin-right:100px;
	}
	.page{
		display:block;
		width:30px;
		height:30px;
		text-align:center;
		line-height:30px;
		background-color:#999;
		border:1px solid #ddd;
		color:#333;
		margin-right:5px;
	}
	.warningDiv{
		position:absolute;
		top:500px;
		left:500px;
	}
	.warning{
		font-size:40px;
		color:#333;
		font:oblique;
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
			<li><a href="javascript:void(0)">我的订单</a></li>
		</ul>
	</div>
	<div class="searchDiv">
		<form action="search" method="post">
			<input type="text" name="condition" id="condition" value="${sessionScope.condition }"><input
				type="submit" id="searchButton" value="搜索">
		</form>
	</div>
	<div class="textAdvertise">
		<div class="advertise">
			<ul class="slides">
				<c:forEach items="${textAdvertiseList }" var="advertise" begin="0"
					end="2">
					<li><a href="${advertise.advertise_link }" title="${advertise.advertise_content }" target="_blank">${advertise.advertise_content }</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:forEach items="${textAdvertiseList }" var="advertise" begin="3"
			end="15">
			<a href="#"  class="content" title="${advertise.advertise_content }"  target="_blank">${advertise.advertise_content }</a>
		</c:forEach>
	</div>
	<div id="logo">
		<a href="homePage"><img src="images/logo.jpg" width="100"
			height="100"></a>
	</div>
	<c:if test="${not empty warning }">
		<div class="warningDiv">
			<p class="warning">${warning }</p>
		</div>
	</c:if>
	<div class="lanren">
		<div id="nav-2015">
			<div id="category-2015" onMouseOver="this.className='on'" onmouseleave="this.className=''">
				<div class="ld">
					<h2>全部商品分类<b></b></h2>
				</div>
				<div id="allsort">
					<c:forEach items="${categoryList }" var="parentcategory">
						<c:if test="${parentcategory.grade==0 }">
							<div class="item" onMouseOver="this.className='item on'" onmouseleave="this.className='item'">
								<span><h3><a href="search?condition=${parentcategory.name }">${parentcategory.name }</a></h3></span>
								<div class="i-mc">
									<ul>
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
									</ul>
								</div>
							</div>
						</c:if>
					</c:forEach>	
				</div>
			</div>
			<div id="navitems-2015">
				<li><a href="#">首页</a></li>
				<li><a href="#">服装城</a></li>
				<li><a href="#">美食</a></li>
				<li><a href="#">团购</a></li>
				<li><a href="#">夺宝岛</a></li>
				<li><a href="#">闪购</a></li>
				<li><a href="#">金融</a></li>
				<li><a href="#">智能</a></li>
			</div>
		</div>
	</div>
	<div class="brandDiv">
		<label id="brandLabel">品牌：</label>
		<div class="brands">
			<c:forEach items="${brandList}" var="brand">
				<c:if test="${empty brand.brand_englishName}">
					<a href="search?condition=${brand.brand_name}" class="brand">${brand.brand_name}</a>
				</c:if>
				<c:if test="${not empty brand.brand_englishName}">
					<a href="search?condition=${brand.brand_name}" class="brand">${brand.brand_name}(${brand.brand_englishName })</a>
				</c:if>
			</c:forEach>
		</div>		
	</div>
	<div class="skuItem">
		<c:forEach items="${productInfoList }" var="productInfo">
			<div class="product">
				<a href="productDetail?productId=${productInfo.product_id }"><img src="files/${productInfo.product_thumbnail }" width="220px" height="220px"></a><br>
				<p>${productInfo.product_price }</p>
				<a href="productDetail?productId=${productInfo.product_id }">${productInfo.product_name }</a><br>
				<p class="introduction"><a href="productDetail?productId=${productInfo.product_id }">${productInfo.product_introduction }</a></p>
			</div>
		</c:forEach>
	</div>
	<div class="advertiseDiv">
		<c:forEach items="${imageAdvertiseList }" var="imageAdvertise">
			<a href="${imageAdvertise.advertise_link }"  target="_blank"><img
				src="files/${imageAdvertise.advertise_thumbnail }" width="200px"
				height="220px"></a>
		</c:forEach>
	</div>
	<div class="pageDiv">
		<c:forEach var="page" begin="1" end="${totalPage }">
			<a href="search?condition=${sessionScope.condition }&&startPage=${page}" class="page">${page }</a>
		</c:forEach>
	</div>
</body>
</html>