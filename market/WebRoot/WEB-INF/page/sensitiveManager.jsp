<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Sensitive.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel='stylesheet' type='text/css' href='css/table.css'>
<style type="text/css">
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
		.addsensitive{
			display:none;
			width:500px;
			height:300px;
			background-color:#999;
			text-align:center;
			position:absolute;
			top:200px;
			left:350px;
		}
		.addsensitive label{
			font-size:24px;
		}
		.addsensitive input{
			width:250px;
			height:30px;
			padding-left:10px;
			font-size:16px;
			margin-top:100px;
		}
		.addsensitive span{
			font-size:10px;
			color:red;
			float:right;
			margin-right:80px;
			margin-top:5px;
		}
		.addsensitive a{
			display:block;
			width:150px;
			height:30px;
			text-align:center;
			line-height:30px;
			background-color:#e22;
			color:white;
			text-decoration:none;
			margin-left:200px;
			margin-top:10px;
		}
		#addButton{
			position:absolute;
			bottom:30px;
			text-decoration:none;
		}
</style>
</head>

<body>
	<header>
		<p>敏感词管理</p>
	</header>
	<table id='genericTable' border="1">
		<%@page import="javabean.Sensitive"%>
		<thead>
			<th>编号</th>
			<th>敏感词</th>
			<th>操作</th>
		</thead>
		<%
			ArrayList<Sensitive> sensitiveList = (ArrayList<Sensitive>) request.getAttribute("sensitiveList");
			int sensitiveListSize = (int) request.getAttribute("sensitiveListSize");
			for (int i = 0; i < sensitiveListSize; i++) {
		%>
		<tr>
			<td><%=sensitiveList.get(i).getSensitiveId()%></td>
			<td><%=sensitiveList.get(i).getSensitiveValue()%></td>
			<td><a href='javascript:void(0)' onclick='deleteSensitive(<%=sensitiveList.get(i).getSensitiveId()%>)'>删除</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<div class='addsensitive'>
		<label>敏感词：</label>
		<input type='text' name='sensitive' id='sensitive'/><br>
		<span>多个敏感词请用逗号隔开</span><br>
		<a href='javascript:void(0)' onclick="addSensitive()">添加</a>
	</div>
	<a href="javascript:void(0)" onclick="showSensitive()" id='addButton'>添加敏感词</a>
</body>
<script src="js/jquery-3.3.1.min.js"></script>
<script>
	function showSensitive(){
		$(".addsensitive").show();
	}
	function addSensitive(){
		var sensitive=$("#sensitive").val();
		$.ajax({
			type:'post',
			url:'addSensitive.do',
			data:{
				"sensitive":sensitive
			},
			success:function(data){
				alert(data);
				$(".addsensitive").hide();
				window.location.reload();
			},
			error:function(){
				alert("Wrong!!!");
			}
		})
	}
	function deleteSensitive(sensitiveId){
		$.ajax({
			type:'post',
			url:'deleteSensitive.do',
			data:{
				"sensitiveId":sensitiveId
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
</html>
