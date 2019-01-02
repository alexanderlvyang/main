<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'brand.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		header{
			width:100%;
			height:80px;
			background-color:#666;
		}
		header p{
			font-size:30px;
			margin-left:100px;
			padding-top:20px;
		}
		table{
			width:100%;
			border-collapse:collapse;
			text-align:center;
		}
		table a{
			text-decoration:none;
			color:blue;
		}
		#addnewbrand{
			position:absolute;
			bottom:10px;
			left:0px;
			font-size:18px;
			display:block;
			text-decoration:none;
			width:100px;
			height:30px;
			text-align:center;
			line-height:30px;
			background-color:#999;
			color:white;
		}
	</style>
	<script src='js/jquery-3.3.1.min.js'></script>
	<script>
		$(function(){
			$(".deleteButton").bind("click",function(){
				var brandId=this.id;
				$.ajax({
					type:'post',
					url:'deleteBrand.do',
					data:{
						"brandId":brandId
					},
					success:function(data){
						alert(data);
						window.location.reload();
					},
					error:function(){
						alert("connection wrong");
					}
				})
			})
		})
	</script>
  </head>
  
  <body>
    <header>
  		<p>品牌管理</p>
 	</header>
   	<table border=2>
   	<%@page import="javabean.Brand" %>
   		<%
   			ArrayList<Brand> brandList=(ArrayList<Brand>)request.getAttribute("brandList");
   			int brandListSize=(int)request.getAttribute("brandListSize");
   			for(int i=0;i<brandListSize;i++){
   		 %>
   		 	<tr>
   		 		<td><%=brandList.get(i).getBrand_id() %></td>
   		 		<td><%=brandList.get(i).getBrand_name() %></td>
   		 		<td><%=brandList.get(i).getBrand_englishName() %></td>
   		 		<td><a href='javascript:void(0)' id='<%=brandList.get(i).getBrand_id() %>' class='deleteButton'>删除</a></td>
   		 	</tr>
   		 <%} %>
   		 
   		</table> 
   		<a href='addNewBrand.do' id='addnewbrand'>添加品牌</a>
	</body>
</html>







