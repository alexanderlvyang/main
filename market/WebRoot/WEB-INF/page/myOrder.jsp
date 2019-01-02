<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myOrder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<style>
		*{
			margin:0;
			padding:0;
			}
		ul{
			width:100%;
			height:50px;
			margin-top:30px;
			line-height:50px;
		}
		ul li{
			list-style:none;
			margin-left:20px;
			float:left;
		}
		ul li a{
			text-decoration:none;
			color:black;
		}
		#allOrder{
			border-bottom:1px solid red;
		}
		#selectTime{
			width:100px;
			height:20px;
			position:absolute;
			top:114px;
			left:30px;
		}
		#genericTable{
			margin-top:80px;
			margin-bottom:20px;
		}
		#genericTable td{
			text-align:center;
		}
		#genericTable a{
			text-decoration:none;
		}
		.bottomtd{
			border-top:none;
		}
		.toptd{
			border-bottom:none;
		}
		.orderNumber{
			padding-right:950px;
		}
		.refundResonBox{
			position:absolute;
			top:200px;
			left:475px;
			width:400px;
			height:200px;
			background-color:#666;
			text-align:center;
			display:none;
		}
		.refundResonBox input{
			width:200px;
			height:25px;
			padding-left:5px;
			font-size:20px;
			margin-top:30px;
		}
		#closeLabel{
			position:absolute;
			top:0px;
			left:380px;
			text-decoration:none;
		}
		#submitApplication{
			width:200px;
			height:30px;
			background-color:#e22;
			color:white;
			border:none;
		}
		#orderNumber{
			border:none;
			background-color:#666;
			color:white;
		}
	</style>
	<script src='js/jquery-3.3.1.min.js'></script>
	<script>
		function changeAllOrder(){
			$("#allOrder").css("border-bottom","1px solid rgb(255, 0, 0)");
			$("#pendingPayOrder").css("border-bottom","none");
			$("#pendingReceipt").css("border-bottom","none");
			$("#pendingEvaluated").css("border-bottom","none");
			var orderCategory=$("#allOrder").html();
			$("#selectTime").val("近一周");
			$.ajax({
				type:'post',
				url:'findMyOrder.do',
				data:{
					"orderCategory":orderCategory,
				},
				success:function(message){
					var myOrderObj=eval(message);
					var tabContent="";
					var oneGroupTab=""
					var moreTab="";
					for(i in myOrderObj){
						tabContent="<tr><td colspan='5' class='orderNumber'>"+myOrderObj[i].createTime+"&nbsp;&nbsp; 订单编号："+myOrderObj[i].orderNumber+"</td></tr>"
						var productObj=eval(myOrderObj[i].productList);
						var ChildTab="";
						var childTabContent="";
						var grandChildTabContent="";
						var moreChildTab="";
						for(j in productObj){
							childTabContent="<tr><td>&nbsp;<br><img src='"+productObj[j].product_thumbnail+
											"' width='50' height='50'><br>"+productObj[j].product_name+productObj[j].product_introduction+
											"<br>&nbsp;</td>";
							/* if(j==0){ */
								grandChildTabContent="<td class='toptd'>"+myOrderObj[i].receiverName+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].totalPrice+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].orderState+"</td>";
							if(myOrderObj[i].orderState=='交易成功'){
								grandChildTabContent+="<td class='toptd'><a href='javascript:void(0)' onclick='deleteMyOrder("+myOrderObj[i].orderNumber+")'>删除</a></td>";
							}
							if(myOrderObj[i].orderState=='已退款'){
								grandChildTabContent+="<td class='toptd'><a href='javascript:void(0)' onclick='deleteMyOrder("+myOrderObj[i].orderNumber+")'>删除</a></td>";
							}
							if(myOrderObj[i].orderState=='待付款'){
								grandChildTabContent+="<td class='toptd'><a href='aliPayPage.do?orderNumber="+myOrderObj[i].orderNumber+"'>去付款</a>|<a href='javascript:void(0)' onclick='cancelOrder("+myOrderObj[i].orderNumber+")'>取消订单</a></td>";
							}
							if(myOrderObj[i].orderState=='已发货'){
								grandChildTabContent+="<td class='toptd'><a href='javascript:void(0)' onclick='confirmReceipt("+myOrderObj[i].orderNumber+")'>确认收货</a></td>";
							}
							if(myOrderObj[i].orderState=='待评价'){
								grandChildTabContent+="<td class='toptd'><a href='productDetail.do?product_id="+productObj[j].product_id+"&orderNumber="+myOrderObj[i].orderNumber+"'>去评价</a></td>";
							}
							if(myOrderObj[i].orderState=='未发货'){
								grandChildTabContent+="<td class='toptd'><a href='javascript:void(0)'>申请退款</a></td>";
							}
							
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							/* }else{
								grandChildTabContent="<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>";
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							} */
							moreChildTab+=ChildTab;
						}
						oneGroupTab=tabContent+moreChildTab;
						moreTab+=oneGroupTab;
					}			
					$(".orderList").html(moreTab);
				},
				error:function(){
					alert("Wrong!!!");
				}
			});
		}
		function changePayOrder(){
			$("#allOrder").css("border-bottom","none");
			$("#pendingPayOrder").css("border-bottom","1px solid rgb(255, 0, 0)");
			$("#pendingReceipt").css("border-bottom","none");
			$("#pendingEvaluated").css("border-bottom","none");
			var orderCategory=$("#pendingPayOrder").html();
			$("#selectTime").val("近一周");
			$.ajax({
				type:'post',
				url:'findMyOrder.do',
				data:{
					"orderCategory":orderCategory,
				},
				success:function(message){
					var myOrderObj=eval(message);
					var tabContent="";
					var oneGroupTab=""
					var moreTab="";
					for(i in myOrderObj){
						tabContent="<tr><td colspan='5' class='orderNumber'>"+myOrderObj[i].createTime+"&nbsp;&nbsp; 订单编号："+myOrderObj[i].orderNumber+"</td></tr>"
						var productObj=eval(myOrderObj[i].productList);
						var ChildTab="";
						var childTabContent="";
						var grandChildTabContent="";
						var moreChildTab="";
						for(j in productObj){
							childTabContent="<tr><td>&nbsp;<br><img src='"+productObj[j].product_thumbnail+
											"' width='50' height='50'><br>"+productObj[j].product_name+productObj[j].product_introduction+
											"<br>&nbsp;</td>";
							if(j==0){
								grandChildTabContent="<td class='toptd'>"+myOrderObj[i].receiverName+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].totalPrice+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].orderState+"</td>"+
													"<td class='toptd'><a href='aliPayPage.do?orderNumber="+myOrderObj[i].orderNumber+"'>去付款</a>|<a href='javascript:void(0)' onclick='cancelOrder("+myOrderObj[i].orderNumber+")'>取消订单</a></td>";
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							}else{
								grandChildTabContent="<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>";
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							}
							moreChildTab+=ChildTab;
						}
						oneGroupTab=tabContent+moreChildTab;
						moreTab+=oneGroupTab;
					}			
					$(".orderList").html(moreTab);
				},
				error:function(){
					alert("Wrong!!!");
				}
			});
		}
		function changeReceipt(){
			$("#allOrder").css("border-bottom","none");
			$("#pendingPayOrder").css("border-bottom","none");
			$("#pendingReceipt").css("border-bottom","1px solid rgb(255, 0, 0)");
			$("#pendingEvaluated").css("border-bottom","none");
			var orderCategory=$("#pendingReceipt").html();
			$("#selectTime").val("近一周");
			$.ajax({
				type:'post',
				url:'findMyOrder.do',
				data:{
					"orderCategory":orderCategory,
				},
				success:function(message){
					var myOrderObj=eval(message);
					var tabContent="";
					var oneGroupTab=""
					var moreTab="";
					for(i in myOrderObj){
						tabContent="<tr><td colspan='5' class='orderNumber'>"+myOrderObj[i].createTime+"&nbsp;&nbsp; 订单编号："+myOrderObj[i].orderNumber+"</td></tr>"
						var productObj=eval(myOrderObj[i].productList);
						var ChildTab="";
						var childTabContent="";
						var grandChildTabContent="";
						var moreChildTab="";
						for(j in productObj){
							childTabContent="<tr><td>&nbsp;<br><img src='"+productObj[j].product_thumbnail+
											"' width='50' height='50'><br>"+productObj[j].product_name+productObj[j].product_introduction+
											"<br>&nbsp;</td>";
							if(j==0){
								grandChildTabContent="<td class='toptd'>"+myOrderObj[i].receiverName+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].totalPrice+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].orderState+"</td>"+
													"<td class='toptd'><a href='javascript:void(0)' onclick='confirmReceipt("+myOrderObj[i].orderNumber+")'>确认收货</a></td>";
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							}else{
								grandChildTabContent="<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>";
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							}
							moreChildTab+=ChildTab;
						}
						oneGroupTab=tabContent+moreChildTab;
						moreTab+=oneGroupTab;
					}			
					$(".orderList").html(moreTab);
				},
				error:function(){
					alert("Wrong!!!");
				}
			});
		}
		function changeEvaluted(){
			$("#allOrder").css("border-bottom","none");
			$("#pendingPayOrder").css("border-bottom","none");
			$("#pendingReceipt").css("border-bottom","none");
			$("#pendingEvaluated").css("border-bottom","1px solid rgb(255, 0, 0)");
			var orderCategory=$("#pendingEvaluated").html();
			$("#selectTime").val("近一周");
			$.ajax({
				type:'post',
				url:'findMyOrder.do',
				data:{
					"orderCategory":orderCategory,
				},
				success:function(message){
					var myOrderObj=eval(message);
					var tabContent="";
					var oneGroupTab=""
					var moreTab="";
					for(i in myOrderObj){
						tabContent="<tr><td colspan='5' class='orderNumber'>"+myOrderObj[i].createTime+"&nbsp;&nbsp; 订单编号："+myOrderObj[i].orderNumber+"</td></tr>"
						var productObj=eval(myOrderObj[i].productList);
						var ChildTab="";
						var childTabContent="";
						var grandChildTabContent="";
						var moreChildTab="";
						for(j in productObj){
							childTabContent="<tr><td>&nbsp;<br><img src='"+productObj[j].product_thumbnail+
											"' width='50' height='50'><br>"+productObj[j].product_name+productObj[j].product_introduction+
											"<br>&nbsp;</td>";
							if(j==0){
								grandChildTabContent="<td class='toptd'>"+myOrderObj[i].receiverName+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].totalPrice+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].orderState+"</td>"+
													"<td class='toptd'><a href='productDetail.do?product_id="+productObj[j].product_id+"&orderNumber="+myOrderObj[i].orderNumber+"'>去评价</a></td>";
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							}else{
								grandChildTabContent="<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'><a href='productDetail.do?product_id="+productObj[j].product_id+"'>去评价</a></td>";
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							}
							moreChildTab+=ChildTab;
						}
						oneGroupTab=tabContent+moreChildTab;
						moreTab+=oneGroupTab;
					}			
					$(".orderList").html(moreTab);
				},
				error:function(){
					alert("Wrong!!!");
				}
			});
			
		}
		function findOrderByTime(){
			var time=$("#selectTime").val();
			var orderCategory;
			$("a").each(function(){
				if($(this).css("border-bottom")=="1px solid rgb(255, 0, 0)"){
					orderCategory=$(this).html();
				}
			});
			$.ajax({
				type:'post',
				url:'findMyOrder.do',
				data:{
					"orderCategory":orderCategory,
					"timeDifference":time
				},
				success:function(message){
					var myOrderObj=eval(message);
					var tabContent="";
					var oneGroupTab=""
					var moreTab="";
					for(i in myOrderObj){
						tabContent="<tr><td colspan='5' class='orderNumber'>"+myOrderObj[i].createTime+"&nbsp;&nbsp; 订单编号："+myOrderObj[i].orderNumber+"</td></tr>"
						var productObj=eval(myOrderObj[i].productList);
						var ChildTab="";
						var childTabContent="";
						var grandChildTabContent="";
						var moreChildTab="";
						/* var operation="<td class='toptd'><a href='javascript:void(0)' onclick='deleteMyOrder("+myOrderObj[i].orderNumber+")'>删除</a></td>"; */
						for(j in productObj){
							if(myOrderObj[i].orderState=='交易成功'){
								operation="<td class='toptd'><a href='javascript:void(0)' onclick='deleteMyOrder("+myOrderObj[i].orderNumber+")'>删除</a></td>";
							}
							if(myOrderObj[i].orderState=='已退款'){
								operation="<td class='toptd'><a href='javascript:void(0)' onclick='deleteMyOrder("+myOrderObj[i].orderNumber+")'>删除</a></td>";
							}
							if(myOrderObj[i].orderState=='待付款'){
								operation="<td class='toptd'><a href='aliPayPage.do?orderNumber="+myOrderObj[i].orderNumber+"'>去付款</a>|<a href='javascript:void(0)' onclick='cancelOrder("+myOrderObj[i].orderNumber+")'>取消订单</a></td>";
							}
							if(myOrderObj[i].orderState=='已发货'){
								operation="<td class='toptd'><a href='javascript:void(0)' onclick='confirmReceipt("+myOrderObj[i].orderNumber+")'>确认收货</a></td>";
							}
							if(myOrderObj[i].orderState=='待评价'){
								operation="<td class='toptd'><a href='productDetail.do?product_id="+productObj[j].product_id+"&orderNumber="+myOrderObj[i].orderNumber+"'>去评价</a></td>";
							}
							if(myOrderObj[i].orderState=='未发货'){
								operation="<td class='toptd'><a href='javascript:void(0)'>申请退款</a></td>";
							}
							childTabContent="<tr><td>&nbsp;<br><img src='"+productObj[j].product_thumbnail+
											"' width='50' height='50'><br>"+productObj[j].product_name+productObj[j].product_introduction+
											"<br>&nbsp;</td>";
							if(j==0){
								grandChildTabContent="<td class='toptd'>"+myOrderObj[i].receiverName+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].totalPrice+"</td>"+
													"<td class='toptd'>"+myOrderObj[i].orderState+"</td>"+
													operation;
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							}else{
								grandChildTabContent="<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>"+
	    											"<td class='bottomtd'></td>";
							ChildTab=childTabContent+grandChildTabContent+"</tr>";
							}
							moreChildTab+=ChildTab;
						}
						oneGroupTab=tabContent+moreChildTab;
						moreTab+=oneGroupTab;
					}			
					$(".orderList").html(moreTab);
				},
				error:function(){
					alert("Wrong!!!");
				}
			});
			
		}
		function confirmReceipt(orderNumber){
		alert(orderNumber);
			$.ajax({
				type:'post',
				url:'confirmReceipt.do',
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
		function deleteMyOrder(orderNumber){
			$.ajax({
				type:'post',
				url:'deleteMyOrder.do',
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
		function applyRefund(orderNumber){
		alert(orderNumber);
			$("#orderNumber").val(orderNumber);
			$(".refundResonBox").show();
		}
		function closeRefundResonBox(){
			$(".refundResonBox").hide();
		}
		function submitApplication(){
			var orderNumber=$("#orderNumber").val();
			var refundReson=$("#refundReson").val();
			$.ajax({
				type:'post',
				url:'submitApplication.do',
				data:{
					"orderNumber":orderNumber,
					"refundReson":refundReson
				},
				success:function(message){
					alert(message);
					$(".refundResonBox").hide();
					window.location.reload();
				},
				error:function(){
					alert("Wrong!!!");
				}
			});
		
		}
		function cancelOrder(orderNumber){
			$.ajax({
				type:'post',
				url:'cancelOrder.do',
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
	</script>
  </head>
  
  <body>
    <ul>
    	<li><a href='javascript:void(0)' onclick='changeAllOrder()' id='allOrder'>全部订单</a></li>
    	<li><a href='javascript:void(0)' onclick='changePayOrder()' id='pendingPayOrder'>待付款</a></li>
    	<li><a href='javascript:void(0)' onclick='changeReceipt()' id='pendingReceipt'>待收货</a></li>
    	<li><a href='javascript:void(0)' onclick='changeEvaluted()' id='pendingEvaluated'>待评价</a></li>
	</ul>
    <select id='selectTime' onchange='findOrderByTime()'>
    	<option value='近一周'>近一周</option>
    	<option value='近一月'>近一月</option>
    	<option value='近一年'>近一年</option>
    </select>
    <table id='genericTable' border='2'>
    	<th>订单详情</th>
    	<th>收货人</th>
    	<th>金额</th>
    	<th>状态</th>
    	<th>操作</th>
	    <tbody class='orderList'>
	    <%@page import="javabean.Product" %>
	    <%@page import="javabean.MyOrder" %>
	    <%
 			ArrayList<MyOrder> myOrderList=(ArrayList<MyOrder>)request.getAttribute("myOrderList");
	    	int myOrderListSize=(int)request.getAttribute("myOrderListSize");
	    	for(int i=0;i<myOrderListSize;i++){
	    		ArrayList<Product> productList=myOrderList.get(i).getProductList();
	    		Iterator<Product> iterator=productList.iterator();
	    		MyOrder myOrder=myOrderList.get(i);
	    	
	    %>
	    <tr>
	    	<td colspan='5' class='orderNumber'><%=myOrder.getCreateTime() %>&nbsp;&nbsp;订单编号：<%=myOrder.getOrderNumber() %></td>
	    </tr>
	    <%		
	    		int count=0;
	    		while(iterator.hasNext()){
	     		Product product=(Product)iterator.next();
	     		count++;
	     %>
	    	<tr>
	    		
	    		<td>&nbsp;<br><img src="<%=product.getProduct_thumbnail()%>" width='50' height='50'><br><%=product.getProduct_name()%><%=product.getProduct_introduction() %><br>&nbsp;</td>
	    		<%-- <%if(count==1){ %> --%>
	    		<td class='toptd'><%=myOrder.getReceiverName() %></td>
	    		<td class='toptd'><%=myOrder.getTotalPrice() %></td>
	    		<td class='toptd'><%=myOrder.getOrderState() %></td>
	    		<%
	    			if(myOrder.getOrderState().equals("已发货")){
	    		 %>
	    		 	<td class='toptd'><a href='javascript:void(0)' onclick='confirmReceipt(<%=myOrder.getOrderNumber() %>)'>确认收货</a></td>
	    		 <%}if(myOrder.getOrderState().equals("交易成功")){ %>
	    			<td class='toptd'><a href='javascript:void(0)'  onclick='deleteMyOrder(<%=myOrder.getOrderNumber() %>)'>删除</a></td>
	    		<%}if(myOrder.getOrderState().equals("已退款")){  %>
	    			<td class='toptd'><a href='javascript:void(0)' onclick='deleteMyOrder(<%=myOrder.getOrderNumber() %>)'>删除</a>
	    		<%}if(myOrder.getOrderState().equals("待付款")){  %>
	    			<td class='toptd'><a href='aliPayPage.do?orderNumber=<%=myOrder.getOrderNumber() %>'>去付款</a>|<a href='javascript:void(0)' onclick='cancelOrder(<%=myOrder.getOrderNumber() %>)'>取消订单</a>
	    		<%}if(myOrder.getOrderState().equals("待评价")){  %>
	    			<td class='toptd'><a href='productDetail.do?product_id=<%=product.getProduct_id() %>&orderNumber=<%=myOrder.getOrderNumber() %>'>去评价</a>
	    		<%}if(myOrder.getOrderState().equals("未发货")){ %>
	    			<td class='toptd'><a href='javascript:void(0)' onclick='applyRefund(<%=myOrder.getOrderNumber() %>)'>申请退款</a></td>
	    		<%}%>
	    		<%-- <td class='bottomtd'></td>
	    		<td class='bottomtd'></td>
	    		<td class='bottomtd'></td>
	    		<td class='bottomtd'></td>
	    		<%} %> --%>
	    	</tr>
	    	<%}} %>
	    </tbody>
    </table>
    <div class='refundResonBox'>
    <a href='javascript:void(0)' onclick='closeRefundResonBox()' id='closeLabel'>X</a>
    	<label>订单编号：</label><input type='text' name='orderNumber' id='orderNumber' readonly="readonly"><br>
    	<label>退款理由：</label><input type='text' name='refundReson' id='refundReson'>
    	<input type='button' value='提交' id='submitApplication' onclick='submitApplication()'>
    </div>
  </body>
</html>
	