<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showAttribute.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <style>
  	*{
  		margin:0;
  		padding:0;
  	}
  	body{
  		background-color:#666;
  	}
  	.text{
  		width:250px;
  		height:30px;
  		font-size:24px;
  		margin-top:20px;
  	}
  	a{
  		text-decoration:none;
  		font-size:24px;
  		
  	}
  	#edit{
  		width:100px;
  		height:30px;
  		border:none;
  		background-color:black;
  		color:white;
  		margin-top:20px;
  	}
  	#submit{
  		width:100px;
  		height:30px;
  		border:none;
  		background-color:black;
  		color:white;
  		margin-top:20px;
  	}
  	#add{
  		width:100px;
  		height:30px;
  		border:none;
  		background-color:black;
  		color:white;
  		margin-top:20px;
  	}
  	.showbox{
  		width:600px;
  		background-color:#e22;
  		text-align:center;
  		margin-left:400px;
  		margin-top:80px;
  		}
  </style>
  <script src="js/jquery-3.3.1.min.js"></script>
  <script>
  	$(function(){
  		$(".text").attr("disabled",true);
  		$(".text").css("border","none");
  	})
  	function editer(){
  		$(".text").attr("disabled",false);
  		$("#submit").val("保存");
  		$("#submit").attr("type","submit");
  	}
  	function submitVerify(){
  		if($(".text").attr("disabled")){
  			window.location.href="categoryManager.do";
  		}
  	}
  	function deleteAttribute(id){
  		var attributeId=id;
  		$.ajax({
  			type:'post',
  			url:'deleteAttribute.do',
  			data:{
  				"attributeId":attributeId
  			},
  			success:function(data){
  				alert(data);
  				window.location.reload();
  			},
  			error:function(){s
				alert("Wrong!!");
				}
  		});
  	}
  	var tagId=0;
  	function addinput(){
  		tagId+=1;
  		var tagContent="<input type='text' name='attributeName' id="+tagId+" class='text'><a href='javascript:void(0)' id="+id+"  onclick='deleteinput("+id+")' >删除</a><br>"
  		$(".inputBox").append(tagContent);
  		$("#submit").val("保存");
  		$("#submit").attr("type","submit");
  	}
  	function deleteinput(id){
  		$("input[id='"+id+"']").remove();
  		$("a[id='"+id+"']").remove();
  		
  	}
  </script>
  <body>
  	<div class='showbox'>
  	<form action='attributeEditer.do?categoryId=<%=request.getAttribute("categoryId") %>' method='post' id='form'>
  	<div class='inputBox'>
  <%
  	Map<Integer,String> attributeMap=(Map<Integer,String>)request.getAttribute("attributeMap");
  	Iterator<Integer> iterator=attributeMap.keySet().iterator();
  	while(iterator.hasNext()){
  	int key=iterator.next();
   %>
   
    	<input type='text' name="<%=key%>" id="<%=key%>" class='text' value="<%=attributeMap.get(key)%>"><a href='javascript:void(0)' id="<%=key%>" onclick="deleteAttribute(id)" >删除</a>
    	<a href='showCategoryAttributeValue.do?attributeId=<%=key%>'>查看属性值</a><br>
   
    <%} %>
     </div>
    <input type='button' value='添加' id='add' onclick='addinput()'> 
    <input type='button' value='编辑' id='edit' onclick='editer()'>
    <input type='button' value='返回' id='submit' onclick='submitVerify()'>
    </form>
    </div>
  </body>
</html>
