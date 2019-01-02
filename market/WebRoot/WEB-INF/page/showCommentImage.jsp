<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showCommentImage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<script src='js/jquery-3.3.1.min.js'></script>
	<script>
		function deleteOperation(id){
			$.ajax({
				type:'post',
				url:'deleteCommentImage.do',
				data:{
					"commentImageId":id
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
    <table id='genericTable' border='2'>
    	<thead>
    		<th>评论图id</th>
    		<th>评论图</th>
    		<th>操作</th>
    	</thead>
    	<%@page import="javabean.CommentImage" %>
    	<%
    		ArrayList<CommentImage> commentImageList=(ArrayList<CommentImage>)request.getAttribute("commentImageList");
    		int commentImageListSize=(int)request.getAttribute("commentImageListSize");
    		for(int i=0;i<commentImageListSize;i++){
    	 %>
    	<tr>
    		<td><%=commentImageList.get(i).getCommentImageId() %></td>
    		<td><img src='<%=commentImageList.get(i).getImageSrc() %>' width='50px' height='50px'></td>
    		<td><a href='javascript:void(0)' onclick='deleteOperation(<%=commentImageList.get(i).getCommentImageId()%>)'>删除</a></td>
    	</tr>
    	<%} %>
    </table>
  </body>
</html>
