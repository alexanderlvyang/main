<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/categorymanage.js"></script>
<link rel="stylesheet" href="css/categorymanage.css">
</head>
<body>

	<div class="title">
		<strong id="titleContent">分类管理</strong>
	</div>
	<div class="addParent">
		<p class="addCategoryP"><input type="text" name="categoryName" class="categoryName"><input type="button" value="确定" class="confirmParentButton"><input type="button" value="取消" class="cancelButton"></p>
		<a href="javascript:void(0)" class="addCategory">添加分类</a>
	</div>
	<div class="container">
		<c:forEach items="${categoryList}" var="category">
		<div>
			<c:if test="${category.parentId==0}">
				<p class="parentP"><a href="#" class="parent">${category.name }</a><a href="#" class="delete">删除</a></p>
				<div class="childDiv">
					<c:forEach items="${categoryList }" var="childCategory">
						<c:if test="${childCategory.parentId == category.id }">
							<p class="childP"><a href="#" class="child">${childCategory.name }</a><a href="#" class="delete">删除</a></p>
							<div class="grandChildDiv">
									<c:forEach items="${categoryList }" var="grandChildCategory">
										<c:if test="${grandChildCategory.parentId == childCategory.id}">
											<p class="grandChildP"><a href="#" class="grandChild">${grandChildCategory.name}</a><a href="#" class="delete">删除</a></p>
										</c:if>
									</c:forEach>
								<p class="addChild"><input type="text" name="categoryName" class="categoryName"><input type="button" value="确定" class="confirmButton"><input type="button" value="取消" class="cancelButton"></p>
								<a href="javascript:void(0)" class="addChildCategory">添加分类</a>
							</div>
						</c:if>
					</c:forEach>
				<p class="addChild"><input type="text" name="categoryName" class="categoryName"><input type="button" value="确定" class="confirmButton"><input type="button" value="取消" class="cancelButton"></p>
				<a href="javascript:void(0)" class="addChildCategory">添加分类</a>
				</div>
			</c:if>
		</div>	
		</c:forEach>
	</div>
	<div class="warnDiv">
		<p id="warn">确认删除名称为<strong id="categoryName"></strong>的分类吗？删除该分类将同时删除该分类下的所有子分类</p>
		<a href="javascript:void(0)" id="confirm">确认</a><a href="javascript:void(0)" id="cancel">取消</a>
	</div>

</body>
</html>