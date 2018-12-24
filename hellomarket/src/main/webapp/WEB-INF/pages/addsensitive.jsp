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
		var  sensitive=UE.getEditor('sensitive',{
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
		UE.getEditor('sensitive').addListener('focus',function(editor){
			sensitive.setContent("",false);
		});
		$("#confirm").click(function(){
			var sensitive_content=sensitive.getContentTxt();
			$.ajax({
				type:"post",
				url:"addSensitiveWord.do",
				data:{
					"sensitive_content":sensitive_content
				},
				success:function(data){
					alert(data);
					window.location="sensitiveManage.do";
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
	a{
		text-decoration:none;
	}
	#sensitiveDiv{
		margin-top:100px;
		margin-left:300px;
	}
	#confirm{
		display:block;
		width:100px;
		height:20px;
		line-height:20px;
		text-align:center;
		background:#e22;
		color:white;
		position:absolute;
		top:450px;
		left:250px;
	}
	#cancel{
		display:block;
		width:100px;
		height:20px;
		line-height:20px;
		text-align:center;
		background:#e22;
		color:white;
		position:absolute;
		top:450px;
		left:750px;
	}
</style>
</head>
<body>
	<div id='sensitiveDiv'>
		<script id="sensitive" type="text/plain" class="ueditor" style="width:80%;height:150px;">
   			多个敏感词使用分号（；）隔开
		</script>
		<a href="javascript:void(0)" id="confirm">确定</a><a href="sensitiveManage.do" id="cancel">取消</a>
	</div>
</body>
</html>