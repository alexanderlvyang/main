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
		$(".status").change(function(){
				var order_status=$(this).val();
				var order_id=$(this).parent().siblings().eq(0).html();
				$.ajax({
					type:"post",
					url:"updateOrder.do",
					data:{
						"order_status":order_status,
						"order_id":order_id
					},
					success:function(data){
						alert(data);
						window.location.reload();
					},
					error:function(){
						alert("请求出错");
					}
				});
		});
	});
</script>
<style type="text/css">
	*{
		margin:0;
		padding:0;
	}
	body{
		background:url(../images/menu-background.jpg) no-repeat;
		background-size:100% 100%;
		background-attachment:fixed;
	}
	a{
		text-decoration:none;
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
		width:1500px;
	}
	table{
		text-align:center;
		width:1500px;
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
</style>
</head>
<body>
	<div class="searchDiv">
		<form action="orderManage.do" method="post">
			<input type="text" name="condition" id="condition" value="${sessionScope.condition}" placeholder="订单号/状态/下单账号/下单时间"/>
			<input type="submit" value="确定" id="searchButton">
		</form>
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
				<th>收件人名称</th>
				<th>收件人联系电话</th>
				<th>收件人地址</th>
				<th>订单总价</th>
				<th>下单账号</th>
				<th>订单状态</th>
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
						<td>${ordersInfo.addressee_name }</td>
						<td>${ordersInfo.addressee_phone }</td>
						<td>${ordersInfo.addressee_address }</td>
						<td>${ordersInfo.order_totalPrice }</td>
						<td>${ordersInfo.phone }</td>
						<td>
							<select class="status">
								<option value="${ordersInfo.order_status }">${ordersInfo.order_status }</option>
								<c:if test="${ordersInfo.order_status=='已下单'}">
									<option value="已发货">已发货</option>
								</c:if>
							</select>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pageDiv">
		<c:forEach var="page" begin="1" end="${totalPage}">
				<a href="orderManage.do?startPage=${page}&condition=${sessionScope.condition}&phone=">${page}</a>
		</c:forEach>
	</div>
</body>
</html>