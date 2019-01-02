<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'commentManager.jsp' starting page</title>
    
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
		.genericTable a{
			text-decoration:none;
		}
		.pages{
			float:right;
		}
		.pages a{
			text-decoration:none;
			float:left;
			margin-right:10px;
		}
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
		.condition{
			width:250px;
			height:30px;
		}
		#searchButton{
			text-decoration:none;
			display:block;
			width:100px;
			height:30px;
			background-color:#e22;
			color:white;
			text-align:center;
			line-height:30px;
			position:absolute;
			top:99px;
			right:60px;
		}
		.searchBox{
			margin-top:20px;
			width:93%;
			height:50px;
			border:1px solid red;
			padding-left:80px;
		}
	</style>
	<script src='js/jquery-3.3.1.min.js'></script>
	<script>
	$(function(){
		<%
		Map<String,String> findconditionmap=(Map<String,String>)request.getSession().getAttribute("findComment");
		if(findconditionmap!=null){
		Iterator<String> it=findconditionmap.keySet().iterator();
		String key="";
		while(it.hasNext()){
			key=it.next();
			if(key.equals("product_id")){%>
			$("#productId").val("<%=findconditionmap.get(key)%>");
		<%}%>
		<%if(key.equals("comment_level")){%>	
			$("#commentLevel").val("<%=findconditionmap.get(key)%>");
		<%}%>
		<%if(key.equals("createTime")){%>	
			$("#createTime").val("<%=findconditionmap.get(key)%>");
		<%}%>
	<%}%>
	<%}%>
	})
	function deleteComment(id){
			var commentId=id;
			$.ajax({
				type:'post',
				url:'deleteComment.do',
				data:{
					"commentId":commentId
				},
				success:function(data){
					alert(data);
					$("."+id).remove();
				},
				error:function(){
					alert("Wrong!!!");
				}
			});
		}
		/* function searchMethod(){
			var productId=$("#productId").val();
			var commentLevel=$("#commentLevel").val();
			var createTime=$("#createTime").val();
			$.ajax({
				type:'post',
				url:'findCommentByCondition.do',
				data:{
					"productId":productId,
					"commentLevel":commentLevel,
					"createTime":createTime
				},
				success:function(data){
					var obj=eval(data);
					
					var str="";
					var astr="";
					for(i in obj){
						str+="<tr><td>"+obj[i].commentId+"</td><td>"+obj[i].productId+"</td><td>"+
						obj[i].commentLevel+"</td><td>"+obj[i].commentValue+"</td><td>"+obj[i].customerId+"</td><td>"+
						obj[i].customerName+"</td><td>"+obj[i].createTime+"</td><td><a href='showCommentImage.do?commentId="+obj[i].commentId+"'>查看评论晒图</a>"
					}
					var k=(x/20)+1;
						alert(k);
							for(var j=1;j<=k;j++)
							astr+="<a>"+j+"</a>";
					$(".pages").html(astr);
					alert(str);
					$(".tbody").html(str);
				},
				error:function(){
					alert("Wrong!!!");
				}
			})
		} */
		
	</script>
  </head>
  
  <body>
  <header>
  	<p>评论管理</p>
  </headers>
  	<div class='searchBox'>
  	<form action='commentManager.do' method='post'>
  		<label>商品ID</label><input type='text' name='productId' class='condition' id='productId'>
  		<label>评论级别</label><input type='text' name='commentLevel' class='condition' id='commentLevel'>
  		<label>评论时间</label><input type='date' name='createTime' class='condition' id='createTime'>
  		<input type='submit' value='查找' id='searchButton'>
  	</form>
  	</div>
    <table class="genericTable" border=2>
    	<thead>
    		<th>评论id</th>
    		<th>商品id</th>
    		<th>评论级别</th>
    		<th>评论内容</th>
    		<th>评论人id</th>
    		<th>评论人昵称</th>
    		<th>评论时间</th>
    		<th>查看晒图</th>
    		<th>操作</th>
		</thead>
		<%@page import="javabean.Comment" %>    	
		<tbody class='tbody'>
		<%
    		ArrayList<Comment> commentList=(ArrayList<Comment>)request.getAttribute("commentList");
    		int commentListSize=(int)request.getAttribute("commentListSize");
    		for(int i=0;i<commentListSize;i++){
    	 %>
    	<tr class='<%=commentList.get(i).getCommentId() %>'>
    		<td><%=commentList.get(i).getCommentId() %></td>
    		<td><%=commentList.get(i).getProductId() %></td>
    		<td><%=commentList.get(i).getCommentLevel() %></td>
    		<td title='<%=commentList.get(i).getCommentValue() %>'><%=commentList.get(i).getCommentValue() %></td>
    		<td><%=commentList.get(i).getCustomerId() %></td>
    		<td><%=commentList.get(i).getCustomerName() %></td>
    		<td><%=commentList.get(i).getCreateTime() %></td>
    		<td><a href='showCommentImage.do?commentId=<%=commentList.get(i).getCommentId()%>'>查看该评论图片</a></td>
    		<td><a href='javascript:void(0)' id='<%=commentList.get(i).getCommentId() %>' onclick='deleteComment(id)'>删除</a></td>
    	</tr>
    	<%} %>
    	</tbody>
    </table>
    <div class='pages'>
    	<%
    		int totalPage=(int)request.getAttribute("totalPage");
    		for(int i=1;i<=totalPage;i++){
    	 %>
    	<a href='commentManager.do?currentPage=<%=i%>'><%=i %></a>
    	<%} %>
    </div>
  </body>
</html>
