<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addImagePage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	*{
		margin:0;
		padding:0;
	}
	body{
		background-color:#666;
	}
		#form lable{
			font-size:20px;
		}
		#productId{
			width:350px;
			height:30px;
			border:1px solid red;
			padding-left:10px;
			font-size:18px;
		}
		#fileup{
			width:350px;
			height:30px;
			border:1px solid green;
			margin-top:30px;
			line-height:30px;
		}
		.uploadBox{
			position:absolute;
			top:100px;
			width:100%;
			height:600px;
			background-color:#e33;
			text-align:center;
		}
		#form{
			text-align:left;
			margin-top:30px;
		}
		#sub{
			width:250px;
			height:30px;
			font-size:24px;
			background-color:white;
			color:black;
			border:none;
			line-height:30px;
			margin-top:80px;
			margin-left:160px;
		}
		.uploadDiv{
			text-align:center;
			border:1px solid white;
			background-color:orange;
			height:600px;
			width:580px;
			overflow:auto;
			margin-left:390px;
			
		}
		#select{
			width:100px;
			height:30px;
		}
		.imageDiv{
			width:300px;
			height:300px;
			border:1px solid white;
			margin-left:135px;
			margin-top:50px;
		}
	</style>
  </head>
  
  <body>
    <div class='uploadBox'>
    	<div class='uploadDiv'>
		    <form id="form">
		    	<lable>商品ID：</lable><input type="text" name="productId" id='productId' placeholder="input productID or productName">
			    	<div id='fileBox'>
			    		<input type="file" name="file" multiple="multiple" id='fileup' onchange="showImage()"/>
			    		<select id='select'>
			    			<option value='展示图片'>展示图片</option>
			    			<option value='介绍图片'>介绍图片</option>
			    		</select>
		    	</div>
		    	<div class='imageDiv'>
		    		<img src="" width='300' height='300' id='thumbnail'>
		    	</div>
		    	<input type="button" value="上传" id='sub' onclick='vrify()'>
		    </form>
    	</div>
    </div>
  </body>
  	<script src="js/jquery-3.3.1.min.js"></script>
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
		function vrify(){
			var inputValue=$("input[type='file']").val();
			var productId=$("#productId").val();
			if(inputValue==""||productId==""){
				alert("id为空或者文件未选择");
			}
			else{
			var productId=$("#productId").val();
			var imageUrl=$("#thumbnail").attr("src");
			var imageCategory=$("#select").val();
			    $.ajax({
			    	type:'post',
			    	url:'addImageOperation.do',
			    	data:{
			    			"productId":productId,
			    			"imageUrl":imageUrl,
			    			"imageCategory":imageCategory
			    		},
			    	success:function(data){
			    		alert(data);
			    		window.location.reload();
			    	},
			    	error:function(){
			    		alert("Wrong!!!");
			    	}
			    	
			    })   
        }
		}
	</script>
</html>
