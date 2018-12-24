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
		$("#type").change(function(){
			var type=$(this).val();
			window.location="advertisementManage.do?advertise_type="+type;
		});
		$(".delete").click(function(){
			var advertise_id=$(this).parent().siblings().eq(0).html();
			$.ajax({
				type:"post",
				url:"deleteAdvertisement.do",
				data:{
					"advertise_id":advertise_id
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
		$(".update").click(function(){
			var advertise_id=$(this).parent().siblings().eq(0).html();
			window.location="showAdvertiseById.do?advertise_id="+advertise_id;
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
	#image{
		width:100px;
		height:80px;
	}
	th{
		width:100px;
	}
	.tableDiv{
		background:rgba(255,255,255,0.2);
	}
	table{
		width:100%;
		border-collapse: separate;
		border-spacing:0px 20px;
		text-align:center;
	}
	.addDiv{
		margin-top:70px;
	}
	#add{
		display:block;
		width:140px;
		height:20px;
		text-align:center;
		line-height:20px;
		background:#e22;
		color:white;
		text-decoration:none;
		position:absolute;
		top:70px;
		left:950px;
	}
	#type{
		width:100px;
	}
</style>
</head>
<body>
	<div class="addDiv">
		<label>选择分类</label>
		<select id="type">
			<option value="${sessionScope.advertise_type }">${sessionScope.advertise_type }</option>
				<c:if test="${sessionScope.advertise_type =='图片'}">
					<option value="文本">文本</option>
				</c:if>
				<c:if test="${sessionScope.advertise_type =='文本'}">
					<option value="图片">图片</option>
				</c:if>
		</select>
	</div>
		<a href="addadvertisementpage" id="add">添加</a>
	<div class="tableDiv">
		<table>
			<thead>
				<th>编号</th>
				<th>名称</th>
				<th>
					<c:if test="${sessionScope.advertise_type =='图片'}">
						图片
					</c:if>
					<c:if test="${sessionScope.advertise_type =='文本'}">
						文本
					</c:if>
				</th>
				<th>投放范围</th>
				<th>链接</th>
				<th>类型</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>操作</th>
			</thead>
			<tbody>
				<c:forEach items="${advertisementList }" var="advertise">
					<tr>
						<td>${advertise.advertise_id }</td>
						<td>${advertise.advertise_name }</td>
						<td>
							<c:if test="${sessionScope.advertise_type =='图片'}">
								<img src="files/${advertise.advertise_thumbnail }" id="image">
							</c:if>
							<c:if test="${sessionScope.advertise_type =='文本'}">
								${advertise.advertise_content }
							</c:if>
						</td>
						<td>${advertise.advertise_range }</td>
						<td>${advertise.advertise_link }</td>
						<td>${advertise.advertise_type }</td>
						<td>${advertise.beginTime }</td>
						<td>${advertise.endTime }</td>
						<td><a href="javascript:void(0)" class="update">修改</a>/<a href="javascript:void(0)" class="delete">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>