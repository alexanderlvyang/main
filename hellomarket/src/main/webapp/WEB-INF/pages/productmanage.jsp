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
		$(".productStatus").change(function(){
			var productId=$(this).parent().siblings().eq(0).html();
			var productStatus=$(this).val();
			$.ajax({
				type:"post",
				url:"updateProductStatus.do",
				data:{
					"productId":productId,
					"productStatus":productStatus
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
<link rel="stylesheet" href="css/productmanage.css">
</head>
<body>
	<div class="searchDiv">
		<form action="productManage.do" method="post">
			<input type="text" name="condition" id="condition" value="${sessionScope.condition}" placeholder="名称/状态"/>
			<input type="submit" value="确定" id="searchButton">
		</form>
	</div>
	<a href="addProduct.do" target="showContent" id="addProduct">添加商品</a>
	<div class="tableDiv">
		<table class="table">
			<thead>
				<th>编号</th>
				<th>名称</th>
				<th>简介</th>
				<th>价格</th>
				<th>详细</th>
				<th>状态</th>
				<th>操作</th>
			</thead>	
			<tbody>
				<c:forEach items="${productList}" var="product">
					<tr>
						<td>${product.product_id}</td>
						<td>${product.product_name}</td>
						<td class="introduction">${product.product_introduction}</td>
						<td>${product.product_price }</td>
						<td class="describe">${product.product_describe}</td>
						<td>
							<select class="productStatus">
								<option value="${product.product_status}">${product.product_status}</option>
								<c:if test="${product.product_status=='上架'}">
									<option value="下架">下架</option>
								</c:if>
								<c:if test="${product.product_status=='下架'}">
									<option value="上架">上架</option>
								</c:if>
							</select>
						</td>
						<td><a href="showSpecification.do?product_id=${product.product_id}" class="showSpecification">规格</a>/<a href="showProductById.do?product_id=${product.product_id}" class="updateProduct">修改</a>
							/<a href="showProductImage.do?product_id=${product.product_id}" class="showProductImage">图片</td>
					</tr>
				</c:forEach>	
			</tbody>
		</table>
	</div>
	<div class="pageDiv">
		<c:forEach var="page" begin="1" end="${totalPage}">
				<a href="productManage.do?startPage=${page}&condition=${sessionScope.condition}">${page}</a>
		</c:forEach>
	</div>
	
</body>
</html>