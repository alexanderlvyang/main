<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateImage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		.updateDiv{
			width:600px;
			height:500px;
			background-color:#666;
			margin-left:320px;
			margin-top:100px;
			text-align:center;
		}
		img{
			margin-top:20px;
			border:1px solid #e22;
		}
		#fileup{
			margin-top:20px;
			width:250px;
			border:1px solid black;
		}
		#submitButton{
			width:200px;
			height:30px;
			font-size:20px;
			background-color:#e22;
			color:white;
			border:none;
			margin-top:20px;
		}
		.imageCategory{
			width:150px;
			height:25px;
			margin-top:20px;
		}
	</style>
  </head>
  
  <body>
    <div class='updateDiv'>
    	<img src="<%=request.getAttribute("imageUrl") %>" imageId="<%=request.getAttribute("imageId") %>" width='300' height='300' id='thumbnail'>
    	<form id="form" >
    		<input type="file" name="file" multiple="multiple" id='fileup' onchange="showImage()"/><br>
    		<select class='imageCategory'>
    			<option value="介绍图片">介绍图片</option>
    			<option value="展示图片">展示图片</option>
    			<option value="颜色图片">颜色图片</option>
    		</select><br>
    		<input type='button' value='submit' id='submitButton' onclick="submitFile()">
    		
    	</form>
    	
    </div>
  </body>
  <script src='js/jquery-3.3.1.min.js'></script>
  <script>
  	function showImage(){
  		var form = new FormData(document.getElementById("form"));
	            $.ajax({
	                url:"servlet/UploadFile",
	                type:"post",
	                data:form,
	                processData:false,
	                contentType:false,
	                success:function(data){
	                	$("#thumbnail").attr("src",data);
	                	url=data;
	                },
	                error:function(e){
	                    alert("错误！！");
	                }
	            }); 
  	}
  	function submitFile(){
  		var input=$("#fileup").val();
  		var imageId=$("#thumbnail").attr("imageId");
  		var imageCategory=$(".imageCategory").val();
  			if(input==""){
				alert("请选择文件");
			}
			else{
			var imageUrl=$("#thumbnail").attr("src");
			    $.ajax({
			    	type:'post',
			    	url:'updateImageOperation.do',
			    	data:{
			    			"imageUrl":imageUrl,
			    			"imageId":imageId,
			    			"imageCategory":imageCategory
			    		},
			    	success:function(data){
			    		alert(data);
			    		window.location.reload();
			    	},
			    	error:function(){
			    		alert("Wrong!!!");
			    	}
			    	
			    }); 
        }
  	}
  </script>
</html>
