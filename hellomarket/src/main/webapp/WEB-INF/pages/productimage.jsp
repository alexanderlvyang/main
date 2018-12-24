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
		$("#addButton").click(function(){
			$("#uploadP").show();
		});
		$("#cancelButton").click(function(){
			$("#uploadP").hide();
		});
		$("#upload").click(function(){
			var formData=new FormData();
			var file=$("#uploadfile").get(0).files[0];
			formData.append("file",file);
			formData.append("identification","sku");
			var fileName=$("#uploadfile").val();
			var suffix=fileName.substr(fileName.lastIndexOf(".")+1,fileName.length);
			if(suffix!="jpg" && suffix!="png"){
				alert("图片格式不正确，请重新选择");
				$("#productThumbnail").val("");
			}else{
				$.ajax({
					type:"post",
					url:"fileUpload.do",
					data:formData,
					processData:false,
					contentType:false,
					success:function(data){
						if(data=="上传失败"&&data=="文件过大"){
							alert(data);
						}else{
							alert("上传成功");
							$("#uploadfile").attr("filename",data);
						}
					},
					error:function(){
						alert("上传错误");
					}
				});
			}
		});
		$("#confirmButton").click(function(){
			var thumbnail=$("#uploadfile").attr("filename");
			var productId=$("#addButton").attr("attribute");
			var thumbnailName=thumbnail.substr(thumbnail.lastIndexOf("\\")+1,thumbnail.length);
			var suffix=thumbnail.substr(thumbnail.lastIndexOf(".")+1,thumbnail.length);
			if(thumbnail==""){
				alert("不能有空");				
			}else{
				if($("#thumbnail").attr("src")==""){
						alert("请选择图片");
				}else{
						if(suffix!="jpg" && suffix!="png"){
								alert("图片格式不正确，请重新选择");
								$("#thumbnail").val("");
						}else{
							$.ajax({
								type:"post",
								url:"addProductImage.do",
								data:{
									"thumbnail":thumbnail,
									"product_id":productId
								},
								success:function(data){
									alert(data);
									window.location.reload();
								},
								error:function(){
									
								}
							});		
							}
					}	
			}
		});
		$(".delete").click(function(){
			var productImage_id=$(this).parent().siblings().eq(0).html();
			$.ajax({
				type:"post",
				url:"deleteProductImage.do",
				data:{
					"productImage_id":productImage_id
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
		padding:0
	}
	body{
		background:url(../images/menu-background.jpg) no-repeat;
		background-size:100% 100%;
		background-attachment:fixed;
	}
	th{
		width:200px;
	}
	table{
		width:100%;
		border-collapse: separate;
		border-spacing:30px 30px;
		text-align:center;
	}
	table input{
		width:250px;
		height:20px;
	}
	.uploadfileDiv input{
		width:80px;
	}
	#uploadP{
		display:none;
	}
	a{
		text-decoration:none;
	}
	#addButton{
		display:block;
		width:150px;
		height:20px;
		line-height:20px;
		background:#e22;
		color:white;
		text-align:center;
		text-decoration:none;
		/* position:absolute;
		top:100px;
		left:950px; */
	}
	/* #upload{
		margin-left:100px;
	} */
	#uploadfile{
		width:250px;
	}
</style>
</head>
<body>
	<div class="uploadfileDiv">
		<p id="uploadP"><input type="file" id="uploadfile" accept="image/jpg,image/png" filename="">
		<input type="button" id="upload" value="上传">
		<input type="button" id="confirmButton" value="确定">
		<input type="button" id="cancelButton" value="取消"></p>
		<a href="javascript:void(0)" id="addButton" attribute="${product_id }">添加</a>
	</div>
	<div>
		<table>
			<thead>
				<th>图片编号</th>
				<th>图片</th>
				<th>操作</th>
			</thead>
			<tbody>
				<c:forEach items="${productImageList }" var="image">
					<tr>
						<td>${image.productImage_id }</td>
						<td><img src="files/${image.thumbnail }" style="width:50px;height:100px;"></td>
						<td><a href="javascript:void(0)" class="delete">删除</a></td>
					</tr>
				</c:forEach>			
			</tbody>
		</table>
	</div>
</body>
</html>