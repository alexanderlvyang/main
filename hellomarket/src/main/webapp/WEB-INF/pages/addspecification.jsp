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
		$("#confirmButton").click(function(){
			var formData=new FormData();
			var file=$("#productThumbnail").get(0).files[0];
			formData.append("file",file);
			formData.append("identification","specification")
			var fileName=$("#productThumbnail").val();
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
						if(data!="上传失败"&&data!="文件过大"){
							$("#thumbnail").attr("src","files/"+data);
							$("#productThumbnail").attr("filename",data);
							alert("上传成功");
						}else{
							alert(data);
						}
					},
					error:function(){
						alert("上传错误");
					}
				});
			}
		});
		$("#confirm").click(function(){
			var product_id=$("#productId").val();
			var product_color=$("#productColor").val();
			var product_specification=$("#productSpecification").val();
			var thumbnail=$("#productThumbnail").attr("filename");
			var price=$("#price").val();
			var thumbnailName=thumbnail.substr(thumbnail.lastIndexOf("\\")+1,thumbnail.length);
			var suffix=thumbnail.substr(thumbnail.lastIndexOf(".")+1,thumbnail.length);
			if(price==""||thumbnail==""||product_specification==""){
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
								url:"addSpecification.do",
								data:{
									"product_id":product_id,
									"product_color":product_color,
									"product_specification":product_specification,
									"product_thumbnail":thumbnail,
									"price":price
								},
								success:function(data){
									alert(data);
									window.location="showSpecification.do?product_id="+product_id;
								},
								error:function(){
									
								}
							});		
							}
					}	
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
		margin-left:250px;
		border-collapse: separate;
		border-spacing:0px 30px;
	}
	table input{
		width:250px;
		height:20px;
	}
	#thumbnail{
		width:60px;
		height:60px;
	}
	#confirmButton{
		width:80px;
	}
	#confirm{
		position:absolute;
		top:380px;
		left:280px;
		display:block;
		width:100px;
		height:25px;
		background-color:#e22;
		color:white;
		line-height:25px;
		text-align:center;
		text-decoration:none; 
	}
	#cancel{
		position:absolute;
		top:380px;
		left:580px;
		display:block;
		width:100px;
		height:25px;
		background-color:#e22;
		color:white;
		line-height:25px;
		text-align:center;
		text-decoration:none; 
	}
	#productId{
		background:rgba(144,144,144,0);
		border:none;
	}
</style>
</head>
<body>
	<div class="tableDiv">
		<table>
			<tr>
				<td>商品编号：</td>
				<td><input type="text" id="productId" value="${sessionScope.productId }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>颜色：</td>
				<td><input type="text" id="productColor"></td>
			</tr>
			<tr>
				<td>规格：</td>
				<td><input type="text" id="productSpecification"></td>
			</tr>
			<tr>
				<td>缩略图：</td>
				<td>
					<input type="file" name="product_thumbnail" id="productThumbnail" accept="image/jpg,image/png" filename="">
					<input type="button" id="confirmButton" value="确定 ">
					<img src="" alt="请选择图片" id="thumbnail">
				</td>
			</tr>
			<tr>
				<td>价格：</td>
				<td><input type="text" id="price"></td>
			</tr>
		</table>
	</div>	
	<div>
		<a href="javascript:void(0)" id="confirm">确定</a>
		<a href="showSpecification.do?product_id=${sessionScope.productId }" id="cancel">取消</a>
	</div>
</body>
</html>