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
		$("#confirmOrder").click(function(){
			var payMethod=""
			var address=""
			var specificationIdArray=""
			$(".payMethod").each(function(){
				if($(this).prop("checked")){
					payMethod=$(this).val();
				}
			});
			$(".chooseAddress").each(function(){
				if($(this).prop("checked")){
					address=$(this).val();
				}
			});
			if(address==""){
				alert("请选择收件人");
			}else{
				if(payMethod==""){
					alert("请选择付款方式");
				}else{
					$(".skuList").each(function(){
						specificationIdArray+=$(this).attr("specification")+",";
					});
					$(this).attr("href","alipayPay?payMethod="+payMethod+"&address="+address+"&specificationIdArray="+specificationIdArray);
				}
			}
		});
		$("#add").click(function(){
			$(".addDiv").show();
		});
		$("#cancel").click(function(){
			$(".addDiv").hide();
		});
		$("#confirm").click(function(){
			var addressName=$("#addName").val();
			var addressPhone=$("#addPhone").val();
			var addressAddress=$("#addAddress").val();
			if(addressPhone.length<11){
				alert("手机号格式错误");
			}else{
					$.ajax({
						type:"post",
						url:"addAddressee",
						data:{
							"addressee_name":addressName,
							"addressee_phone":addressPhone,
							"addressee_address":addressAddress
						},
						success:function(data){
							if(data=="添加成功"){
								alert(data);
								window.location.reload();
							}else{
								alert(data);
							}
						}
					});
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
	.addressDiv{
		padding-left:300px;
		margin-top:80px;
		height:100px;
		overflow: auto;
	}
	.address{
		margin-left:50px;
	}
	.tableDiv{
		padding-left:300px;
	}
	table{
		border-collapse:separate;
		border-spacing:20px 0px;
	}
	.introduction{
		width:500px;
	}
	.payMethod{
		margin-left:80px;
	}
	.methodDiv{
		margin-left:300px;
	}
	.payDiv{
		margin-left:950px;
	}
	#confirmOrder{
		margin-left:200px;
		display:block;
		width:150px;
		height:20px;
		line-height:20px;
		background-color:#e22;
		color:white;
		text-align:center;
		text-decoration:none;
	}
	.addDiv{
		width:500px;
		height:200px;
		text-align:center;
		border:1px solid red;
		position:absolute;
		top:280px;
		left:400px;
		background-color:#999;
		display:none;
	}
	.addDiv table{
		margin-left:100px;
		margin-top:10px;
		border-collapse:separate;
		border-spacing:5px 20px;
	}
	#confirm{
		margin-left:10px;
		width:100px;
		border:none;
		background-color:#e22;
		color:white;
	}
	#cancel{
		margin-left:10px;
		width:100px;
		border:none;
		background-color:#e22;
		color:white;
	}
	#add{
		margin-left:10px;
		width:100px;
		border:none;
		background-color:#e22;
		color:white;
		margin-left:700px;
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
		<div class="addressDiv">
		<label>收件人信息：</label><button id="add">添加收件人</button>
			<c:forEach items="${addressList }" var="address">
				<p class="address"><label><input type="radio" name="address" class="chooseAddress" value="${address.addressee_id}">${address.addressee_name } ${address.addressee_phone } ${address.addressee_address }</label></p>
			</c:forEach>
		</div>
		<div class="tableDiv">
		<label>清单：</label>
			<table>
				<c:forEach items="${productInfoLsit }" var="productInfo">
					<tr class="skuList" specification="${productInfo.specification_id }">
						<td><img src="files/${productInfo.product_thumbnail }" width="100px" height="100px"></td>
						<td class="introduction">
							${productInfo.product_name }<br>
							${productInfo.product_introduction }
						</td>
						<td>
							<c:if test="${not empty productInfo.product_color}">
								颜色：${productInfo.product_color }<br>
							</c:if>
							规格：${productInfo.product_specification }
						</td>	
						<td>${productInfo.price }</td>
						<td>${productInfo.count }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="methodDiv">
			<label>付款方式：</label><br>
			<label><input type="radio" name="payMethod" class="payMethod" value="支付宝支付">支付宝支付</label><br>
			<label><input type="radio" name="payMethod" class="payMethod" value="微信支付">微信支付</label>
		</div>
		<div class="payDiv">
			<label>应付金额：</label><span>${payTotalPrice }</span>
			<a href="#" id="confirmOrder">提交订单</a>
		</div>
		<div class="addDiv">
		<table>
			<tr>
				<td>收件人姓名：</td>
				<td><input type="text" id="addName"></td>
			</tr>
			<tr>
				<td>收件人手机：</td>
				<td><input type="text" id="addPhone" maxlength="11"></td>
			</tr>
			<tr>
				<td>收件人地址：</td>
				<td><input type="text" id="addAddress"></td>
			</tr>
		</table>
		<button id="confirm">确定</button><button id="cancel">取消</button>
	</div>
</body>
</html>