<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/usermanage.js"></script>
<link rel="stylesheet" href="css/usermanage.css">
</head>
<body>
	<div class="searchDiv">
		<form action="userManage.do" method="post">
			<input type="text" name="condition" id="condition" value="${sessionScope.condition}" placeholder="用户名/手机号/状态">
			<input type="submit" value="查询" id="seachButton">
		</form>
	</div>
	<div class="tableDiv">
		<table class="userManageTable">
			<thead>
				<th>编号</th>
				<th>用户名</th>
				<th>密码</th>
				<th>手机号</th>
				<th>邮箱</th>
				<th>状态</th>
			</thead>
			<tbody>
				<c:forEach items="${allUserList}" var="userList">
					<tr>
						<td>${userList.id}</td>
						<td>${userList.username }</td>
						<td>${userList.password  }</td>
						<td>${userList.phone }</td>
						<td>${userList.email }</td>
						<td>
							<select class="selectStatus">
								<option value="${userList.status }" selected="selected">${userList.status }</option>
								<option>VIP</option>
								<option>封号</option>
								<option>普通用户</option>
							</select>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pageDiv">
		<div>
			<c:forEach var="page" step="1" begin="1" end="${totalPage}">
				<a href="userManage.do?currentPage=${page}&condition=${sessionScope.condition}">${page}</a>
			</c:forEach>
		</div>
	</div>
</body>
</html>