<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="javabean.ProductItem"%>
<%@page import="javabean.Product"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'buycar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/buycar.js"></script>
	<script>
	
		function chooseproduct(classes){
			var totalmoney=parseInt($("#totalprice").html());
			var productprice=parseInt($("#"+classes).html());
			if($("."+classes).prop("checked")){
				totalmoney=totalmoney+productprice;
			}else{
				totalmoney=totalmoney-productprice;
			}
			$("#totalprice").html(totalmoney);
		}
		function deleteproduct(id){
			$.ajax({
				type:'post',
				url:'delete.do',
				data:{
					"productid":id
				},
				success:function(data){
					$("tr[name="+id+"]").hide();
				},
				error:function(){
					alert("wrong");
				}
			
			})
		}
		function chooseall(){
			if($("input[type='checkbox']").prop("checked")){
				$("input[type='checkbox']").prop("checked",false);
			}else{
				$("input[type='checkbox']").prop("checked",true);
			}
		}
		function gopay(){
			var productIdStr="";
			if($("input[type='checkbox']").prop("checked")){
					$(".checkbox").each(function(){
						if($(this).prop("checked")){
							productIdStr+=$(this).attr("value")+",";
						}
					});

					$.ajax({
						type:'post',
						url:'orderSettlement.do',
						success:function(data){
							window.location.href="orderSettlement.do";
						},
						error:function(){
							alert("Wrong!!!");
						}
						
					})					
			}else{
				alert("请选择你要结算的商品");
				
			}
		}
	</script>
	<style>
		*{
			margin:0;
			padding:0;
		}
		.tablebox{
			width:100%;
			height:450px;
			margin-top:150px;
			overflow:auto;
		}
		table{
			width:1180px;
			text-align:center;
			margin-left:140px;
			border-collapse:separate;
			border-spacing:0px 10px;
		}
		table tr{
			background:lightyellow;
		}
		.totalmoney{
			position:fixed;
			bottom:0px;
			width:100%;
			height:60px;
			background-color:#666;
		}
		.totalprice{
			margin-right:200px;
			float:right;
			margin-top:10px;
		}
		#choose{
			margin-left:160px;
		}
		#gopay{
			display:block;
			width:120px;
			height:40px;
			background-color:#e22;
			color:white;
			text-align:center;
			line-height:40px;
			text-decoration:none;
			font-size:20px;
			position:absolute;
			bottom:8px;
			right:20px;
		}
		.header{
			position:absolute;
			top:10px;
			left:50px;
			float:left;
			
		}
		.header span{
			position:absolute;
			display:block;
			width:200px;
			height:40px;
			top:65px;
			left:160px;
			font-size:30px;
			color:black;
		}
		a{
			text-decoration:none;
		}
		.searchbox{
			position:absolute;
			top:20px;
			left:950px;
			width:300px;
			height:30px;
		}
		#searchbtn{
			position:absolute;
			top:-2px;
			left:210px;
			display:block;
			width:50px;
			height:29px;
			font-size:16px;
			color:black;
			background-color:#e22;
			text-align:center;
			text-decoration:none;
			line-height:29px;
		}
		#searchtext{
			position:absolute;
			top:-2px;
			left:0xp;
			width:210px;
			height:30px;
			font-size:16px;
			
		}
	</style>
  </head>
	<body>
	<div class='header'>
		<img src='image/sprite.head.png' width="120px" height="120px">
		<span>购物车</span>
		<div class='searchbox'>
		<input type='text' name='search' id='searchtext'><a href='jsvascript:void(0)' id='searchbtn'>搜索</a>
		</div>
	</div>
	<div class='tablebox'>
	<table>
	<thead>
		<th>选择</th>
		<th>编号</th>
		<th>名称</th>
		<th>缩略图</th>
		<th>价格</th>
		<th>简介</th>
		<th>数量</th>
		<th>总价</th>
		<th>操作</th>
	</thead>
	<tbody>
	
		<%
			List<ProductItem> productitemList=(List<ProductItem>)request.getAttribute("productitemList");
			int poductitemListSize=(int)request.getAttribute("productitemListSize");
			ProductItem productitem=null;
			for(int i=0;i<poductitemListSize;i++){
			    productitem=(ProductItem)productitemList.get(i);
		 %>
		<tr name="<%=productitem.getProduct().getProduct_id() %>"  value="<%=productitem.getProduct().getProduct_id() %>">
		<td><input type="checkbox" name="productid" class="checkbox"  value="<%=productitem.getProduct().getProduct_id() %>" onclick="chooseproduct(<%=productitem.getProduct().getProduct_id() %>)" /></td>
		<td class='productId'><%=productitem.getProduct().getProduct_id() %></td>
		<td><%=productitem.getProduct().getProduct_name()%></td>
		<td><img src='<%=productitem.getProduct().getProduct_thumbnail()%>' width="80" height="80"></td>
		<td><%=productitem.getProduct().getProduct_price()%></td>
		<td><%=productitem.getProduct().getProduct_introduction()%></td>
		<td>X<%=productitem.getCount()%></td>
		<td id="<%=productitem.getProduct().getProduct_id() %>" class="price"><%=productitem.totalMoney()%></td>
		<td><a href="javascript:void(0)" onclick="deleteproduct(<%=productitem.getProduct().getProduct_id() %>)">删除</a></td>
		</tr>
		<%}%>
		</tbody>
		</table>
		<div class='totalmoney'>
			<div class='totalprice'>
				<label>总价：</label><span id="totalprice"></span>
			</div>
			<input type="checkbox" id="choose" onclick="chooseall()"/>全选
			<a href="javascript:void(0)" id='gopay' onclick='gopay()'>去结算</a>
		</div>
		</div>
	</body>
</html>
<!-- /* Cookie[] cookies=request.getCookies();
			out.print(cookies.length);
		   		for(Cookie cookie:cookies){
			   		String json="";
			   		String countprice="";
		   			if(cookie.getName().equals("buycar")||cookie.getName().equals("countprice")){
		   			json=URLDecoder.decode(cookie.getValue());
		   			countprice=cookie.getValue();
		   			JSONObject buycarjson=JSONObject.fromObject(json);
		   			Iterator iterator=buycarjson.keySet().iterator();
			   		String key="";
			   		Map<String,Object> buycarmap=null;
			   		Map<String,Object> productmap=null;
			   		ProductItem poductitem=null;
			   		Product product=null;
			   		int totalmoney=0;
			   		while(iterator.hasNext()){
			   			key=(String)iterator.next();
			   			buycarmap=(Map<String,Object>)buycarjson.get(key);
			   			productmap=(Map<String,Object>)buycarmap.get("product");
			   			totalmoney=(int)productmap.get("product_price")*(int)buycarmap.get("count");
			   			
			   		 */ -->