<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'music.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
body {
	background: url('image/findbg.jpg') no-repeat center center;
	background-size: cover;
	background-attachment: fixed;
}

a {
	text-decoration: none;
}

input[type='file'] {
	width: 250px;
	height: 25px;
	margin-top: 20px;
	background-color: transparent;
	font-size: 16px;
}

@font-face {
	font-family: 'yang';
	src: url("font/yang.ttf");
}

p {
	font-family: 'yang';
	font-size: 30px;
}

button {
	font-size: 20px;
	width: 120px;
	height: 30px;
	background: linear-gradient(to right, red, blue);
	border: none;
	color: gold;
}

#sub {
	width: 150px;
	height: 30px;
	font-size: 16px;
	background-color: green;
	border-radius: 40px;
	border: none;
	margin-top: 15px;
}

.divbox {
	position: absolute;
	top: 20px;
	left: 28%;
}

table input[type='text'] {
	width: 250px;
	height: 25px;
}

textarea {
	width: 300px;
	height: 100px;
}

.addproduct {
	position: absolute;
	top: 20px;
	left: 20%;
	text-align: center;
}

#addbtn {
	width: 80px;
	height: 30px;
	margin-top:60px;
}

table {
	border-collapse: separate;
	border-spacing: 0px 20px
}

select {
	width: 80px;
}

.editer {
	width: 400px;
	height: 200px;
	background-color: red;
}

td {
	width: 700px;
}

.first {
	width: 700px;
}
.attrvalueBox{
	text-align:left;
}
.label{
	margin-left:10px;
}
</style>

</head>

