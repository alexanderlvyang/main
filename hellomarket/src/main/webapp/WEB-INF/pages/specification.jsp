<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".productStatus").change(function(){
			var status=$(this).val();
			var specificationId=$(this).parent().siblings().eq(0).html();
			$.ajax({
				type:"post",
				url:"updateSpecificationStatus.do",
				data:{
					"status":status,
					"specification_id":specificationId
				},
				success:function(data){
					alert(data);
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
	}
	.table td{
		width:200px;
		text-align:center;
	}
	.table th{
		width:200px;
		text-align:center;
	}
	#add{
		display:block;
		width:150px;
		height:20px;
		line-height:20px;
		text-align:center;
		background-color:#e22;
		color:white;
		position:absolute;
		top:80px;
		right:0px;
	}
	.productStatus{
		width:80px;
	}
	.thumbnail{
		width:50px;
		height:50px;
	}
</style>
</head>
<body>
	<div class="tableDiv">
		<table class="table">
			<thead>
				<th>编号</th>
				<th>价格</th>
				<th>颜色</th>
				<th>规格</th>
				<th>状态</th>
				<th>操作</th>
			</thead>
			<tbody>
				<c:forEach items="${productSpecificationList}" var="specification">
					<tr>
						<td>${specification.specification_id }</td>
						<td>${specification.price }</td>
						<td>${specification.product_color }</td>
						<td>${specification.product_specification }</td>
						<td>
							<img src="files/${specification.product_thumbnail }" class="thumbnail">
						</td>
						<td>
							<select class="productStatus">
								<option value="${specification.status}">${specification.status}</option>
								<c:if test="${specification.status=='上架'}">
									<option value="下架">下架</option>
								</c:if>
								<c:if test="${specification.status=='下架'}">
									<option value="上架">上架</option>
								</c:if>
							</select>
						</td>
						<td><a href="updateSpecification.do?specification_id=${specification.specification_id }" class="updateSepecification">修改</a></td>
					</tr>
				</c:forEach>
			</tbody>	
		</table>
	</div>
	<a href="addspecificationpage" id="add">添加</a>
</body>
</html>