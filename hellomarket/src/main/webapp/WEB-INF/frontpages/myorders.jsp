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
	
	#username {
		color: #EE30A7;
	}
	
	#logo {
		margin-top: 10px;
		margin-left: 100px;
	}
		.tableDiv{
		background:rgba(255,255,255,0.2);
	}
	.image{
		width:60px;
		height:80px;
	}
	.tableDiv{
		margin-top:50px;
		width:1350px;
	}
	table{
		text-align:center;
		width:1350px;
	}
	.searchDiv{
		text-align:center;
		height:80px;
		line-height:80px;
	}
	#condition{
		width:250px;
		height:20px;
	}
	#searchButton{
		background:#e22;
		color:white;
		width:100px;
		height:22px;
		border:none;
	}
	.pageDiv{
		background:#e22;
		text-align:center;
		position:fixed;
		bottom:1px;
		width:100%;
	}
	.pageDiv a{
		color:white;
	}
	td{
		border-bottom:1px solid black;
	}
	a{
		text-decoration:none;
	}
	#received{
		display:block;
		position:absolute;
		top:140px;
		left:400px;
		width:200px;
		height:25px;
		text-align:center;
		line-height:20px;
		background-color:#666;
		color:white;
	}
	#commented{
		display:block;
		position:absolute;
		top:140px;
		left:600px;
		border-right:1px solid black;
		width:200px;
		height:25px;
		text-align:center;
		line-height:20px;
		background-color:#666;
		color:white;
	}
	#dateTime{
		position:absolute;
		top:140px;
		left:100px;
		width:100px;
		height:25px;
		
	}
	#allOrders{
		display:block;
		position:absolute;
		top:140px;
		left:200px;
		border-right:1px solid black;
		width:200px;
		height:25px;
		text-align:center;
		line-height:20px;
		background-color:#666;
		color:white;
	}
</style>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#dateTime").change(function(){
			var createTime=$('#dateTime option:selected').val();
			var status=""
			$(".status").each(function(){
				if($(this).css("background-color")=="rgb(238, 34, 34)"){
					status=$(this).html();
				}
			});
			if(status=='待收货'){
				status="received"
			}else if(status=='待评价'){
				status="commented";
			}else if(status=='全部订单'){
				status="";
			}
			window.location="myOrder?createTime="+createTime+"&status="+status;
		});
		$(".status").click(function(){
			var createTime=$('#dateTime option:selected').val();
			var status=$(this).html();
			if(status=='待收货'){
				status="received"
			}else if(status=='待评价'){
				status="commented";
			}else if(status=='全部订单'){
				status="";
			}
			$(this).attr("href","myOrder?createTime="+createTime+"&status="+status);
		});
		$(".page").click(function(){
			var createTime=$('#dateTime option:selected').val();
			var status=""
			var page=$(this).html();
			$(".status").each(function(){
				if($(this).css("background-color")=="rgb(238, 34, 34)"){
					status=$(this).html();
				}
			});
			if(status=='待收货'){
				status="received"
			}else if(status=='待评价'){
				status="commented";
			}else if(status=='全部订单'){
				status="";
			}
			$(this).attr("href","myOrder?createTime="+createTime+"&status="+status+"&startPage="+page);
		});
	});
</script>
	
