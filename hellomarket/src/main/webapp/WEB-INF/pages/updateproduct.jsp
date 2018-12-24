<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			var productIntroduction=introduction.getContentTxt();
			var productDescribe=describe.getContentTxt();
			var productId=$("#productName").attr("pid");
			$.ajax({
				type:"post",
				url:"updateProduct.do",
				data:{
					"product_name":productName,
					"product_introduction":productIntroduction,
					"product_describe":productDescribe,
					"product_id":productId
				},
				success:function(data){
					alert(data);
					if(data=="更新成功"){
						window.location="productManage.do";
					}
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
		border-spacing:0px 10px;
	}
	table input{
		width:250px;
		height:20px;
	}
	#confirm{
		position:absolute;
		top:600px;
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
		top:600px;
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
</style>
</head>
<body>
	<div class="tableDiv">
		<table class="table">
			<tr>
				<td>名称：</td>
				<td><input type="text" id="productName" value="${product.product_name }" pid="${product.product_id }"></td>
			</tr>
			<tr>
				<td>简介</td>
				<td>
					<script id="introduction" type="text/plain" class="ueditor" style="width:80%;height:150px;">
   						${product.product_introduction }
					</script>
				</td>
			</tr>
			<tr>
				<td>详细</td>
				<td>
					<script id="describe" type="text/plain" class="ueditor" style="width:80%;height:150px;">
   						${product.product_describe }
					</script>
				</td>
			</tr>
		</table>
	</div>
	<a href="javascript:void(0)" id="confirm">确定</a>
	<a href="productManage.do" id="cancel">取消</a>
</body>
</html>