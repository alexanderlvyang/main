<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var specificationId=$(this).parent().siblings().eq(0).children().val();
			$.ajax({
				type:"post",
				url:"deleteCart",
				data:{
					"specificationId":specificationId
				},
				success:function(data){
					alert(data);
					window.location.reload();
				},
				error:function(){
					alert(" 删除出错");
				}
			});
		});
		$(".checkAllBox").click(function(){
			var totalPrice=0;
			if($(this).prop("checked")){
				$(".checkbox").each(function(){
					totalPrice=totalPrice+parseInt($(this).attr("total"));
				});
				$(".checkbox").prop("checked",true);
				$("#totalPrice").html(totalPrice);
			}else{
				$(".checkbox").prop("checked",false);
				$("#totalPrice").html(0.0);
			}
		});
		$(".checkbox").click(function(){
			var totalPrice=0;
			var single=0;
			if($(this).prop("checked")){
				$(".checkbox").each(function(){
					if($(this).prop("checked")){
						single=single+parseInt($(this).attr("total"));
					}
				});
				totalPrice=totalPrice+single;
				$("#totalPrice").html(totalPrice);
			}else{
				var totalPrice=$("#totalPrice").html();
				$("#totalPrice").html(totalPrice-$(this).attr("total"));
			}
		});
		$("#payButton").click(function(){
			var specificationId="";
			$(".checkbox").each(function(){
				if($(this).prop("checked")){
					specificationId=specificationId+$(this).val()+",";
				}
			});
			var username=$("#username").html();
			if(typeof(username)=="undefined"){
				alert("请登录");
				window.location="loginfront";
			}else if(specificationId==""){
				alert("请选择商品");	
			}else{
				specificationId=window.encodeURIComponent(specificationId);
				$("#payButton").attr("href","confirmPay?specificationId="+specificationId+"&indentification=&count=");
			}
			
		});
	});
</script>
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
	
	#username {
		color: #EE30A7;
	}
	
	#logo {
		margin-top: 10px;
		margin-left: 100px;
	}
	#label{
		font-size:30px;
		position:absolute;
		top:100px;
		left:220px;
	}
	.tableDiv{
		margin-top:80px;
	}
	.table{
		border-collapse:separate;
		border-spacing:20px 0px;
	}
	td{
		width:200px;
		text-align:center;
	}
	#tip{
		padding-left:400px;
		padding-top:100px;
		width:100%;
		font-size:25px;
	}
	.payDiv{
		position:fixed;
		bottom:0px;
		width:100%;
		height:40px;
		background-color:#e22;
	}
	#chooseAll{
		margin-left:80px;
	}
	#priceLabel{
		margin-left:800px;
	}
	#payButton{
		background-color:white;
		color:red;
		display:block;
		position:absolute;
		bottom:10px;
		right:100px;
		width:150px;
		height:20px;
		text-align:center;
		line-height:20px;
		text-decoration:none;
	}
</style>
</head>
<body>
	<div class="header" productId="${productInfoList[0].product_id}">
		<ul class="navigation">
			<c:if test="${empty sessionScope.user }">
				<li><a href="loginfront">请登录</a></li>
				<li><a href="registerfront" id="register">请注册</a></li>
			</c:if>
			<c:if test="${not empty sessionScope.user}">
				<li><a href="personInfo" id="username" >${sessionScope.user.username }</a></li>
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
	<span id="label">购物车</span>
	<div class="tableDiv">
		<table class="table">
			<c:if test="${not empty productInfoList or not empty cookieProductInfoList}">
				<thead>
					<th></th>
					<th></th>
					<th>商品</th>
					<th></th>
					<th>单价</th>
					<th>数量</th>
					<th>小计</th>
					<th>操作</th>
				</thead>
			</c:if>
			<c:if test="${not empty productInfoList}">
				<c:forEach items="${cookieProductInfoList }" var="cookieProductInfo">
						<tr>
							<td><input type="checkbox" class="checkbox" name="cart" value="${cookieProductInfo.specification_id }" total="${cookieProductInfo.total_price }"></td>
							<td><img src="files/${cookieProductInfo.product_thumbnail }" width="100px" height="100px"></td>
							<td>${cookieProductInfo.product_name }</td>
							<td>
								<c:if test="${not empty cookieProductInfo.product_color }">
									<p>颜色：${cookieProductInfo.product_color }</p>
								</c:if>
								规格：${cookieProductInfo.product_specification }
							</td>
							<td>${cookieProductInfo.price }</td>
							<td>${cookieProductInfo.count }</td>
							<td>${cookieProductInfo.total_price }</td>
							<td><a href="javascript:void(0)" class="delete">删除</a></td>
						</tr>
					</c:forEach>
					<c:forEach items="${productInfoList }" var="productInfo">
							<tr>
								<td><input type="checkbox" class="checkbox" name="cart" value="${productInfo.specification_id }" total="${productInfo.total_price }"></td>
								<td><img src="files/${productInfo.product_thumbnail }" width="100px" height="100px"></td>
								<td>${productInfo.product_name }</td>
								<td>
									<c:if test="${not empty productInfo.product_color }">
										<p>颜色：${productInfo.product_color }</p>
									</c:if>
									规格：${productInfo.product_specification }
								</td>
								<td>${productInfo.price }</td>
								<td>${productInfo.count }</td>
								<td>${productInfo.total_price }</td>
								<td><a href="javascript:void(0)" class="delete">删除</a></td>
							</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty productInfoList}">
				<c:forEach items="${cookieProductInfoList }" var="cookieProductInfo">
						<tr>
							<td><input type="checkbox" class="checkbox" name="cart" value="${cookieProductInfo.specification_id }" total="${cookieProductInfo.total_price }"></td>
							<td><img src="files/${cookieProductInfo.product_thumbnail }" width="100px" height="100px"></td>
							<td>${cookieProductInfo.product_name }</td>
							<td>
								<c:if test="${not empty cookieProductInfo.product_color }">
									<p>颜色：${cookieProductInfo.product_color }</p>
								</c:if>
								规格：${cookieProductInfo.product_specification }
							</td>
							<td>${cookieProductInfo.price }</td>
							<td>${cookieProductInfo.count }</td>
							<td>${cookieProductInfo.total_price }</td>
							<td><a href="javascript:void(0)" class="delete">删除</a></td>
						</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty productInfoList and empty cookieProductInfoList}">
				<tr>
					<td id="tip">
						购物车内暂时没有商品，登录后将显示您之前加入的商品
					</td>
				</tr>	
			</c:if>
		</table>
	</div>
	<c:if test="${not empty productInfoList or not empty cookieProductInfoList}">
		<div class="payDiv">
			<label id="chooseAll"><input type="checkbox" name="cart" class="checkAllBox" value="全选">全选</label>
			<label id="priceLabel"><label>价格：</label><span id="totalPrice">0.0</span></label>
			<!-- <input type="button" value="确认支付" id="payButton"> -->
			<a href="#" id="payButton">确认支付</a>
		</div>
	</c:if>
	
</body>
</html>