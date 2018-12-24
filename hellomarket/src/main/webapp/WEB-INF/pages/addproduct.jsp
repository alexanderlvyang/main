<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="UTF-8" src="ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="UTF-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	$(function(){
		var introduction=UE.getEditor('introduction',{
			   toolbars: [[
		            'fullscreen', 'source', 'undo', 'redo', 'bold', 'italic',
		        'underline','fontborder', 'backcolor', 'fontsize', 'fontfamily',
		        'justifyleft', 'justifyright','justifycenter', 'justifyjustify',
		        'strikethrough','superscript', 'subscript', 'removeformat',
		        'formatmatch','autotypeset', 'blockquote', 'pasteplain', '|',
		        'forecolor', 'backcolor','insertorderedlist', 'insertunorderedlist',
		        'selectall', 'cleardoc', 'link', 'unlink','emotion', 'help'
		        ]]
		});
		var describe=UE.getEditor('describe',{
			toolbars: [[
	            'fullscreen', 'source', 'undo', 'redo', 'bold', 'italic',
	        'underline','fontborder', 'backcolor', 'fontsize', 'fontfamily',
	        'justifyleft', 'justifyright','justifycenter', 'justifyjustify',
	        'strikethrough','superscript', 'subscript', 'removeformat',
	        'formatmatch','autotypeset', 'blockquote', 'pasteplain', '|',
	        'forecolor', 'backcolor','insertorderedlist', 'insertunorderedlist',
	        'selectall', 'cleardoc', 'link', 'unlink','emotion', 'help'
	        ]]
		});
		
		$("#confirm").click(function(){
			var productName=$("#productName").val();
			var productEnglishName=$("#productEnglishName").val();
			var productPrice=$("#productPrice").val();
			var productThumbnail=$("#productThumbnail").val();
			var productDescribe=describe.getContentTxt();
			var productIntroduction=introduction.getContentTxt();
			var productCategory=$("#productCategory").val();
			var productBrand=$("#productBrand").val();
			/* var productColor=$("#productColor").val();
			var productSpecification=$("#productSpecification").val(); */
	/* 		var thumbnailName=productThumbnail.substr(productThumbnail.lastIndexOf("\\")+1,productThumbnail.length);
			var suffix=productThumbnail.substr(productThumbnail.lastIndexOf(".")+1,productThumbnail.length); */
			if(productName==""||productEnglishName==""||productPrice==""||productDescribe==""||
					productIntroduction==""||productCategory==""||productBrand==""){
				alert("不能有空内容");
			}else{
			/* 	if($("#thumbnail").attr("src")=="")
					{
						alert("请选择图片");
					}else{
						if(suffix!="jpg" && suffix!="png"){
							alert("图片格式不正确，请重新选择");
							$("#productThumbnail").val("");
					}else{ */
							$.ajax({
								type:"post",
								url:"submitProduct.do",
								data:{
									"product_name":productName,
									"product_englishName":productEnglishName,
									"product_price":productPrice,
									"product_describe":productDescribe,
									"product_introduction":productIntroduction,
									"productCategory":productCategory,
									"productBrand":productBrand
								},
								success:function(data){
									alert(data);
									window.location="productManage.do";
								},
								error:function(){
									
								}
							});
				}
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
	td:first-child{
		width:100px;
	}
	table{
		margin-left:150px;
	}
	table input{
		width:250px;
		height:20px;
	}
	#productCategory{
		width:150px;
		height:20px;
	}
	#productDescribe{
		width:150px;
		height:20px;
	}
	#confirmButton{
		width:100px;
	}
	#thumbnail{
		width:50px;
		height:50px;
	}
	#confirm{
		display:block;
		width:100px;
		height:20px;
		line-height:20px;
		text-align:center;
		text-decoration:none;
		background:#e22;
		color:white;
		margin-top:10px;
		margin-left:200px;
		margin-bottom:20px;
		float:left;
	}
	#cancel{
		display:block;
		width:100px;
		height:20px;
		text-align:center;
		line-height:20px;
		text-decoration:none;
		background:#e22;
		color:white;
		margin-top:10px;
		margin-right:300px;
		margin-bottom:20px;
		float:right;
	}
	#productSpecification{
		width:250px;
		height:20px;
	}
	#productColor{
		width:250px;
		height:20px;
	}
</style>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>名称：</td>
				<td><input type="text" name="product_name" id="productName"></td>
			</tr>		
			<tr>
				<td>英文名：</td>
				<td><input type="text" name="product_englishName" id="productEnglishName"></td>
			</tr>		
			<!-- <tr>
				<td>价格：</td>
				<td><input type="text" name="product_price" id="productPrice"></td>
			</tr> -->		
			<tr>
				<td>简介：</td>
				<td>
					<script id="introduction" type="text/plain" class="ueditor" style="width:80%;height:150px;">
						
   					</script>
   				</td>
			</tr>		
			<tr>
				<td>详细描述：</td>
				<td>
					<script id="describe" type="text/plain" class="ueditor" style="width:80%;height:150px;">
						请使用分号分割每一条描述
   					</script>
				</td>
			</tr>		
			<tr>
				<td>分类：</td>
				<td>
					<select id="productCategory">
						<c:forEach items="${categoryList}" var="category">
							<option value="${category.name }">${category.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>		
			<tr>
				<td>品牌：</td>
				<td>
					<select id="productBrand">
						<c:forEach items="${brandList}" var="brand">
							<option value="${brand.brand_name }">${brand.brand_name }
						</c:forEach>
					</select>
				</td>
			</tr>
			<!-- <tr>
				<td>颜色：</td>
				<td><input type="text" id="productColor"></td>
			</tr>		
			<tr>
				<td>规格：</td>
				<td><input type="text" id="productSpecification"></td>
			</tr> -->		
		</table>
	</div>
	<div>
		<a href="javascript:void(0)" id="confirm">确定</a>
		<a href="productManage.do" target="showContent" id="cancel">取消</a>
	</div>
</body>
</html>