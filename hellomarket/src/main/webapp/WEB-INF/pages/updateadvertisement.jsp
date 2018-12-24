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
		$("#type").change(function(){
			if($(this).val()=="图片"){
				$("#image").show();
				$("#text").hide();
			}else{
				$("#image").hide();
				$("#text").show();
			}
		});
		$("#upload").click(function(){
			var formData=new FormData();
			var file=$("#file").get(0).files[0];
			formData.append("file",file);
			var fileName=$("#file").val();
			var suffix=fileName.substr(fileName.lastIndexOf(".")+1,fileName.length);
			if(suffix!="jpg" && suffix!="png"){
				alert("图片格式不正确，请重新选择");
				$("#file").val("");
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
							$("#thumbnail").attr("filename",data);
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
			var id=$("#advertiseId").val();
			var name=$("#name").val();
			var range=$("#range").val();
			var link=$("#link").val();
			var beginTime=$("#beginTime").val();
			var endTime=$("#endTime").val();
			var type=$("#type").val();
			var thumbnail=$("#thumbnail").attr("filename");
			var content=$("#content").val();
			var myDate = new Date();
			var year=myDate.getFullYear();
			var month=myDate.getMonth()+1;
			var date=myDate.getDate(); 
			var date=myDate.getDate(); 
			var dataTime=year+"-"+month+"-"+date;
			var begin=new Date(beginTime).getTime();
			var end=new Date(endTime).getTime();
			var currentTime=new Date(dataTime).getTime();
			if(name==""||range==""||link==""||beginTime==""||endTime==""||type==""){
				alert("不能有空");
			}else{
				if(type=="文本"){
					if(content==""){
						alert("不能有空");
					}else if(content.length>10){
						alert("内容不得超过十个字");	
					}else if(begin<currentTime){
						alert("开始时间有误");
					}else if(begin>end){
						alert("开始时间不能晚于结束时间");
					}else if(begin==end){
						alert("开始时间不能和结束时间相等");
					}else if(end<currentTime){
						alert("结束时间有误");
					}else{
						$.ajax({
							type:"post",
							url:"updateAdvertisement.do",
							data:{
								"advertise_id":id,
								"advertise_name":name,
								"advertise_range":range,
								"advertise_link":link,
								"advertise_thumbnail":thumbnail,
								"advertise_content":content,
								"beginTime":beginTime,
								"endTime":endTime,
								"advertise_type":type
							},
							success:function(data){
								alert(data);
								window.location="advertisementManage.do?advertise_type=图片";
							},
							error:function(){
								alert("请求出错");
							}
						});
					}
				}
				if(type=="图片"){
					if(thumbnail==""){
						alert("不能有空");
					}else if(begin<currentTime){
						alert("开始时间有误");
					}else if(begin>end){
						alert("开始时间不能晚于结束时间");
					}else if(begin==end){
						alert("开始时间不能和结束时间相等");
					}else if(end<currentTime){
						alert("结束时间有误");
					}else{
						$.ajax({
							type:"post",
							url:"updateAdvertisement.do",
							data:{
								"advertise_id":id,
								"advertise_name":name,
								"advertise_range":range,
								"advertise_link":link,
								"advertise_thumbnail":thumbnail,
								"advertise_content":content,
								"beginTime":beginTime,
								"endTime":endTime,
								"advertise_type":type
							},
							success:function(data){
								alert(data);
								window.location="advertisementManage.do?advertise_type=图片";
							},
							error:function(){
								alert("请求出错");
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
	a{
		text-decoration:none;
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
		margin-top:80px;
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
		left:650px;
	}
	#upload{
		width:100px;
		height:20px;
	}
	#thumbnail{
		width:100px;
		height:80px;
	}
	#advertiseId{
		background:rgba(144,144,144,0);
		border:none;
	}
	#type{
		width:100px;
	}
</style>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>编号</td>
				<td><input type="text" id="advertiseId" value="${advertisement.advertise_id }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>名称</td>
				<td><input type="text" id="name" value="${advertisement.advertise_name }"></td>
			</tr>		
			<tr>
				<td>投放范围：</td>
				<td>
					<select id="range">
						<option value="${advertisement.advertise_range }">${advertisement.advertise_range }</option>
						<c:if test="${advertisement.advertise_range=='所有栏目' }">
							<option value="首页">首页</option>
							<option value="搜索页">搜索页</option>
						</c:if>
						<c:if test="${advertisement.advertise_range=='首页' }">
							<option value="所有栏目">所有栏目</option>
							<option value="搜索页">搜索页</option>
						</c:if>
						<c:if test="${advertisement.advertise_range=='搜索页' }">
							<option value="所有栏目">所有栏目</option>
							<option value="首页">首页</option>
						</c:if>
					</select>
				</td>
			</tr>		
			<tr>
				<td>链接：</td>
				<td><input type="url" id="link" value="${advertisement.advertise_link }"></td>
			</tr>		
			<tr>
				<td>开始时间：</td>
				<td><input type="date" id="beginTime" value="${advertisement.beginTime }"></td>
			</tr>		
			<tr>
				<td>结束时间：</td>
				<td><input type="date" id="endTime" value="${advertisement.endTime }"></td>
			</tr>		
			<tr>
				<td>类型：</td>
				<td>
					<select id="type">
						<option value="${advertisement.advertise_type }">${advertisement.advertise_type }</option>
						<c:if test="${advertisement.advertise_type=='图片' }">
							<option value="文本">文本</option>
						</c:if>
						<c:if test="${advertisement.advertise_type=='文本' }">
							<option value="图片">图片</option>
						</c:if>
					</select>
				</td>
			</tr>	
			<c:if test="${advertisement.advertise_type=='图片' }">
				<tr id="image">
					<td>图片：</td>
					<td>
						<input type="file" id="file"><input type="button" id="upload" value="上传"><br>
						<img src="files/${advertisement.advertise_thumbnail }" id="thumbnail" alt="请选择图片" filename="">
					</td>
				</tr>	
				<tr id="text" style="display:none;">
					<td>文本：</td>
					<td><input type="text" id="content" value="${advertisement.advertise_content }"></td>
				</tr>		
			</c:if>
			<c:if test="${advertisement.advertise_type=='文本' }">
				<tr id="image" style="display:none;">
					<td>图片：</td>
					<td>
						<input type="file" id="file"><input type="button" id="upload" value="上传"><br>
						<img src="files/${advertisement.advertise_thumbnail }" id="thumbnail" alt="请选择图片" filename="">
					</td>
				</tr>
				<tr id="text">
					<td>文本：</td>
					<td><input type="text" id="content" value="${advertisement.advertise_content }"></td>
				</tr>		
			</c:if>
		</table>
	</div>
	<a href="javascript:void(0)" id="confirm">确定</a><a href="advertisementManage.do?advertise_type=图片" id="cancel">取消</a>
</body>
</html>