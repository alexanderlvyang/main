<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'orderManager.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
* {
	margin: 0;
	padding: 0;
}

.searchBox {
	width: 100%;
	height: 40px;
	margin-top: 50px;
	text-align: center;
}

#searchBtn {
	background-color: #e22;
	color: white;
	border: none;
	width: 100px;
	height: 25px;
}

.condition {
	height: 25px;
}
table a{
	text-decoration:none;
}
#clearButton{
	background-color: #e22;
	color: white;
	border: none;
	width: 100px;
	height: 25px;
}
.pageBox{
	width:100%;
}
.pageBox a{
	float:right;
	text-decoration:none;
}
.updateBox{
	border:1px solid red;
	width:400px;
	height:300px;
	position:absolute;
	top:150px;
	left:395px;
	text-align:center;
	background-color:#666;
	display:none;
}
#showOrderNumber{
	margin-top:40px;
	border:none;
	margin-left:16px;
	color:white;
	background-color:#666;
}
.updateBox input[type="text"]{
	width:200px;
	height:20px;
	margin-top:20px;
}
#updateButton{
	background-color:#e22;
	color:white;
	width:150px;
	height:30px;
	margin-top:20px;
	border:none;
}
#closeButton{
	position:absolute;
	top:-5px;
	left:385px;
	font-size:20px;
	color:red;
	text-decoration:none;
}
</style>
<link rel="stylesheet" type="text/css" href="css/table.css">
<script src='js/jquery-3.3.1.min.js'></script>
<script>
	$(function(){
		$.ajax({
			type:'post',
			url:'orderCondition.do',
			success:function(message){
				var conditionObj=JSON.parse(message);
				$("#orderNumber").val(conditionObj.order_number);
				$("#customerPhone").val(conditionObj.customer_phone);
				$("#createTime").val(conditionObj.createTime);
				$("#orderState").val(conditionObj.order_state);
			},
			error:function(){
				alert("Wrong!!!");
			}
		});
		
	})
	function clearInput(){
		$("#customerPhone").val("");
		$("#orderNumber").val("");
		$("#createTime").val("");
		$("#orderState").val("");
	}
	function changeState(orderNumber){
		$.ajax({
			type:'post',
			url:'changeOrderState.do',
			data:{
				"orderNumber":orderNumber
			},
			success:function(message){
				window.location.reload();
			},
			error:function(){
				alert("Wrong!!!");
			}
		});
	}
	function refund(orderNumber){
		$.ajax({
			type:'post',
			url:'alipay.trade.refund.do',
			data:{
				"orderNumber":orderNumber
			},
			success:function(message){
				alert(message);
				window.location.reload();
			},
			error:function(){
				alert("Wrong!!!");
			}
		});
	}
	function showUpdateBox(orderNumber){
		$("#showOrderNumber").val(orderNumber);
		$.ajax({
			type:'post',
			url:'findReceiverInfo.do',
			data:{
				"orderNumber":orderNumber
			},
			success:function(message){
				var receiverObj=JSON.parse(message);
				$("#receiverName").val(receiverObj[0]);
				$("#receiverAddress").val(receiverObj[1]);
				$("#receiverPhone").val(receiverObj[2]);				
			},
			error:function(){
				alert("Wrong!!!");
			}
		});
		$(".updateBox").show();
	}
	function closeUpdateBox(){
		$(".updateBox").hide();
	}
	function updateData(){
		$.ajax({
			type:'post',
			url:'updateOrder.do',
			data:{
				"orderNumber":$("#showOrderNumber").val(),
				"receiverName":$("#receiverName").val(),
				"receiverPhone":$("#receiverPhone").val(),
				"receiverAddress":$("#receiverAddress").val()
			},
			success:function(message){
				alert(message);
				window.location.reload();
			},
			error:function(){
				alert("Wrong!!!");			
			}
		});
	}
</script>
</head>

<body>
<a href="orderManager.do?orderState=已提交退款申请">查看退款申请</a>
	<div class='searchBox'>
	<form action="orderManager.do" method="post">
		<button id='clearButton' onclick='clearInput()'>清空</button>
		<label>下单账号：</label><input type="text" name='customerPhone'
			class='condition' id='customerPhone'> <label>订单号：</label><input type="text"
			name='orderNumber' class='condition' id='orderNumber'> <label>购买日期：</label><input
			type="date" name='createTime' class='condition' id='createTime'><label>发货状态：</label>
			<input type='text' name='orderState' id='orderState' class='condition'>
		<input type='submit' id='searchBtn' value='搜索'>
	</form>
	</div>
	<table id='genericTable' border=2>
		<thead>
			<th>商户订单号</th>
			<th>阿里订单号</th>
			<th>下单账号</th>
			<th>收货人姓名</th>
			<th>收货人联系方式</th>
			<th>收货人地址</th>
			<th>总价</th>
			<th>状态</th>
			<th>创建订单日期</th>
			<th>操作</th>
		</thead>
		<tbody >
			<%@page import="javabean.Order"%>
			<%
    			ArrayList<Order> orderList =(ArrayList<Order>)request.getAttribute("orderList");
    			int orderListSize=(int)request.getAttribute("orderListSize");
    			for(int i = 0;i < orderListSize ; i++){
    		 %>
			<tr>
				<td><%=orderList.get(i).getOrderNumber() %></td>
				<td><%=orderList.get(i).getAliOrderNumber() %></td>
				<td><%=orderList.get(i).getCustomerPhone() %></td>
				<td><%=orderList.get(i).getReceiverName() %></td>
				<td><%=orderList.get(i).getReceiverPhone() %></td>
				<td><%=orderList.get(i).getReceiverAddress() %></td>
				<td><%=orderList.get(i).getTotalPrice() %></td>
				<td><%=orderList.get(i).getOrderState() %></td>
				<td><%=orderList.get(i).getCreateTime() %></td>
				<td><a href='javascript:void(0)' onclick='showUpdateBox(<%=orderList.get(i).getOrderNumber() %>)'>修改订单</a>|<a href='javascript:void(0)' onclick='changeState(<%=orderList.get(i).getOrderNumber() %>)'>发货</a>|<a href='javascript:void(0)' onclick='refund("<%=orderList.get(i).getOrderNumber() %>")'>退款</a></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<div class='pageBox'>
		<%
			int totalPage=(int)request.getAttribute("totalPage");
			for(int i = 1;i <= totalPage;i++){
		 %>
			<a href='orderManager.do?currentPage=<%=i %>'><%=i %></a>
		<%} %>
	</div>
	<div class='updateBox'>
	<a href='javascript:void(0)' id='closeButton' onclick='closeUpdateBox()'>X</a>
		<label>订单编号：</label><input type='text' name='orderNumber' id='showOrderNumber' readonly='readonly'><br>
		<label>收件人姓名：</label><input type="text" name='receiverName' id='receiverName'><br>
		<label>收件人手机：</label><input type='text' name='receiverPhone' id='receiverPhone'><br>
		<label>收件人地址：</label><input type='text' name='receiverAddress' id='receiverAddress'><br>
		<button id='updateButton' onclick='updateData()'>更新</button> 
	</div>
</body>
</html>


