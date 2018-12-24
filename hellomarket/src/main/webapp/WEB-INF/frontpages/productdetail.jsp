<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="UTF-8" src="ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="UTF-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="js/move.js"></script>
<script type="text/javascript" src="js/productdetail.js"></script>
<link rel="stylesheet" type="text/css" href="css/productdetail.css">
</head>
<body>
	<div class="header" productId="${productInfoList[0].product_id}">
		<ul class="navigation">
			<c:if test="${empty sessionScope.user }">
				<li><a href="loginfront">请登录</a></li>
				<li><a href="registerfront" id="register">请注册</a></li>
			</c:if>
			<c:if test="${not empty sessionScope.user}">
				<li><a href="personInfo" id="username" >${sessionScope.user.username }</a></li>
				<li><a href="outLogin">退出登陆</a></li>
			</c:if>
			<li><a href="shoppingCartPage">购物车</a></li>
			<li><a href="javascript:void(0)">我的订单</a></li>
		</ul>
	</div>
	<div class="searchDiv">
		<form action="search" method="post">
			<input type="text" name="condition" id="condition" ><input
				type="submit" id="searchButton" value="搜索">
		</form>
	</div>
	<div id="logo">
		<a href="homePage"><img src="images/logo.jpg" width="100"
			height="100"></a>
	</div>
	<div id="playimages" class="play">
		<ul class="big_pic">
		<div class="prev"></div>
	    <div class="next"></div>
	    <a class="mark_left" href="javascript:;"></a>
	    <a class="mark_right" href="javascript:;"></a>
	    <c:forEach items="${thumbnailList }" var="thumbnail">
		    <li><img src="files/${thumbnail.product_thumbnail }" width="450px" height="450px"/></li>
	    </c:forEach>
	    </ul>
	    <div id="small_pic" class="small_pic">
	    	<ul style="width:400px;">
	    		<c:forEach items="${thumbnailList }" var="thumbnail">
		        	<li><img src="files/${thumbnail.product_thumbnail }" /></li>
	    		</c:forEach>
	        </ul>       
	    </div>
	</div>
	<div class="details">
		<p>${productInfoList[0].product_name }</p>
		<p>${productInfoList[0].product_introduction }</p>
		<p><label>价格：${specificationObj.price }</label></p>
		<p>
		<c:if test="${not empty specificationObj.product_color }">
			<label>颜色:</label>
			<c:forEach items="${colorList }" var="color">
					<c:choose>
						<c:when test="${specificationObj. product_color==color }">
							<a href="#" class="color" style="border:1px solid red">${color}</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty notInSpecificationList }">
									<a href="javascript:void(0)" class="color">${color}</a>
								</c:when>	
								<c:otherwise>
									<c:choose>
										<c:when test="${fn:contains(notInSpecificationList,color)}">
												<a href="javascript:void(0)" id="color" style="background-color:#666">${color}</a>
										</c:when>
										<c:otherwise>
												<a href="javascript:void(0)" class="color">${color}</a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
			</c:forEach>
		</c:if>
		</p>
		<p>
		<label>规格：</label>
		<c:forEach items="${specificationList }" var="specification">
					<c:choose>
						<c:when test="${specificationObj. product_specification==specification }">
							<a href="#" class="specification" style="border:1px solid red">${specification}</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty notInColorList }">
									<a href="javascript:void(0)" class="specification">${specification}</a>
								</c:when>	
								<c:otherwise>
									<c:choose>
										<c:when test="${fn:contains(notInColorList,specification)}">
												<a href="javascript:void(0)" id="specification" style="background-color:#666">${specification}</a>
										</c:when>
										<c:otherwise>
												<a href="javascript:void(0)" class="specification">${specification}</a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
		</c:forEach>
		</p>
		<div class="countDiv">
			<label>数量：</label><input type="button" value="-" id="reduce"><input type="text" id="count" value="1"><input type="button" id="add" value="+">
		</div>	
		<div class="operation">
			<a href="javascript:void(0)" id="shoppingCart">加入购物车</a><a href="#" id="buy" specificationId="${specificationId }">确定购买</a>
		</div>
	</div>
	
	<div class="tabDiv">
		<ul>
			<li class="skuDescribe">商品介绍</li>
			<li class="skuSpecification">规格</li>
			<li class="skuComment">商品评论</li>
		</ul>
	</div>
	<div class="skuDescribeDiv">
		<c:forEach items="${productImage }" var="image">
			<img src="files/${image.thumbnail }">
		</c:forEach>
	</div>
	<div class="skuSpecificationDiv">
		<div class="describe">
		<c:forEach items="${describeList}" var="describe">
			<p>${describe}</p>
		</c:forEach>
		</div>
	</div>
	<div class="skuCommentDiv">
	<div class="commentGradeDiv">
		<a href="javascript:void(0)" class="commentGrade">好评</a>
		<a href="javascript:void(0)" class="commentGrade">中评</a>
		<a href="javascript:void(0)" class="commentGrade">差评</a>
	</div>
	<div class="commentContent">
		<c:forEach items="${commentList}" var="comment">
			<label>${comment.username}:</label>
			<p class="commentContent">${comment.comment_content}</p>
			<div class="commentImageDiv">
				<c:forEach items="${commentImageList}" var="commentImage">
					<c:if test="${commentImage.comment_id==comment.comment_id}">
						<img src="files/${commentImage.comment_thumbnail}" width="100px" height="100px" class="commentimage">
					</c:if>
				</c:forEach>
			</div>
			<p class="time">${comment.createTime}</p>
		</c:forEach>
	</div>	
			<div class="pagesDiv">
				<c:forEach var="page" begin="1" end="${totalPage}">
					<a href="javascript:void(0)" class="page">${page}</a>
				</c:forEach>
			</div>
			<div class="gradeDiv">
				<label><input type="radio" name="grade" value="好评" class="grade">好评</label>
				<label><input type="radio" name="grade" value="中评" class="grade">中评</label>
				<label><input type="radio" name="grade" value="差评" class="grade">差评</label>
			</div>
			<script id="comment" type="text/plain" class="ueditor" style="width:80%;height:150px;">

			</script>
			<input type="button" value="发表" id="sendButton">
	</div>
</body>
</html>