</head>
<body>
	<div class="header">
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
	<div>
		<select id="dateTime">
			<c:if test="${sessionScope.createTime=='过去一周'}">
				<option value="${sessionScope.createTime }">${sessionScope.createTime }</option>
				<option value="过去一月">过去一月</option>
				<option value="过去一年">过去一年</option>
			</c:if>
			<c:if test="${sessionScope.createTime=='过去一月'}">
				<option value="过去一周">过去一周</option>
				<option value="${sessionScope.createTime }">${sessionScope.createTime }</option>
				<option value="过去一年">过去一年</option>
			</c:if>
			<c:if test="${sessionScope.createTime=='过去一年'}">
				<option value="过去一周">过去一周</option>
				<option value="过去一月">过去一月</option>
				<option value="${sessionScope.createTime }">${sessionScope.createTime }</option>
			</c:if>
			<c:if test="${empty sessionScope.createTime||sessionScope.createTime==''}">
				<option value="" selected="selected"></option>
				<option value="过去一周">过去一周</option>
				<option value="过去一月">过去一月</option>
				<option value="过去一年">过去一年</option>
			</c:if>
			
		</select>
		<c:if test="${sessionScope.status=='received' }">
			<a href="#" id="allOrders" class="status">全部订单</a>
			<a href="#" id="received" style="background-color:#e22" class="status">待收货</a>
			<a href="#" id="commented" class="status">待评价</a>
		</c:if>
		<c:if test="${sessionScope.status=='commented' }">
			<a href="#" class="status">全部订单</a>
			<a href="#"" id="received" class="status">待收货</a>
			<a href="#" id="commented" style="background-color:#e22" class="status">待评价</a>
		</c:if>
		<c:if test="${empty sessionScope.status }">
			<a href="#"" id="allOrders" style="background-color:#e22" class="status">全部订单</a>
			<a href="#" id="received" class="status">待收货</a>
			<a href="#" id="commented" class="status">待评价</a>
		</c:if>
	</div>
	<div class="tableDiv">
		<table border="0" cellpadding="0" cellspacing="0">
			<thead>
				<th>订单号</th>
				<th>商品名</th>
				<th>商品颜色</th>
				<th>商品规格</th>
				<th>商品简图</th>
				<th>商品单价</th>
				<th>收件人信息</th>
				<th>订单总价</th>
			</thead>
			<tbody>
				<c:forEach items="${ordersInfoList }" var="ordersInfo">
					<tr>
						<td>${ordersInfo.order_id }</td>
						<%-- <td>${ordersInfo.product_name }</td>
						<td>${ordersInfo.product_color }</td>
						<td>${ordersInfo.product_specification }</td>
						<td><img src="files/${ordersInfo.product_thumbnail }" class="image"></td>
						<td>${ordersInfo.price }</td> --%>
						<td>
							<c:forEach items="${ordersInfo.productInfoList}" var="productInfo">
								${productInfo.product_name }<br>
							</c:forEach>
						</td>
						<td>
							<c:forEach items="${ordersInfo.productInfoList}" var="productInfo">
								${productInfo.product_color }<br>
							</c:forEach>
						</td>
						<td>
							<c:forEach items="${ordersInfo.productInfoList}" var="productInfo">
								${productInfo.product_specification }<br>
							</c:forEach>
						</td>
						<td>
							<c:forEach items="${ordersInfo.productInfoList}" var="productInfo">
								<img src="files/${productInfo.product_thumbnail }" class="image"><br>
							</c:forEach>
						</td>
						<td>
							<c:forEach items="${ordersInfo.productInfoList}" var="productInfo">
								${productInfo.price }<br>
							</c:forEach>
						</td>
						<td>${ordersInfo.addressee_name } ${ordersInfo.addressee_phone } ${ordersInfo.addressee_address }</td>
						<td>${ordersInfo.order_totalPrice }</td>
						<td>
							<c:if test="${ordersInfo.order_status=='已下单' }">
								<p>装箱中</p>
							</c:if>
							<c:if test="${ordersInfo.order_status=='已发货' }">
								<a href="confirmGetGoods?orderId=${ordersInfo.order_id }">确认收货</a>
							</c:if>
							<c:if test="${ordersInfo.order_status=='已收货' }">
								<a href="goComment?orderId=${ordersInfo.order_id }">去评价</a>
							</c:if>
							<c:if test="${ordersInfo.order_status=='已完成' }">
								<a href="#">${ordersInfo.order_status}</a>
							</c:if>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pageDiv">
		<c:forEach var="page" begin="1" end="${totalPage}">
				<a href="#" class="page">${page}</a>
		</c:forEach>
	</div>
</body>
</html>