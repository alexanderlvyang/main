<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="javabean.Product" %>
<%@page import="javabean.Brand" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchresult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		*{
			margin:0px;
			padding:0px;
			font-size: 14px;
		}
		body{
			background-color: #ccc;
		}
		.header{
			height: 30px;
			line-height: 30px;
			border-bottom: 1px solid #ddd;
    		background-color: #e3e4e5;
		}
		.header li{
			list-style: none;
		}
		.logo{
			position: absolute;
			top: 0px;
			left: 60px;
			width: 190px;
			height: 170px;
		}
		.logo_image_a{
			display: block;
			width: 190px;
			height: 170px;
			background-image: url(../image/sprite.head.png);
		}
		.header li{
			float: left;
		}
		.header ul{
			margin-left: 280px;
		}
		.address>a{
			float: left;
			text-decoration: none;
			margin-left: 30px;
			margin-top: 5px;
			color:#666;
		}
		.address{
			position: absolute;
			top: 31px;
			left: 280px;
			width: 320px;
			height: 300px;
			background-color: white;
			border:1px solid #999;
			display: none;
			z-index: 2;
		}
		.chooseaddress{
			height: 30px;
			line-height: 30px;
			text-align: center;
			color:#666;
		}
		li{
			color:#666;
		}
		.login{
			margin-left: 220px;

		}
		li a{
			text-decoration: none;
			color:#666;
		}
		.login>a{
			color: #666;
		}
		.register{
			margin-left: 20px;

		}
		.register>a{
			color: red;
		}
		.line{
			margin-left: 20px;
		}
		.order{
			margin-left: 20px;
		}
		.myjd{
			margin-left: 20px;
		}
		.jdvip{
			margin-left: 20px;
		}
		.qycg{
			margin-left: 20px;
		}
		.jd>a{
			float: left;
			text-decoration: none;
			margin-right: 50px;
			margin-top: 20px;
			color:#666;
		}
		.jd{
			position: absolute;
			top: 31px;
			left: 856px;
			border:1px solid #999;
			background-color: #fff;
			width: 250px;
			height: 250px;
			display: none;
			z-index: 2;
		}
		.enterprisebuy{
			position: absolute;
			top: 31px;
			left: 1077px;
			border:1px solid #999;
			background-color: #fff;
			width: 180px;
			height: 80px;
			display: none;
		}
		.enterprisebuy a{
			float: left;
			text-decoration: none;
			margin-right: 20px;
			margin-top: 5px;
			color:#666;
		}
		.search_btn{
			display: block;
			width: 50px;
			height: 35px;
			background-color: red;
		}
		#product_name{
			width: 600px;
			height: 35px;
		}
		.searchdiv{
			position: absolute;
			top: 50px;
			left: 350px;
		}
		#searchbtn{
			display: block;
			width: 50px;
			height: 35px;
			position: absolute;
			top: 1px;
			left: 620px;
			background-color: red;
			text-align: center;
			line-height: 45px;
		}
		#search_photo{
			position: absolute;
			top: 10px;
			left: 550px;
		}
		.product{
			float:left;
			text-align:center;
			margin-left:10px;
			width:200px;
			height:300px;
		}
		.product a{
			text-decoration:none;
			color:red;
		}
		.productbox{
			position:absolute;
			top:250px;
			left:264px;
			border:1px solid red;
			background-color:yellow;
			width:1083px;
		}
		.buycar{
			display:block;
			text-decoration:none;
			color:red;
			background-color:white;
			text-align:center;
			width:150px;
			height:30px;
			line-height:30px;
			position:absolute;
			top:56px;
			right:100px;
		}
		.brand{
			width:900px;
			height:100px;
			margin-top:110px;
			margin-left:265px;
			border:1px solid red;
			background-color:white;
		}
		.label{
			font-size:24px;
		}
		.brandlabel a{
			text-decoration:none;
			color:blue;
			margin-left:10px;
		}
		.introduction{
			width:200px;
			text-overflow:ellipsis;
		}
	</style>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script>
		function addproductToBuycar(id){
			$.ajax({
				type:"post",
				url:"addBuycar.do",
				data:{
					"product_id":id
				},
				success:function(data){
					alert(data);
				},
				error:function(){
					alert("wrong");
				}
			})
		}
		function searchproduct(){
			$("#form").submit();
		}
	</script>
  </head>
  
  <body>
    <div class="header">
		<ul>
			<li>
				<div class="chooseaddress" onmousemove="showaddress()" onmouseleave="hidenaddress()">
					<img src="image/address.png" width="20px" height="20px">
					<span id="position">北京</span>
				</div>
			</li>
			<li class="login"><a href="login.do"><span id="customername"></span></a></li>
			<li class="register"><a href="javascript:void(0)" id="register">免费注册</a></li>
			<li class="line">|</li>
			<li class="order"><a href="">我的订单</a></li>
			<li class="line">|</li>
			<li class="myjd">
				<div class="myjddiv">
				<a href="#">我的京东</a>
				<img src="image/bottomarrow.png" width="18px" height="18px">
				</div>
			</li>
			<li class="line">|</li>
			<li class="jdvip"><a href="#">京东会员</a></li>
			<li class="line">|</li>
			<li class="qycg">
				<div class="qycgdiv">
				<a href="#">企业采购</a>
				<img src="image/bottomarrow.png" width="18px" height="18px">
				</div>
			</li>
		</ul>
	</div>
	<div class="address">
		<a href="#" id="bj" onclick="chooseposition(id)">北京</a>
		<a href="#" id="sh" onclick="chooseposition(id)">上海</a>
		<a href="#" id="sz" onclick="chooseposition(id)">深圳</a>
		<a href="#" id="gz" onclick="chooseposition(id)">广州</a>
		<a href="#" id="xm" onclick="chooseposition(id)">厦门</a>
		<a href="#" id="hz" onclick="chooseposition(id)">杭州</a>
		<a href="#" id="cq" onclick="chooseposition(id)">重庆</a>
		<a href="#" id="cd" onclick="chooseposition(id)">成都</a>
		<a href="#" id="jl" onclick="chooseposition(id)">吉林</a>
	</div>
	<div class="jd" >
		<a href="#" id="dcldd" >待处理订单</a>
		<a href="#" id="message" >消息</a>
		<a href="#" id="backgoods" >返修退换货</a>
		<a href="#" id="myqueation" >我的问答</a>
		<a href="#" id="depreciate" >降价商品</a>
		<a href="#" id="myfollow" >我的关注</a>
		<a href="#" id="myjdbean" >我的京豆</a>
		<a href="#" id="mycoupon" >我的优惠券</a>
		<a href="#" id="mybt" >我的白条</a>
		<a href="#" id="mylc" >我的理财</a>
	</div>
	<div class="enterprisebuy">
		<a href="#" id="qyg">企业购</a>
		<a href="#" id="sycjg">商用场景馆</a>
		<a href="#" id="gyp">工业品</a>
		<a href="#" id="lpk">礼品卡</a>
	</div>
	<div class="logo" >
		<a href="homepage.do" class="logo_image_a"><img src='image/sprite.head.png' width='190' height='170'></a>
	</div>
	<div class="searchdiv">
		<form action="searchResult.do" method="post" id="form">
		<input type="text" name="product_name" id="product_name">
		<a href="#"><img src="image/photo.png" id="search_photo" width="19" height="15"></a>
		<a href="javascript:void(0)" id="searchbtn" onclick="searchproduct()"><img src="image/searchbtn.png" width="20" height="21"></a>
	</form>
	</div>
	<a href="car.do" class='buycar'>购物车</a>
	<div class="productbox">
	<%
		List<Product> productlist=(List<Product>)request.getAttribute("productList");
		int size=(int)request.getAttribute("productListSize");
		for(int i=0;i<size;i++){
	 %>
	 <div class='product'>
	 <a href="productDetail.do?product_id=<%=productlist.get(i).getProduct_id()%>"><img src="<%=productlist.get(i).getProduct_thumbnail()%>" width='200px' height='200px'></a>
	 <p><%=productlist.get(i).getProduct_price() %></p>
	 <p><%=productlist.get(i).getProduct_name() %></p>
	 <p style="width:200px; white-space:nowrap; text-overflow:ellipsis; overflow:hidden;"><%=productlist.get(i).getProduct_introduction() %></p>
	 
	 
	 <a href="javascript:void(0)" id="<%=productlist.get(i).getProduct_id()%> " onclick="addproductToBuycar(id)">加入购物车</a>
	 </div>
	 <%} %>
	 </div>
	 <div class='brand'>
	 	<div class='label'>品牌选择</div>
	 	<div class='brandlabel'>
	 	<%
	 		ArrayList<Brand> brandList=(ArrayList<Brand>)request.getAttribute("brandList");
	 		int brandListSize=(int)request.getAttribute("brandListSize");
	 		int categoryId=(int)request.getAttribute("categoryId");
	 		String productName=(String)request.getAttribute("productName");
	 		for(int i=0;i<brandListSize;i++){
	 		
	 	 %>
	 		<a href="findProductByBrand.do?brandId=<%=brandList.get(i).getBrand_id()%>&categoryId=<%=categoryId %>&productName=<%=productName %>" ><%=brandList.get(i).getBrand_name() %>(<%=brandList.get(i).getBrand_englishName() %>)</a>
	 	<%} %>
	 	</div>
	 </div>
  </body>
</html>
