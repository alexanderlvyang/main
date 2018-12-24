<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("a").click(function(){
			$(this).css("color","red");
			$(this).siblings().css("color","blue");
		});
	});
</script>
<style type="text/css">
	*{
		margin:0;
		padding:0;
	}
	body{
		background:url(images/menubackground.jpg) no-repeat;
		background-size:100% 100%;
		background-attachment:fixed;
	}
	a{
		text-decoration:none;
		font-size:20px;
		display:block;
		width:200px;
		height:40px;
		line-height:40px;
		text-align:center;
		background-color:white;
		margin-left:30px;
		margin-top:20px;
		color:blue;
	}
</style>
</head>
<body>
	<a href="userManage.do" target="showContent" id="userManage">用户管理</a>
	<a href="categoryManage.do" target="showContent" id="categoryManage">分类管理</a>
	<a href="brandManage.do" target="showContent" id="brandManage">品牌管理</a>
	<a href="productManage.do" target="showContent" id="productManage">商品管理</a>
	<a href="rotationChartManage.do" target="showContent" id="rotationChartManage">轮播图管理</a>
	<a href="advertisementManage.do" target="showContent" id="advertisementManage">广告管理</a>
	<a href="orderManage.do" target="showContent" id="orderManage">订单管理</a>
	<a href="sensitiveManage.do" target="showContent" id="sensitiveManage">敏感词管理</a>
	<a href="commentManage.do" target="showContent" id="commentManage">评论管理</a>
	<a href="reportFormsManage.do" target="showContent" id="reportFormsManage">统计报表</a>
</body>
</html>