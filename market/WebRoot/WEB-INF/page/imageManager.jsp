<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'imageManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	*{
		margin:0;
		padding:0;
	}
		header{
			width:100%;
			height:80px;
			background-color:#666;
			line-height:80px;
			
		}
		header p{
			font-size:30px;
			margin-left:100px;
		}
		#searchText{
			width:350px;
			height:40px;
			font-size:20px;
			padding-left:10px;
		}
		.searchBox{
			position:absolute;
			top:100px;
			width:1180px;
			height:50px;
			line-height:50px;
			text-align:center;
		}
		#searchBtn{
			display:block;
			position:absolute;
			top:0px;
			left:764px;
			width:40px;
			height:40px;
			text-align:center;
			line-height:40px;
			background-color:#e22;
		}
		.tableBox{
			position:absolute;
			top:170px;
			left:20px;
			width:1150px;
			height:470px;
			border:1px solid green;
		}
		.tableBox table{
			width:1150px;
			border-collapse:collapse;
			text-align:center;
		}
		#addImage{
			text-decoration:none;
			color:blue;
			font-size:20px;
			position:absolute;
			top:140px;
			right:40px;
		}
		.tableBox table a{
			text-decoration:none;
			color:blue;
			
		}
		.pagesBox{
			position:absolute;
			bottom:0px;
			width:100%;
			height:20px;
			
		}
		.pagesBox a{
			float:left;
			text-decoration:none;
			color:blue;
			margin-left:20px;
			
		}
	</style>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script>
	$(function(){
	if(<%=request.getAttribute("productId")%>!=null){
		$("#searchText").val("<%=request.getAttribute("productId")%>");
	
	}
	})
		function submitId(){
			$("#form").submit();
		}
		function delectImage(imageId){
			$.ajax({
				type:'post',
				url:'deleteImageOperation.do',
				data:{
					"imageId":imageId
				},
				success:function(data){
					alert(data);
					window.location.reload();
				},
				error:function(){
					alert("Wrong!!!");
				}
			})
		}
	</script>
  </head>
  
  <body>
    <header>
    	<p>图片管理</p>
    </header>
    <div class='searchBox'>
    	<form action="imageManagerPage.do" method="post" id="form">
	    	<input type='text' name='productId' id='searchText'  placeholder="input the key word to search image">
	    	<a href="javascript:void(0)" id='searchBtn' onclick="submitId()"><img src='image/searchbtn.png' width='40' height='40'></a>
  		</form>
    </div>
    	<a href="addImagePage.do" id='addImage'>添加图片</a>
    <div class='tableBox'>
	    <table border='1'>
	    	<thead>
	    		<th>id</th>
	    		<th>图片</th>
	    		<th>商品id</th>
	    		<th>商品类型</th>
	    		<th colspan="2">操作</th>
	    	</thead>
	    	<tbody>
	    	<%@page import="javabean.Image" %>
	    	<%
	    		ArrayList<Image> imageList=(ArrayList<Image>)request.getAttribute("imageList");
	    		int imageListSize=(int)request.getAttribute("imageListSize");
	    		for(int i=0;i<imageListSize;i++){
	    	 %>
	    		<tr>
	    			<td><p><%=imageList.get(i).getImageId() %></p></td>
					<td><img src="<%=imageList.get(i).getImageUrl() %>" width="60" height="40"></td>
					<td><p><%=imageList.get(i).getProductId() %></p></td>
					<td><p><%=imageList.get(i).getImageCategory() %></p></td>
	    			<td><p><a href="javascript:void(0)" id="<%=imageList.get(i).getImageId() %>" onclick="delectImage(id)">delete</a></p></td>
	    			<td><p><a href="updateImagePage.do?imageId=<%=imageList.get(i).getImageId() %>">update</a></p></td>
	    		</tr>
	    		<%} %>
	    	</tbody>
	    	
	    </table>
	    <div class='pagesBox'>
	    	<%
	    		int totalPages=(int)request.getAttribute("totalPages");
	    		for(int i=1;i<=totalPages;i++){
	    	 %>
	    	 	<a href="imageManagerPage.do?currentPage=<%=i %>"><%=i %></a>
	    	 <%} %>
	    </div>
    </div>
  </body>
</html>
