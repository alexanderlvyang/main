<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page import="javabean.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>登陆</title>
<link rel="stylesheet" type="text/css" href="">
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	font-size: 18px;
}

#maxprice {
	width: 80px;
}

#minprice {
	width: 80px;
}

hr {
	margin-top: 20px;
	margin-bottom: 20px;
}

.headifo {
	text-align: center;
	height: 100px;
	line-height: 100px;
}

tbody {
	text-align: center;
}

td, th {
	width: 80px;
}

.productshow {
	background: #CCC;
	width: 100%;
}

a {
	text-decoration: none;
}

#newbuild {
	color: black;
	display: block;
	width: 80px;
	height: 27px;
	background-color: #CCC;
	position: absolute;
	top: 75px;
	right: 3%;
	text-align: center;
	line-height: 27px;
}

.inputtext {
	width: 200px;
	height: 25px;
}

#findbtn {
	width: 80px;
	height: 27px;
	background-color: #CCC;
	position: absolute;
	text-align: center;
	top: 15px;
	right: 3%;
	border: none;
}

#clearbtn {
	position: absolute;
	top: 40px;
	left: 3%;
	width: 80px;
	height: 27px;
	border: none;
	background-color: #CCC;
	cursor: pointer;
}

#pages {
	position: fixed;
	bottom: 0px;
	width: 100%;
	background: red;
}

.alabel {
	margin-left: 20px;
}

#table {
	width: 1200px;
	table-layout: fixed;
}

#introduction {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
<script src="js/jquery-3.3.1.min.js"></script>
<script>
	
	$(function(){
		<%Map<String, String> findconditionmap = (Map<String, String>) request.getSession().getAttribute("find");
			//Map<String,String> findconditionmap=(Map<String,String>)request.getAttribute("find");
			if (findconditionmap != null) {
				Iterator<String> it = findconditionmap.keySet().iterator();
				String key = "";
				while (it.hasNext()) {
					key = it.next();
					if (key.equals("product_name")) {%>
			$("#productname").val("<%=findconditionmap.get(key)%>");
		<%}%>
		<%if (key.equals("lowPrice")) {%>	
			$("#minprice").val("<%=findconditionmap.get(key)%>");
		<%}%>
		<%if (key.equals("highPrice")) {%>	
			$("#maxprice").val("<%=findconditionmap.get(key)%>");
		<%}%>
		<%if (key.equals("product_category")) {%>	
			$("#productcategory").val("<%=findconditionmap.get(key)%>");
		<%}%>
	<%}%>
	<%}%>
	})
		/* $(function(){
			$.ajax({
				type:"post",
				url:"find.do",
				success:function(){
				
				},
				error:function(){
				}
			})
		})  */
		/* function findproduct(){
		var product_minprice=$("#minprice").val();
		var product_maxprice=$("#maxprice").val();
		var product_name=$("#productname").val();
		var product_category=$("#productcategory").val();
			$.ajax({
				type:"post",
				url:"find.do",
				data:{
					"product_minprice":product_minprice,
					"product_maxprice":product_maxprice,
					"product_name":product_name,
					"product_category":product_category
				},
				success:function(data){
					var obj=eval(data);
					var str="";
					for(i in obj){
						str+="<tr id="+obj[i].product_id+">"+
						"<td>"+obj[i].product_id+"</td>"+
						"<td>"+obj[i].product_name+"</td>"+
						"<td><img src='"+obj[i].product_thumbnail+"' width='30' height='30'></td>"+
						"<td>"+obj[i].product_introduction+"</td>"+
						"<td>"+obj[i].product_originalprice+"</td>"+
						"<td>"+obj[i].product_price+"</td>"+
						"<td class='"+obj[i].product_id+"'>"+obj[i].peoduct_state+"</td>"+
						"<td><a href='javascript:void(0)' onclick='changestate("+obj[i].product_id+")'>"+"上/下架"+"</a>"+"<br>"+"<a href='editer.do?id="+obj[i].product_id+"'>"+"编辑"+"</a></td>"+
						"</tr>";
					} 
					$("tbody").html(str);
				},
				error:function(){
				}
			})
		} */
		function changestate(product_id){
			$.ajax({
				type:"post",
				url:"changeState.do",
				data:{
					"product_id":product_id
				},
				success:function(data){
					$("."+product_id).html(data);
				},
				error:function(){
					alert("连接错误");
				}
				
			})
		}
		function clearvalue(){
			$("#productname").val("");
			$("#minprice").val("");
			$("#maxprice").val("");
			$("#productcategory").val("");
		}
	</script>
