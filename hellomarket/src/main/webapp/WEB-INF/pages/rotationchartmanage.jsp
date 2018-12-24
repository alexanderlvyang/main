<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var chart_id = $(this).parent().siblings().eq(0).html();
			$.ajax({
				type:"post",
				url:"deleteRotationChart.do",
				data:{
					"chart_id":chart_id
				},
				success:function(data){
					alert(data);
					window.location.reload();
				},
				error:function(){
					alert("请求出错");					
				}
			});
		});
	});
</script>
<style type="text/css">
	*{
		margin:0;
		padding:0;
	}
	body{
		background:url(../images/menu-background.jpg) no-repeat;
		background-size:100% 100%;
		background-attachment:fixed;
	}
	a{
		text-decoration:none;
	}
	#delete{
		text-decoration:none;
		font-size:18px;
	}
	.tableDiv{
		background:rgba(255,255,255,0.2);
		margin-top:80px;
	}
	table{
		width:100%;
		border-collapse: separate;
		border-spacing:0px 30px;
		text-align:center;
	}
	th{
		width:200px;
	}
	#image{
		width:100px;
		height:80px;
	}
	#addRotationChart{
		display:block;
		width:150px;
		height:20px;
		background-color:#e22;
		color:white;
		text-align:center;
		line-height:20px;
		position:absolute;
		top:60px;
		left:940px;
		text-decoration:none;
	}
</style>
</head>
<body>
	<a href="addrotationchartpage" id="addRotationChart">添加</a>
	<div class="tableDiv">
		<table>
			<thead>
				<th>编号</th>
				<th>图片</th>
				<th>链接</th>
				<th>备注</th>
				<th>创建日期</th>
				<th>操作</th>
			</thead>
			<tbody>
				<c:forEach items="${rotationChartList }" var="chart">
					<tr>
						<td>${chart.chart_id }</td>
						<td><img src="files/${chart.chart_thumbnail }" id="image"></td>
						<td>${chart.chart_link }</td>
						<td>${chart.remarks }</td>
						<td>${chart.createTime }</td>
						<td><a href="javascript:void(0)" class="delete">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>