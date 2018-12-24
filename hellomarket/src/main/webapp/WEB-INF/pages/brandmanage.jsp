<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/brandmanage.js"></script>
<link rel="stylesheet" href="css/brandmanage.css">
</head>
<body>
	<div class="searchDiv">
		<form action="brandManage.do" method="post">
			<input type="text" name="condition" id="condition" placeholder="名称/公司名"  value="${sessionScope.condition}"/>
			<input type="submit" value="确定" id="searchButton"/>
		</form>
		<input type="button" value="添加新品牌" id="addBrand">
	</div>
	<div class="tableDiv">
		<table class="brandTable">
			<thead>
				<th>编号</th>
				<th>名称</th>
				<th>英文名</th>
				<th>公司</th>
				<th>入驻时间</th>
			</thead>
			<c:forEach items="${brandList }" var="brand">
					<tr>
						<td>${brand.brand_id }</td>
						<td>${brand.brand_name }</td>
						<td>${brand.brand_englishName }</td>
						<td>${brand.brand_company }</td>
						<td>${brand.brand_joinTime }</td>
						<td><a href="javascript:void(0)" class="delete">删除</a></td>
					</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pageDiv">
		<c:forEach var="page" begin="1" end="${totalPage }">
			<a href="brandManage.do?startPage=${page }&condition=${sessionScope.condition}">${page }</a>
		</c:forEach>
	</div>
	<div class="addDiv">
		<table class="addTable">
			<tr>
				<td>名称：</td>
				<td><input type="text" name="brand_name" id="brandName"></td>
			</tr>
			<tr>
				<td>英文名：</td>
				<td><input type="text" name="brand_englishName" id="brandEnglishName"></td>
			</tr>
			<tr>
				<td>公司：</td>
				<td><input type="text" name="brand_company" id="brandCompany"></td>
			</tr>
			<input type="button" value="确定" id="confirmButton"><input type="button" value="取消" id="cancelButton">
 		</table>
	</div>
</body>
</html>