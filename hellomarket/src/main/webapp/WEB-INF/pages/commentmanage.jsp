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
			var comment_id = $(this).parent().siblings().eq(0).html();
			$.ajax({
				type:"post",
				url:"deleteComment.do",
				data:{
					"comment_id":comment_id
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
	.tableDiv{
		background:rgba(255,255,255,0.2);
		margin-top:50px;
	}
	.table{
		border-collapse:separate;
		border-spacing:0px 5px;
	}
	.table td{
		text-align:center;
	}
	.table th{
		width:200px;
		text-align:center;
	}
	.searchDiv{
		text-align:center;
		height:80px;
		line-height:80px;
	}
	#condition{
		width:250px;
		height:20px;
	}
	#searchButton{
		background:#e22;
		color:white;
		width:100px;
		height:22px;
		border:none;
	}
	.pageDiv{
		background:#e22;
		text-align:center;
		position:absolute;
		bottom:1px;
		width:100%;
	}
	.pageDiv a{
		color:white;
	}
</style>
</head>
<body>
	<div class="searchDiv">
		<form action="commentManage.do" method="post">
			<input type="text" name="condition" id="condition" value="${sessionScope.condition}" placeholder="商品名/等级"/>
			<input type="submit" value="确定" id="searchButton">
		</form>
	</div>
	<div class="tableDiv">
		<table class="table">
			<thead>
				<th>编号</th>
				<th>评论内容</th>
				<th>用户名</th>
				<th>商品id </th>
				<th>等级 </th>
				<th>评论时间 </th>
				<th>操作</th>
			</thead>
			<tbody>
				<c:forEach items="${commentList }" var="comment">
					<tr>
						<td>${comment.comment_id }</td>
						<td>${comment.comment_content }</td>
						<td>${comment.username }</td>
						<td>${comment.product_name }</td>
						<td>${comment.comment_grade }</td>
						<td>${comment.createTime }</td>
						<td><a href="commentImageManage.do?comment_id=${comment.comment_id }" class="thumbnail">评论图</a>/<a href="javascript:void(0)" class="delete">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pageDiv">
		<c:forEach var="page" begin="1" end="${totalPage}">
				<a href="commentManage.do?startPage=${page}&condition=${sessionScope.condition}">${page}</a>
		</c:forEach>
	</div>
</body>
</html>