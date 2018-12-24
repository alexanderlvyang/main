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
			var commentImage_id = $(this).parent().siblings().eq(0).html();
			$.ajax({
				type:"post",
				url:"deleteCommentImage.do",
				data:{
					"commentImage_id":commentImage_id
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
		margin-top:100px;
	}
	.table{
		border-collapse:separate;
		border-spacing:0px 5px;
		margin-left:200px;
	}
	.table td{
		text-align:center;
	}
	.table th{
		width:200px;
		text-align:center;
	}
</style>
</head>
<body>
	<div class="tableDiv">
		<table class="table">
			<thead>
				<th>编号</th>
				<th>图片</th>
				<th>操作</th>
			</thead>
			<tbody>
				<c:forEach items="${commentImageList }" var="commentImage">
					<tr>
						<td>${commentImage.commentImage_id }</td>
						<td><img src="files/${commentImage.comment_thumbnail }" class="image"></td>
						<td><a href="javascript:void(0)" class="delete">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>