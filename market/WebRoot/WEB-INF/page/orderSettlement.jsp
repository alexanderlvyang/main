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
	<link rel='stylesheet' type='text/css' href='css/table.css'>
	<style>
		*{
			margin:0;
			padding:0;
		}	
		.orderBox{
			width:1000px;
			border: 1px solid #666;
			margin-left:180px;
			margin-top:50px;
		}
		.header{
			margin-left:100px;
			margin-top:20px;
		}
		#tag{
			font-size:24px;
			margin-left:10px;
		}
		a{
			text-decoration:none;
		}
		#newAddress{
			float:right;
			color:blue;
		}
		.address{
			margin-top:5px;
		}
		#hdfk{
			margin-left:10px;
			float:left;
			display:block;
			width:100px;
			height:25px;
			text-align:center;
			line-height:25px;
			margin-top:15px;
		}
		#zxzf{
			margin-left:10px;
			display:block;
			width:100px;
			height:25px;
			float:left;
			border:1px solid red;
			text-align:center;
			line-height:25px;
			margin-top:15px;
		}
		#returnbuycar{
			margin-left:824px;
			color:blue;
		}
		#list{
			float:left;
		}
		#method{
			float:left;
		}
		.label{
			margin-left:180px;
		}
		#sendorder{
			float:right;
			margin-top:80px;
			margin-right:180px;
			margin-bottom:40px;
			display:block;
			width:150px;
			height:30px;
			background-color:#e22;
			color:white;	
			text-align:center;
			line-height:30px;
		
		}
		.yhqd{
			width:100%;
		}
		div{
			margin-top:30px;
		}
		
		.methodBox{
			width:450px;
			height:200px;
			margin-top:30px;
			float:left;
			background-color:#fff;
		}
		.productBox{
			width:550px;
			height:200px;
			margin-top:30px;
			margin-left:450px;
			background-color:#f3fbfe;
		}
		.goodslist{
			width:100%;
		}
		.addAddressBox{
			width:600px;
			height:500px;
			background-color:white;
			border:3px solid #c6c5bb;
			position:absolute;
			top:100px;
			left:350px;
			display:none;
		}
		#title{
			width:100%;
			background-color:#999;
			margin-top:0px;
		}
		.addAddressBox span{
			font-size:10px;
			color:#c0c0c0;
		}
		.addAddressBox input{
			width:200px;
			height:25px;
			margin-top:10px;
		}
		.addAddressBox label{
			margin-top:20px;
		}
		.sign{
			color:red;
		}
		.addAddressBox table{
			margin-top:30px;
		}
		#submitButton{
			width:150px;
			height:30px;
			color:white;
			background-color:#e22;
			border:none;
			margin-left:150px;
			margin-top:20px;
		}
		#turnOff{
			float:right;
			margin-right:20px;
			color:red;
			font-size:18px;
			cursor:pointer;
		}
		a{
			text-decoration:none;
			color:black;
		}
		#expressDelivery{
			display:block;
			width:150px;
			height:25px;
			border:1px solid red;
			text-align:center;
			line-height:25px;
			margin-left:20px;
			margin-top:10px;
		}
		.receiver{
			display:block;
			width:100px;
			height:20px;
			margin-right:20px;
			text-align:center;
			line-height:20px;
			border:1px solid red;
		}
		#genericTable td{
			width:112px;
		}
		.totalprice{
			float:right;
			margin-right:30px;
			margin-top:85px;
		}
	</style>
	<script src='js/jquery-3.3.1.min.js'></script>
	<script>
		function showAddressBox(){
			$(".addAddressBox").show();
		}
		function hideAddressBox(){
			$(".addAddressBox").hide();
		}
		function choosehdfk(){
			$("#zxzf").css("border","none");
			$("#hdfk").css("border","1px solid red");
		}
		function choosezxzf(){
			$("#hdfk").css("border","none");
			$("#zxzf").css("border","1px solid red");
		}
		var i=0;
		function addAddress(){
			var address=$("#addresstext").val();
			var receiver=$("#receivertext").val();
			var phoneNumber=$("#phoneNumbertext").val();
			var email=$("#email").val();
			var otherAddressName=$("#otherAddressName").val();
			$.ajax({
				type:'post',
				url:'addReceiver.do',
				data:{
					"address":address,
					"receiver":receiver,
					"phoneNumber":phoneNumber,
					"email":email,
					"otherAddressName":otherAddressName
				},
				success:function(data){
					alert(data);
					i++;
					var addressId=i+"a";
					var receiverId =addressId+"id";
					var tagContent="<a href='javascript:void(0)' class='receiver' id='"+receiverId+"' addressid='"+addressId+"' onclick='addresschoose(id)'>"+receiver+"</a>"+
    		 		"<span id="+addressId+">"+receiver+","+address+"</span><br>";
    		 		$(".address").append(tagContent);
				},
				error:function(){
					alert("Wrong!!!");
				}
			});
					
		}
		function addresschoose(id){
				$("#"+id).css("border","1px solid green");
				$(this).siblings(".receiver").css("border","1px solid red");
		}
		$(function(){
			$(".receiver").bind("click",function(){
				$(this).css("border","1px solid green");
				$(this).siblings(".receiver").css("border","1px solid red");
			});
		})
		function submitfun(){
			var receiverAddress;
			$(".receiver").each(function(){
				if($(this).css("border")=="1px solid rgb(0, 128, 0)")
				{
					var id=$(this).attr("addressid");
					receiverInfo=$("#"+id).html();
					window.location.href="aliPayPage.do?receiverInfo="+receiverInfo;
				}else{
					alert('请选择收货地址');
				}
			});
			
		}
	</script>
  </head>
  
  <body>
    <div class='header'>
    	<img src='image/logo-201305-b.png'>
    	<span id='tag'>结算页</span>
    </div>
    <p class='label'>填写并核对订单信息</p>
    <div class='orderBox'>
    	<div class='info'>
    		<label>收货人信息</label>
    		<a href='javascript:void(0)' id='newAddress' onclick='showAddressBox()'>新增收货地址</a>
    	</div>
    	<div class='address'>
    	<%@page import="javabean.ReceiverAddress" %>
    		<%
    			ArrayList<ReceiverAddress> receiverAddressList=(ArrayList<ReceiverAddress>)request.getAttribute("receiverAddressList");
    			int receiverAddressListSize=(int)request.getAttribute("receiverAddressListSize");
    			for(int i=0;i<receiverAddressListSize;i++){
    		 %>
    		 <a href='javascript:void(0)' class='receiver' addressid='<%=i%>'><%=receiverAddressList.get(i).getReceiverName() %></a>
    		 <span id='<%=i%>'><%=receiverAddressList.get(i).getReceiverName() %>,<%=receiverAddressList.get(i).getReceiverAddress() %>,<%=receiverAddressList.get(i).getReceiverPhone()%></span><br>
    		 <%
    		 	}
    		  %>
    	</div>
    	<div>
    		<label>支付方式</label><br>
    		<a href='javascript:void(0)' id='hdfk' onclick='choosehdfk()'>货到付款</a><a href='javascript:void(0)' id='zxzf' onclick='choosezxzf()'>在线支付</a><br>
    	</div>
    	<div class='goodslist'>
    		<div>
	    		<label id='list'>运货清单</label>
	    		<a href='car.do' id='returnbuycar' >返回修改购物车</a>
    		</div>
    		<div class='methodBox'>
    			<label id='method'>配送方式</label><br>
    			<a href='javascript:void(0)' id='expressDelivery'>京东快递</a>
    		</div>
    		<div class='productBox'>
    		<%@page import="javabean.ProductItem" %>
    		<table id='genericTable'>
    			<%
    				ArrayList<ProductItem> productItemList=(ArrayList<ProductItem>)request.getAttribute("ProductItemList");
    			 	int productItemListSize=(int)request.getAttribute("ProductItemListSize");
    			 	double totalPrice=0.00;
    			 	String productIdStr="";
    			 	for(int i=0;i<productItemListSize;i++){
    			 	totalPrice+=productItemList.get(i).getProduct().getProduct_price()*productItemList.get(i).getCount();
    			 	productIdStr+=productItemList.get(i).getProduct().getProduct_id()+",";
    			 %>
				<tr>
					<td><img src="<%=productItemList.get(i).getProduct().getProduct_thumbnail()%>" width='50px' height='50px'></td>
					<td><%=productItemList.get(i).getProduct().getProduct_name() %><%=productItemList.get(i).getProduct().getProduct_introduction() %></td>
					<td><%=productItemList.get(i).getProduct().getProduct_price() %></td>
					<td>X<%=productItemList.get(i).getCount() %></td>
				</tr>    			 	
 				<%} %>
 			</table>	
    		</div>
    	</div>
    	<div>
    		<label>发票信息</label>
    		<div>
    			<a>电子普通发票</a>
    			<a>个人</a>
    			<a>商品明细</a>
    		</div>
    	</div>
    </div>
    <div class='addAddressBox'>
    	<p id='title'>new receiver information<span id='turnOff' onclick='hideAddressBox()'>X</span></p>
    	<table>
    	<tr>
    		<td><label class='sign'>*</label><label>Address:</label></td>
    		<td><input type='text' name='address' id='addresstext'></td>
    	</tr>
    	<tr>
    		<td><label class='sign'>*</label><label>receiver:</label></td>
    		<td><input type='text' name='receiver' id='receivertext'></td>
    	</tr>
    	<tr>
    		<td><label class='sign'>*</label><label>phoneNumber:</label></td>
    		<td><input type='text' name='phoneNumber' id='phoneNumbertext'></td>
    	</tr>
    	<tr>
    		<td><label>email:</label></td>
    		<td><input type='email' name='email' id='email'></td>
    	</tr>
    	<tr>
    		<td><label>otherAddressName:</label></td>
    		<td><input type='text' name='otherAddressName' id='otherAddressName'><span>forExample&nbsp;home,company</span></td>
    	</tr>	
    	</table>
    	<input type='button' value='保存收货人信息' id='submitButton' onclick='addAddress()'>
	</div>
    <a href='javascript:void(0)' id='sendorder' productIdStr='<%=productIdStr%>' onclick='submitfun()'>提交订单</a>
    <div class='totalprice'>
		<label>总价：</label><span id='totalPrice' value='<%=totalPrice%>'><%=totalPrice%></span>
	</div>
  </body>
</html>
		