<body>
	<%@page import="javabean.CategoryAttributeValue"%>
	<!--  <div class='divbox'>
  <p>文件上传</p>
  <button onclick='addupload()'>添加上传</button>
    <form id="form">
    	<div id='div'>
    	<input type="file" name="file" multiple="multiple" id='fileup' accept=".mp3,.wav,.ogg"/><br/>
    	</div>
    	<input type="button" value="上传" id='sub' onclick='vrify()'>
    </form>
   </div> -->
	<div class='addproduct'>
		<table class="productTable">
			<tr>
				<td class='first'>商品名：</td>
				<td><input type="text" id='product_name' /></td>
			</tr>
			<tr>
				<td class='first'>缩略图：</td>
				<td>
					<form id="form">
						<div id='div'>
							<input type="file" name="file" multiple="multiple" id='fileup'
								accept=".png,.jpg" /><img src='' width='50' height='50'
								id='thumbnail' alt='未选择' />
						</div>
						<input type="button" value="确定" id='sub' onclick='vrify()'>

					</form>

				</td>
				<td></td>
			</tr>
			<tr>
				<td class='first'>简介：</td>
				<td><input type="text" id='product_introduction' /></td>
			</tr>
			<tr>
				<td class='first'>原价格：</td>
				<td><input type="text" id='product_originalprice' /></td>
			</tr>
			<tr>
				<td class='first'>实际价格：</td>
				<td><input type="text" id='product_price' /></td>
			</tr>
			<tr>
				<td class='first'>描述：</td>
				<td><script id="ueditor" type="text/plain"></script></td>
			</tr>
			<tr>
				<td class='first'>品牌：</td>
				<td><select class='brand'>
						<%
							ArrayList<String> brandNameList = (ArrayList<String>) request.getAttribute("brandName");
							int brandNameListSize = (int) request.getAttribute("brandNameListSize");
							for (int i = 0; i < brandNameListSize; i++) {
						%>
						<option value='<%=brandNameList.get(i)%>'><%=brandNameList.get(i)%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td class='first'>数量：</td>
				<td><input type="text" id='product_number' /></td>
			</tr>
			<tr>
				<td class='first'>排序：</td>
				<td><input type="text" id='product_sort' /></td>
			</tr>
			<tr>
				<td class='first'>上/下架</td>
				<td><label><input type="radio" name="state" value="上架" />上架</label>
					<label><input type="radio" name="state" value="下架" />下架</label></td>
			</tr>
			<tr>
				<td class='first'>类别：</td>
				<td><select id='firstselect' onchange='showcategoeyattribute(id)'>
						<%
							ArrayList<String> categorynameList = (ArrayList<String>) request.getAttribute("categoryName");
							int categorynameListSize = (int) request.getAttribute("categorynameListSize");
							for (int i = 0; i < categorynameListSize; i++) {
						%>
						<option value='<%=categorynameList.get(i)%>'><%=categorynameList.get(i)%></option>
						<%
							}
						%>
				</select> <select id='secondselect'>
				</select> <select id='thirdselect'>
				</select></td>
			
		</table>
		<div class='attrvalueBox'>
				<%
					Map<String, ArrayList<CategoryAttributeValue>> categoryAttributeMap = (Map<String, ArrayList<CategoryAttributeValue>>) request.getAttribute("categoryAttributeMap");
					Iterator<String> iterator = categoryAttributeMap.keySet().iterator();
					int count=0;
					while (iterator.hasNext()) {
						count++;
						String key = (String) iterator.next();
						ArrayList<CategoryAttributeValue> categoryAttributeValueList = categoryAttributeMap.get(key);
				%>
				<label><%=key%>:</label>
					<%
						for (Object obj : categoryAttributeValueList) {
					%> <label class='label'><input type='radio' name='attrValue<%=count %>'
						value='<%=obj%>'><%=obj%></label> 
				<%
 					}
 				%>
 				<br>
			<%
				}
			%>
			</div>
		<input type='button' value='添加' id='addbtn' />
	</div>
</body>
<script src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charser="utf-8"
	src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script>
	var ue = UE.getEditor("ueditor");
</script>
<script type="text/javascript">
	function showcategoeyattribute(id){
		var firstCategoryName=$("#"+id).val();
		$.ajax({
			type:'post',
			url:'showCategoryAttribute.do',
			data:{
				"firstCategoryName":firstCategoryName
			},
			 dataType : "json",
			success:function(data){
				var obj=eval(data);
				var tagContent="";
				var count=0;
				for(var key in obj){
					count=count+1;
					var values=new Array();
					var objstr=obj[key].toString();
					values=objstr.split(',');
					var valuehtml="";
					for(var i=0;i<values.length;i++)
					{
						valuehtml+="<label class='label'><input type='radio' name='attrvalule"+count+"' value='"+values[i]+"'>"+values[i]+"</label>"	
					}
					tagContent+="<label>"+key+"</label>"+valuehtml+"<br>";
				}
				$(".attrvalueBox").html(tagContent);
			},
			error:function(){
				alert("Wrong!!!");
			}
		})
	}
	$(function() {
		var url;
		$.ajax({
			type : 'post',
			url : 'findSecondSelect.do',
			data : {
				"firstCategory" : $("#firstselect").val()
			},
			success : function(data) {
				var obj = eval(data);
				var tagContent = "";
				for (i in obj) {
					tagContent += "<option value='" + obj[i].category_name + "'>" + obj[i].category_name + "</option>";
				}
				$("#secondselect").html(tagContent);
				$.ajax({
					type : 'post',
					url : 'findThirdSelect.do',
					data : {
						"secondCategory" : $("#secondselect").val()
					},
					success : function(data) {
						var obj = eval(data);
						var tagContent = "";
						for (i in obj) {
							tagcontent += "<option value='" + obj[i].category_name + "'>" + obj[i].category_name + "</option>";
						}
						$("#thirdselect").html(tagcontent);
					},
					error : function() {}
				});
			},
			error : function() {}
		});

		$("#sub").bind("click", function() {
			var input = $("#fileup").val();
			if (input == "") {
				alert("请选择要上传的文件");
			} else {
				var form = new FormData(document.getElementById("form"));
				$.ajax({
					url : "servlet/UploadFile",
					type : "post",
					data : form,
					processData : false,
					contentType : false,
					success : function(data) {
						$("#thumbnail").attr("src", data);
						url = data;
						alert("选择完成");
					},
					error : function(e) {
						alert("错误！！");
					}
				});
			}
		});

		$("#addbtn").bind("click", function() {
			var product_state = $("input[name='state']:checked").val();
			var product_name = $("#product_name").val();
			var product_realprice = $("#product_price").val();
			var product_originalprice = $("#product_originalprice").val();
			var product_describe = UE.getEditor('ueditor').getContentTxt();
			var attrvalues="";
			  $('input:radio:checked').each(function() {
			  	attrvalues+=$(this).val()+",";
			  	alert(attrvalues);
			  })
			attrvalues=attrvalues.substring(0,attrvalues.length-1);
			alert(attrvalues);
			attrvalues=attrvalues.substr(3);
			alert(attrvalues);
			var product_introduction = $("#product_introduction").val();
			var product_sort = $("#product_sort").val();
			var product_number = $("#product_number").val();
			var brand_name = $(".brand").val();
			var firstselect = $("#firstselect").val();
			var secondselect = $("#secondselect").val();
			var thirdselect = $("#thirdselect").val();
			$.ajax({
				type : "post",
				url : "insert.do",
				data : {
					"product_state" : product_state,
					"product_name" : product_name,
					"product_realprice" : product_realprice,
					"product_originalprice" : product_originalprice,
					"product_describe" : product_describe,
					"product_introduction" : product_introduction,
					"product_sort" : product_sort,
					"product_number" : product_number,
					"product_thumbnail" : url,
					"brand_name" : brand_name,
					"firstselect" : firstselect,
					"secondselect" : secondselect,
					"thirdselect" : thirdselect,
					"attrvalues":attrvalues
				},
				success : function(data) {
					alert(data);
				},
				error : function() {
					alert("连接错误");
				}
			})

		});


		$("#firstselect").bind("click", function() {
			$.ajax({
				type : 'post',
				url : 'findChildCategory.do',
				data : {
					"categoryName" : $("#firstselect").val()
				},
				success : function(data) {
					var obj = eval(data);
					var tagContent = "";
					for (i in obj) {
						tagContent += "<option value='" + obj[i].category_name + "'>" + obj[i].category_name + "</option>"
					}
					$("#secondselect").html(tagContent);
					$.ajax({
						type : 'post',
						url : 'findChildCategory.do',
						data : {
							"categoryName" : $("#secondselect").val()
						},
						success : function(data) {
							var obj = eval(data);
							var tagContent = "";
							for (i in obj) {
								tagContent+= "<option value='" + obj[i].category_name + "'>" + obj[i].category_name + "</option>"
							}
							$("#thirdselect").html(tagContent);
						},
						error : function() {}
					});
				},
				error : function() {}
			})
		});
		$("#secondselect").bind("click", function() {
			$.ajax({
				type : 'post',
				url : 'findChildCategory.do',
				data : {
					"categoryName" : $("#secondselect").val()
				},
				success : function(data) {
					var obj = eval(data);
					var tagContent = "";
					for (i in obj) {
						tagContent += "<option value='" + obj[i].category_name + "'>" + obj[i].category_name + "</option>"
					}
					$("#thirdselect").html(tagcontent);
				},
				error : function() {}
			})
		});
	})
</script>
<!-- <script>
	$(function(){
			
		$.ajax({
			type:'post',
			url:'findSecondSelect.do',
			data:{
				"firstCategory":$("#firstselect").val()
			},
			success:function(data){
					var obj=eval(data);
					var str="";
					for(i in obj){
						str+="<option value='"+obj[i].category_name+"'>"+obj[i].category_name+"</option>";
					}
					$("#secondselect").html(str);
					$.ajax({
							type:'post',
							url:'findThirdSelect.do',
							data:{
								"secondCategory":$("#secondselect").val()
							},
							success:function(data){
									var obj=eval(data);
									var str="";
									for(i in obj){
										str+="<option value='"+obj[i].category_name+"'>"+obj[i].category_name+"</option>";
									}
									$("#thirdselect").html(str);
							},
							error:function(){
							
							}
							
						});
			},
			error:function(){
			
			}
		});
	
	})

	
		function shownext(){
			$.ajax({
				type:'post',
				url:'findChildCategory.do',
				data:{
					"categoryName":$("#firstselect").val()
				},
				success:function(data){
					var obj=eval(data);
					var str="";
					for(i in obj){
						str+="<option value='"+obj[i].category_name+"'>"+obj[i].category_name+"</option>"
					}
					$("#secondselect").html(str); 
						$.ajax({
								type:'post',
								url:'findChildCategory.do',
								data:{
									"categoryName":$("#secondselect").val()
								},
								success:function(data){
									var obj=eval(data);
									var str="";
									for(i in obj){
										str+="<option value='"+obj[i].category_name+"'>"+obj[i].category_name+"</option>"
									}
									$("#thirdselect").html(str);
									},
									error:function(){
										
										}
									});
				},
				error:function(){
				
				}
			})
		}
	</script> -->
</html>