</head>
<body>

	<div class="headifo">
		<input type="button" value="清空" id='clearbtn' onclick='clearvalue()' />
		<form action='find.do' method='get'>
			名称：<input type="text" name="product_name" class="inputtext"
				id='productname'> 价格区间：<input type="text"
				name="product_minprice" id="minprice">——<input type="text"
				name="product_maxprice" id="maxprice"> 类别：<input type="text"
				name="product_category" class="inputtext" id="productcategory">
			<input type="submit" value="查询" id="findbtn">
		</form>
	</div>
	<!-- <input type="button" value="查询" id="findbtn" onclick="findproduct()"> -->
	<!-- <a href="find.do" width="100px" id="findbtn">查询</a> -->
	<a href="buildProduct.do" width="100px" id="newbuild">新建</a>
	<hr>
	<div class="productshow">
		<table id='table'>
			<thead>
				<th>商品id</th>
				<th>名称</th>
				<th>缩略图</th>
				<th>简介</th>
				<th>原价格</th>
				<th>实际价格</th>
				<th>类别</th>
				<th>数量</th>
				<th>状态</th>
				<th>规格</th>
				<th>操作</th>
			</thead>
			<tbody>
				<%
					List<Product> list = (List<Product>) request.getSession().getAttribute("productList");
					int productListSize = (int) request.getAttribute("productListSize");
					for (int i = 0; i < productListSize; i++) {
				%>
				<tr id="<%=list.get(i).getProduct_id()%>">
					<td><%=list.get(i).getProduct_id()%></td>
					<td><%=list.get(i).getProduct_name()%></td>
					<td><img src='<%=list.get(i).getProduct_thumbnail()%>'
						width='30' height='30'></td>
					<td id='introduction'
						title="<%=list.get(i).getProduct_introduction()%>"><%=list.get(i).getProduct_introduction()%></td>
					<td><%=list.get(i).getProduct_originalprice()%></td>
					<td><%=list.get(i).getProduct_price()%></td>
					<td><%=list.get(i).getProduct_category()%></td>
					<td><%=list.get(i).getProduct_number()%></td>
					<td class='<%=list.get(i).getProduct_id()%>'><%=list.get(i).getPeoduct_state()%></td>
					<td><a
						href="showSkuAttribute.do?productId=<%=list.get(i).getProduct_id()%>">查看规格</a></td>
					<td><a href='javascript:void(0)'
						onclick='changestate(<%=list.get(i).getProduct_id()%>)'>上/下架</a><br>
					<a href='editer.do?productId=<%=list.get(i).getProduct_id()%>'>编辑</a></td>
				</tr>
				<%
					}
				%>
				<%-- <!-- items 要被循环的信息     var 当前条件的变量名称    varState  循环状态的变量名称 -->
			<!-- 不能用Java代码因为list.size()在jsp中不可用-->
				 <c:forEach items="${sessionScope.productList}" var="keyword" varStatus="id">
	     		<tr id="${keyword.getProduct_id()}">
				<td>${keyword.getProduct_id()}</td>
				<td>${keyword.getProduct_name()}</td>
				<td><img src='${keyword.getProduct_thumbnail()}' width='30' height='30'></td>
				<td>${keyword.getProduct_introduction()}</td>
				<td>${keyword.getProduct_originalprice()}</td>
				<td>${keyword.getProduct_price()}</td>
				<td class='${keyword.getProduct_id()}'>${keyword.getPeoduct_state()}</td>
				<td><a href='javascript:void(0)' onclick='changestate(${keyword.getProduct_id()})'>上/下架</a><a href='editer.do?id=${keyword.getProduct_id()}'>编辑</a></td>
	     		</c:forEach> --%>
			</tbody>
		</table>
	</div>
	</div>
	<%-- <%
				List<Product> list=(List<Product>)request.getSession().getAttribute("productList");
				int length=list.size();
				for(int i=0;i<length;i++){
			 %>
			 			<tr id="<%=list.get(i).getProduct_id()%>">
						<td><%=list.get(i).getProduct_id()%></td>
						<td><%=list.get(i).getProduct_name()%></td>
						<td><img src='<%=list.get(i).getProduct_thumbnail() %>' width='30' height='30'></td>
						<td><%=list.get(i).getProduct_introduction()%></td>
						<td><%=list.get(i).getProduct_originalprice()%></td>
						<td><%=list.get(i).getProduct_price()%></td>
						<td class='<%=list.get(i).getProduct_id()%>'><%=list.get(i).getPeoduct_state()%></td>
						<td><a href='javascript:void(0)' onclick='changestate(<%=list.get(i).getProduct_id()%>)'>上/下架</a><a href='editer.jsp?id=<%=list.get(i).getProduct_id()%>'>编辑</a></td>
				</tr>
				<%} %>  --%>
	<div id='pages'>
		<%
			int i = 0;
			int totalpages = (int) request.getAttribute("totalpages");
			for (i = 1; i <= totalpages; i++) {
		%>
		<a href='find.do?page=<%=i%>' class='alabel'><%=i%></a>
		<%
			}
		%>
	</div>
</body>
</html>