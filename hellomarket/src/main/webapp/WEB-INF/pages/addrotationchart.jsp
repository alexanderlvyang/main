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
		$("#confirm").click(function(){
			var chart_title=$("#title").val();
			var chart_link=$("#link").val();
			var chart_thumbnail=$("#showimage").attr("filename");
			var remarks=$("#remarks").val();
			if(chart_title==""||chart_link==""||chart_thumbnail==""||remarks==""){
				alert("不能为空");
			}else{
				$.ajax({
					type:"post",
					url:"addRotationChart.do",
					data:{
						"chart_title":chart_title,
						"chart_link":chart_link,
						"chart_thumbnail":chart_thumbnail,
						"remarks":remarks
					},
					success:function(data){
						alert(data);
						window.location="rotationChartManage.do";
					},
					error:function(){
						alert("请求出错");
					}
				});	
			}
		});
		$("#uploadButton").click(function(){
			var formData=new FormData();
			var file=$("#thumbnail").get(0).files[0];
			formData.append("file",file);
			formData.append("identification","lunbo");
			var fileName=$("#thumbnail").val();
			var suffix=fileName.substr(fileName.lastIndexOf(".")+1,fileName.length);
			if(suffix!="jpg" && suffix!="png"){
				alert("图片格式不正确，请重新选择");
				$("#thumbnail").val("");
			}else{
				$.ajax({
					type:"post",
					url:"fileUpload.do",
					data:formData,
					processData:false,
					contentType:false,
					success:function(data){
						if(data!="上传失败"&&data!="文件过大"){
							alert("上传成功");
							$("#showimage").attr("src","files/"+data);
							$("#showimage").attr("filename",data);
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
	#image{
		height:100px;
	}
	#showimage{
		width:100px;
		height:80px;
	}
	table{
		border-collapse: separate;
		border-spacing:0px 15px;
		margin-left:400px;
	}
	table input{
		width:200px;
		height:20px;
	}
	.tableDiv{
		width:100%;
		margin-top:100px;
	}
	#uploadButton{
		width:80px;
		height:20px;
	}
	#confirm{
		display:block;
		width:100px;
		height:20px;
		background:#e22;
		color:white;
		text-align:center;
		line-height:20px;
		position:absolute;
		top:450px;
		left:350px;
	}
	#cancel{
		display:block;
		width:100px;
		height:20px;
		background:#e22;
		color:white;
		text-align:center;
		line-height:20px;
		position:absolute;
		top:450px;
		left:600px;
	}
</style>
</head>
<body>
	<div class="tableDiv">
		<table>
			<tr>
				<td>标题</td>
				<td><input type="text" id="title"/></td>
			</tr>
			<tr>
				<td>链接</td>
				<td><input type="url" id="link" placeholder="http://"/></td>
			</tr>	
			<tr>
				<td>备注</td>
				<td><input type="text" id="remarks"/></td>
			</tr>
			<tr id="image">
				<td>图片</td>
				<td>
					<input type="file" id="thumbnail"/>
					<input type="button" id="uploadButton" value="上传"><br>
					<img src="" id="showimage" alt="请选择图片" filename="">
				</td>
			</tr>	
		</table>
		<a href="javascript:void(0)" id="confirm">确定</a><a href="rotationChartManage.do" id="cancel">取消</a>
	</div>
</body>
</html>