<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showAttributeValue.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type='text/css' href='css/table.css'>
	<style>
		*{
			margin:0;
			padding:0;
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
		.text{
			width:250px;
			height:25px;
			margin-top:10px;
		}
		.inputBox{
			width:500px;
			height:300px;
			overflow:auto;
			text-align:center;
		}
		.inputBox a{
			text-decoration:none;
			margin-left:10px;
			font-size:26px;
		}
		#firstinput{
			margin-right:17px;
		}
		.formBox{
			width:500px;
			height:350px;
			background-color:#999;
			text-align:center;
			display:none;
		}
		#send{
			width:150px;
			height:30px;
			background-color:#e22;
			color:white;
			border:none;
			
		}
		#form{
			position:absolute;
			top:200px;
			left:350px;
			
		}
		#addAttribute{
			position:absolute;
			bottom:20px;
			left:5px;
			text-decoration:none;
			display:block;
			width:150px;
			height:30px;
			background-color:#e22;
			color:white;
			text-align:center;
			line-height:30px;
		}
	</style>
	<script src='js/jquery-3.3.1.min.js'></script>
	<script>
	var tagId=0;
		function addinput(){
			tagId+=1;
  			var tagContent="<input type='text' name='attrvalue' id="+tagId+" class='text'><a href='javascript:void(0)' id="+tagId+"  onclick='addinput()'>+</a>"
  			+"<a href='javascript:void(0)' id="+tagId+"  onclick='deleteinput("+tagId+")' >-</a><br>"
			$(".inputBox").append(tagContent);
		}
		function deleteinput(id){
			$("input[id='"+id+"']").remove();
  			$("a[id='"+id+"']").remove();
		}
		function showformBox(){
			$(".formBox").show();
		}
		function deleteAttributeValue(id){
			var attributeValueId=id;
			$.ajax({
				type:'post',
				url:'deleteAttributeValue.do',
				data:{
					"attributeValueId":attributeValueId
				},
				success:function(data){
					alert(data);
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
  <header>
  	<p>属性值管理</p>
  </header>
    <table id='genericTable' border='1'>
    	<thead>
    		<th>属性值编号</th>
    		<th>属性值</th>
    		<th>操作</th>
    	</thead>
    	<tbody id="genericTable">
    	<%@page import="javabean.CategoryAttributeValue" %>
    	<%
    			ArrayList<CategoryAttributeValue> categoryAttributelist=(ArrayList<CategoryAttributeValue>)request.getAttribute("categoryAttributelist");
    		int categoryAttributelistSize=(int)request.getAttribute("categoryAttributelistSize");
    		for(int i=0;i<categoryAttributelistSize;i++){
    	 %>
    		<tr>
    			<td><%=categoryAttributelist.get(i).getAttributeValueId() %></td>
    			<td><%=categoryAttributelist.get(i).getAttributeValue() %></td>
    			<td><a href='javascript:void(0)' onclick='deleteAttributeValue(<%=categoryAttributelist.get(i).getAttributeValueId()%>)'>删除</a></td>
    		</tr>
    	<%
    		}
    	 %>	
    	</tbody>
    </table>
    <form action='addCategoryAttributeValue.do?attributeId=<%=request.getAttribute("attributeId") %>' method='post' id='form'>
    <div class='formBox'>
	    <div class="inputBox">
	    	<input type='text' name='attrvalue' class='text' id='firstinput'><a href='javascript:void(0)' onclick='addinput()'>+</a><br>
	    </div>
	   <input type='submit' id='send' value='提交'>
    </div>
    </form>
    <a href='javascript:void(0)' id='addAttribute' onclick='showformBox()'>添加属性值</a>
  </body>
</html>
