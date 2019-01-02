<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="javabean.Category" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'categorymanager.jsp' starting page</title>
    
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
		}
		#addnewchildcategory{
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
		#return{
			position:absolute;
			top:45px;
			left:1115px;
			text-decoration:none;
			color:#e22;
			font-size:20px;
			
		}
	</style>
	<script src='js/jquery-3.3.1.min.js'></script>
	<script type="text/javascript">
	/* 	$(function(){
			$(".delete").bind("click",function(){
				var categoryid=this.id;
				$.ajax({
					type:'post',
					url:'deletCategory.do',
					data:{
						"category_id":categoryid
					},
					success:function(data){
						alert(data);
						 window.location.reload();
					},
					error:function(){
						alert("wrong");
					}
				})
			})
		}) */
		function deleteCategory(id){
		var categoryId=id;
			$.ajax({
				type:'post',
				url:'deleteCategory.do',
				data:{
					"categoryId":categoryId
				},
				success:function(data){
					alert(data);
					window.location.reload();
				},
				error:function(){
				
				}
			})
		}
	</script>
  </head>
  
  <body>
  <header>
  	<p>子分类管理</p>
  	<a href='categoryManager.do' id='return'>返回</a>
  </header>
    <div>
    	
    	<table border=2>
    		<%
    			ArrayList<Category> categoryList=(ArrayList<Category>)request.getAttribute("categoryList");
    			int categoryFatherId=(int)request.getAttribute("fatherid");
    			int categoryListSize=(int)request.getAttribute("categoryListSize");
    			for(int i=0;i<categoryListSize;i++){
    			
    		%>
    		<tr>
    		<td><%=categoryList.get(i).getCategory_id() %></td>
    		<td><%=categoryList.get(i).getCategory_name() %></td>
    		<td><a href='thirdChildCategoryManager.do?categoryid=<%=categoryList.get(i).getCategory_id()%>&fatherId=<%=request.getAttribute("fatherid")%>'>查看子分类</a></td>
    		<td><a href="javascript:void(0)" class='delete' onclick='deleteCategory(id)' id='<%=categoryList.get(i).getCategory_id() %>'>删除</a></td>
    		</tr>
    		<%} %>
    	</table>
    </div>
    	<a href='addSecondChildCategory.do?categoryFatherId=<%=categoryFatherId %>' id='addnewchildcategory'>添加新分类</a>
  </body>
</html>


