<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editer.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		table{
			border-collapse:separate; 
			border-spacing:0px 20px
		}
		#addbtn{
		width:80px;
		height:30px;
		}
		table input[type='text']{
			width:250px;
			height:25px;
		}
		textarea{
			width: 300px;
			height: 100px;
		}
		.updateproduct{
			position:absolute;
			top:20px;
			left:28%;
			text-align:center;
		}
	</style>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src='js/editer.js'></script>
	<script>
			var id=0;
			var imgurl="";
			var url=window.location.href;
			var firstsign=url.indexOf("=")+1;
			var length=url.length;
		$(function(){
			var productId=url.substring(firstsign,length);
			$.ajax({
				type:"post",
				url:"findById.do",
				data:{
					"product_id":productId,
				},
				success:function(data){
					var obj=eval(data);
					if(obj[0].product_state==="下架"){
						$("#down").prop("checked","checked");
					}
					else if(obj[0].product_state==="上架"){
						$("#up").prop("checked","checked");
					}
					$("#product_name").val(obj[0].product_name);
					$("#product_describe").val(obj[0].product_describe);
					$("#product_originalprice").val(obj[0].product_originalprice);
					$("#product_price").val(obj[0].product_price);
					$("#product_introduction").val(obj[0].product_introduction);
					$("#product_sort").val(obj[0].product_sort);
					$("#thumbnail").attr("src",obj[0].product_thumbnail);
					$("#product_number").val(obj[0].product_number);
				},
				error:function(){
					alert("连接错误");
				}
			});
			$("#sub").bind("click",function(){
			var input=$("#fileup").val();
			if(input==""){
				alert("请选择要上传的文件");
			}
			else{
			  var form = new FormData(document.getElementById("form"));
            $.ajax({
                url:"servlet/UploadFile",
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                	$("#thumbnail").attr("src",data);
                	imgurl=data;
                	alert("选择完成");
                },
                error:function(e){
                    alert("错误！！");
                }
            });        
        }
		});
						
		$.ajax({
			type:'post',
			url:'findSecondSelect.do',
			data:{
				"firstCategory":$("#firstselect").val()
			},
			success:function(data){
					var obj=eval(data);
					var tagContent="";
					for(i in obj){
						tagContent+="<option value='"+obj[i].category_name+"'>"+obj[i].category_name+"</option>";
					}
					$("#secondselect").html(tagContent);
					$.ajax({
							type:'post',
							url:'findThirdSelect.do',
							data:{
								"secondCategory":$("#secondselect").val()
							},
							success:function(data){
									var obj=eval(data);
									var tagContent="";
									for(i in obj){
										tagContent+="<option value='"+obj[i].category_name+"'>"+obj[i].category_name+"</option>";
									}
									$("#thirdselect").html(tagContent);
							},
							error:function(){
							
							}
							
						});
			},
			error:function(){
			
			}
		});
		$("#firstselect").bind("click",function(){
			$.ajax({
				type:'post',
				url:'findChildCategory.do',
				data:{
					"categoryName":$("#firstselect").val()
				},
				success:function(data){
					var obj=eval(data);
					var tagContent="";
					for(i in obj){
						tagContent+="<option value='"+obj[i].category_name+"'>"+obj[i].category_name+"</option>"
					}
					$("#secondselect").html(tagContent); 
						$.ajax({
								type:'post',
								url:'findChildCategory.do',
								data:{
									"categoryName":$("#secondselect").val()
								},
								success:function(data){
									var obj=eval(data);
									var tagContent="";
									for(i in obj){
										tagContent+="<option value='"+obj[i].category_name+"'>"+obj[i].category_name+"</option>"
									}
									$("#thirdselect").html(tagContent);
									},
									error:function(){
										
										}
									});
				},
				error:function(){
				
				}
			})
		});
			$("#secondselect").bind("click",function(){
				$.ajax({
				type:'post',
				url:'findChildCategory.do',
				data:{
					"categoryName":$("#secondselect").val()
				},
				success:function(data){
					var obj=eval(data);
					var tagContent="";
					for(i in obj){
						tagContent+="<option value='"+obj[i].category_name+"'>"+obj[i].category_name+"</option>"
					}
					$("#thirdselect").html(tagContent); 
				},
				error:function(){
				
				}
		})
		});
		$("#addbtn").bind("click",function(){
				var product_state= $("input[name='state']:checked").val();
				var product_name=$("#product_name").val();
				var product_realprice=$("#product_price").val();
				var product_originalprice=$("#product_originalprice").val();
				var product_describe=$("#product_describe").val();
				var product_introduction=$("#product_introduction").val();
				var product_sort=$("#product_sort").val();
				var product_number=$("#product_number").val();
				var brand_name=$(".brand").val();
				var firstselect=$("#firstselect").val();
				var secondselect=$("#secondselect").val();
				var thirdselect=$("#thirdselect").val();
				var productId=$("#product_id").val();
				$.ajax({
					type:"post",
					url:"update.do",
					data:{
						"product_state":product_state,
						"product_name":product_name,
						"product_realprice":product_realprice,
						"product_originalprice":product_originalprice,
						"product_describe":product_describe,
						"product_introduction":product_introduction,
						"product_sort":product_sort,
						"product_number":product_number,
						"product_thumbnail":imgurl,
						"brand_name":brand_name,
						"firstselect":firstselect,
						"secondselect":secondselect,
						"thirdselect":thirdselect,
						"productId":productId
					},
					success:function(data){
						alert(data);
						$("input[name='state']:checked").val("");
						$("#product_name").val("");
						$("#product_price").val("");
						$("#product_originalprice").val("");
						$("#product_describe").val("");
						$("#product_introduction").val("");
						$("#product_sort").val("");
						$("#product_thumbnail").val("");
						$("#product_category").val("");
						$("#product_number").val("");
					},
					error:function(){
						alert("连接错误");
					}
				
				})
				
		});
		})
	</script>
  </head>
  
  <body>
  	<div class="updateproduct">
    	<table>
    		<tr>
    			<td>商品编号：</td>
    			<td><input type="text" id='product_id' disabled="disabled" value="<%=request.getAttribute("productId")%>"/></td>
    		</tr>
			<tr>
				<td>商品名：</td>
				<td><input type="text" id='product_name'/></td>
			</tr>
			<tr>
				<td>缩略图：</td>
				<td>
					<form id="form" >
    					<div id='div'>
    						<input type="file" name="file" multiple="multiple" id='fileup' accept=".png,.jpg"/><br/>
    					</div>
    					<input type="button" value="确定" id='sub' onclick='vrify()'>
    				</form>
				</td>
				<td><img src='' width='40' height='40' id='thumbnail'/></td>
			</tr>
			<tr>
				<td>简介：</td>
				<td><input type="text" id='product_introduction'/></td>
			</tr>
			<tr>
				<td>原价格：</td>
				<td><input type="text" id='product_originalprice'/></td>
			</tr>
			<tr>
				<td>实际价格：</td>
				<td><input type="text" id='product_price'/></td>
			</tr>
			<tr>
				<td>描述：</td>
				<td><textarea id='product_describe'></textarea></td>
			</tr>
			<tr>
				<td>类别：</td>
				<td><select onchange='shownext()' id='firstselect'>
				<%
					ArrayList<String> categorynameList=(ArrayList<String>)request.getAttribute("categoryName");
					int categorynameListSize=(int)request.getAttribute("categorynameListSize");
					for(int i=0;i<categorynameListSize;i++){
				 %>
					<option value='<%=categorynameList.get(i) %>'  ><%=categorynameList.get(i) %></option>
					<%}%>
				</select >
					
				<select id='secondselect'>	
				</select>
				
				<select id='thirdselect'>	
				</select>
				</td>
			</tr>
			<tr>
				<td>品牌：</td>
				<td><select class='brand'>
					<%
					ArrayList<String> brandNameList=(ArrayList<String>)request.getAttribute("brandName");
					int brandNameListSize=(int)request.getAttribute("brandNameListSize");
					for(int i=0;i<brandNameListSize;i++){
				 %>
					<option value='<%=brandNameList.get(i) %>'><%=brandNameList.get(i) %></option>
					<%}%>
				</select></td>
			</tr>
			<tr>
				<td>数量：</td>
				<td><input type="text" id='product_number'/></td>
			</tr>
			<tr>
				<td>排序：</td>
				<td><input type="text" id='product_sort'/></td>
			</tr>
			<tr>
				<td>上/下架</td>
				<td><label><input type="radio" name="state" value="上架" id='up'/>上架</label>
					<label><input type="radio" name="state" value="下架" id='down'/>下架</label>
				</td>
			</tr>
			
		</table>
		<input type='button' value='保存' id='addbtn'/>
	</div>
  </body>
</html>
