<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'productDetail.jsp' starting page</title>
    
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
		.imageBox{
			float:left;
			margin-left:50px;
			margin-top:100px;
			width:450px;
			height:550px;
			background-color:white;
		}
		.detailBox{
			float:left;
			width:450px;
			height:500px;
			margin-top:100px;
			color:white;
			margin-left:20px;
		}
		.detailBox p{
			margin-top:10px;
		}
		.originalLabel{
			margin-top:10px;
			
		}
		.otherDetail{
			position:absolute;
			top:950px;
			left:250px;
			width:1000px;
			height:50px;
			line-height:30px;
			background-color:white;
		}
		.otherDetail li{
			list-style:none;
			float:left;
			margin-left:20px;
			width:100px;
			text-align:center;
		}
		.otherDetail ul{
			margin-top:10px;
		}
		.productDetail{
			position:absolute;
			top:1010px;
			left:250px;
			width:1000px;
			
			background-color:white;		
			text-align:center;
			}
		.otherDetail li:hover{
			cursor:pointer;
		}
		#count{
			width:60px;
			text-align:center;
		}
		.addBuycar{
			display:block;
			width:150px;
			height:30px;
			background-color:#F0CAB6;
			color:#E5511D;
			text-decoration:none;
			line-height:30px;
			text-align:center;
			margin-left:20px;
			float:left;
			margin-top:20px;
		}
		#buyNow{
			display:block;
			width:150px;
			height:30px;
			background-color:#e22;
			color:white;
			text-decoration:none;
			line-height:30px;
			text-align:center;
			float:left;
			margin-top:20px;
			border:none;
		}
		.count{
			margin-top:280px;
		}
		
		#myBuycar{
			display:block;
			position:absolute;
			top:10px;
			left:1175px;
			width:150px;
			height:30px;
			background-color:white;
			color:#666;
			line-height:30px;
			text-align:center;
			text-decoration:none;
		
		}
		.search_btn{
			display: block;
			width: 50px;
			height: 35px;
			background-color: red;
		}
		#product_name{
			width: 400px;
			height: 35px;
		}
		.searchdiv{
			position: absolute;
			top: 10px;
			left: 550px;
		}
		#searchbtn{
			display: block;
			width: 50px;
			height: 35px;
			position: absolute;
			top: 1px;
			left: 400px;
			background-color: red;
			text-align: center;
			line-height: 35px;
		}
		#search_photo{
			position: absolute;
			top: 10px;
			left: 550px;
		}
		.catalog{
			margin-top:150px;
			width:100%;
			height:30px;
		}
		.catalog li{
			margin-left:10px;
			float:left;
			list-style:none;
		}
		.catalog li a{
			text-decoration:none;
			color:white;
			font-size:16px;
			
		}
		.category{
			position:absolute;
			top:90px;
			left:0px;
			width:100%;
			height:40px;
			border:1px solid green;
		}
		.category ul{
			margin-left:214px;
			height:40px;
		}
		.category li{
			margin-left:10px;
			float:left;
			list-style:none;
		}
		.category li a{
			display:block;
			width:80px;
			heihgt:40px;
			float:left;
			line-height:40px;
			text-align:center;
			text-decoration:none;
			color:white;
			font-size:16px;
			background-color:#e22;
		}
		#show{
			width:150px;
		}
		.allProductList{
			display:none;
			width:300px;
			height:400px;
			background-color:white;
			color:black;
			position:absolute;
			top:132px;
			left:225px;
		}
		.allProductList a{
			text-decoration:none;
		}
		#reduce{
			width:30px;
		}
		#add{
			width:30px;
		}
		.moreImage{
			width:450px;
			height:80px;
			background-color:white;
			margin-top:10px;
		}
		.moreImage img{
			float:left;
			margin-left:25px;
		}
		#productThumbnail{
			border-bottom:1px solid #666;
		}
		.imageshow:hover{
			border:1px solid red;
		}
		.bigImageBox{
			display:none;
			width:600px;
			height:600px;
			position:absolute;
			top:280px;
			left:500px;
			border:1px solid red;
		}
		.producttable{
			width:200px;
			border-collapse:separate; 
			border-spacing:0px 10px;
		}
		.productName{
			margin-left:10px;
		}
		.showImage{
			margin-top:40px;
		}
		#describe{
			margin-top:40px;
		}
		.describeTable{
			width:1000px;
			table-layout: fixed;
			
		}
		.describeTable td{
			text-overflow: ellipsis; /* for IE */  
		    -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
		    overflow: hidden;  
		    white-space: nowrap;  
		    text-align: left;  
		}
		.comment{
			position:absolute;
			top:1010px;
			left:250px;
			width:1000px;
			background-color:white;		
			text-align:center;
			display:none;
		}
		.sendComment{
			width:800px;
			margin-left:100px;
		}
		#submit{
			width:150px;
			height:30px;
			color:white;
			background-color:#e22;
			border:none;
			margin-left:650px;
		}
		#productDetail{
			border:1px solid red;
		}
		#customerId{
			float:left;
		}
		#createTime{
			float:right;
		}
		.commentDiv{
			width:100%;
			margin-top:30px;
			margin-bottom:30px;
		}
		#commentContent{
			float:left;
			padding-left:20px;
			margin-top:5px;
		}
		.commentLevel{
			margin-top:10px;
		}
		.commentLevelButton{
			text-decoration:none;
			color:white;
			background-color:#e22;
			font-size:20px;
			border:1px solid red;
			margin-right:10px;
		}
		.simpleComment{
			margin-top:20px;
		}
		a{
			text-decoration:none;
		}
		.totalPages{
			text-align:right;
		}
		.pages{
			margin-right:10px;
		}
		.specificationBox{
			position:absolute;
			top:1010px;
			left:250px;
			width:850px;
			background-color:white;		
			text-align:center;
			display:none;
			text-align:left;
			padding-left:150px;
		}
		#showdescribe{
			float:right;
		}
		#ueditor{
			margin-bottom:30px;
		}
		.attributeItemBox{
			margin-top:30px;
		}
		.attributeItemBox a{
			margin-left:20px;
			border: 1px solid #8080c0;
			background-color:white;
		}
	</style>
	<script src='js/jquery-3.3.1.min.js'></script>
  	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>  
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>  
	<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
	<script>  
		var ue = UE.getEditor("ueditor"); 
	</script>
	<script>
			$(function(){
				var attributeValue="";
				$(this).siblings(".attributeItem").css("border","none");
				$(".attributeItem").each(function(){
					if($(this).css("border")=="1px solid rgb(238, 34, 34)"){
						attributeValue+=($(this).html())+",";
					}
				});
				var productId=$("#productName").attr("productID");
				attributeValue=attributeValue.substr(0, attributeValue.length-1);
				$.ajax({
						type:'post',
						url:'judgmentAttributeItem.do',
						data:{
							"productId":productId,
							"attributeValue":attributeValue
						},
						success:function(message){
							var attributeObj=JSON.parse(message);
							for(i in attributeObj){
							if(attributeObj[i].length>1){
								var attributeArray=new Array();
								attributeArray=attributeObj[i].toString().split(",");
								for(var j=0;j<attributeArray.length;j++){
									$(".attributeItem").each(function(){
										if(attributeArray[j]==$(this).html()){
											$(this).css("background-color","#666");
											$(this).attr("class","");
										}
									});
								}
							}else{
								$(".attributeItem").each(function(){
										if(attributeObj[i]==$(this).html()){
											$(this).css("background-color","#666");
											$(this).attr("class","");
										}
									});
							}
						}
						},
						error:function(){
							alert("Wrong!!!");
						}
					});
	
	})
	</script>
  </head>
  
  <body>
  <%@page import="javabean.Product" %>
	    <%
	    	Product product=(Product)request.getAttribute("product");
	     %>
	 
	 
	<div class='searchDiv'>
		<form action="searchResult.do" method="post" id="form">
			<input type="text" name="product_name" id="product_name">
			<a href="javascript:void(0)" id="searchbtn" onclick="searchproduct()"><img src="image/searchbtn.png" width="20" height="21"></a>
		</form>
	</div> 
	<div>
		<a href="car.do" id='myBuycar'>我的购物车</a>
	</div>
	
	
	<div class='category'>
		
		<ul>
			<li><a href="javascript:void(0)" id='show'>查看所有商品</a></li>
		<%
			ArrayList<String> categoryNameList=(ArrayList<String>)request.getAttribute("categoryNameList");
			int categoryNameListSize=(int)request.getAttribute("categoryNameListSize");
			for(int i=0;i<categoryNameListSize;i++){
		 %>
			<li><a href="javascript:void(0)"><%=categoryNameList.get(i) %></a></li>
		<%} %>
		</ul>
	</div>
	<div class='allProductList'>
	
	</div>
	
	<div class='catalog'> 
		<ul>
		<%
			ArrayList<String> catalogNameList=(ArrayList<String>)request.getAttribute("catalogNameList");
			int catalogNameListSize=(int)request.getAttribute("catalogNameListSize");
			for(int i=0;i<catalogNameListSize;i++){
		 %>
			<li><a href="javascript:void(0)"><%=catalogNameList.get(i) %></a>></li>
			
		<%} %>
		<li><a href="javascript:void(0)"><%=product.getProduct_name() %></a></li>
		</ul>
	</div>
	
	
	
	
	<div class='imageBox'>
		<img src="<%=product.getProduct_thumbnail()%>" width="450" height="450" id='productThumbnail'>
		<div class='moreImage'>
			<%			
				ArrayList<String> showImageUrlList=(ArrayList<String>)request.getAttribute("showImageUrlList");
			 	int showImageUrlListSize=(int)request.getAttribute("showImageUrlListSize");
			 	for(int i=0;i<showImageUrlListSize;i++){
			 %>
			 <img src="<%=showImageUrlList.get(i) %>" width="60" height="80" class='imageshow'>
		 	<%} %>
		 </div>
	</div>
	<div class='bigImageBox'>
		<img src="" width='600' height='600' id='bigImage'>
	</div>
	<div class='detailBox'>
		<p id='productName' productID='<%=product.getProduct_id() %>'><%=product.getProduct_name() %></p>
		<p><%=product.getProduct_introduction() %></p>
		<p>价格：<%=product.getProduct_price() %></p>
		
		<%
			Map<String,ArrayList<String>> skuattributeMap=(Map<String,ArrayList<String>>)request.getAttribute("skuattributeMap");
			Iterator<String> attributeiterator=skuattributeMap.keySet().iterator();
			ArrayList<String> productSpecificationList=(ArrayList<String>)request.getAttribute("productSpecificationList");
		 	int productSpecificationListSize=(int)request.getAttribute("productSpecificationListSize");
		 	for(int j=0;j<productSpecificationListSize;j++){
			while(attributeiterator.hasNext()){
			String mapkey=(String)attributeiterator.next();
		 %>
		 	<div class='attributeItemBox'>
		 	<label class='attributeLabel'><%=mapkey %></label>
		 	<%	
		 		ArrayList<String> attributeValuleList=skuattributeMap.get(mapkey);
		 		Iterator<String> Listiterator=attributeValuleList.iterator();
		 		while(Listiterator.hasNext()){
		 		String keyValue=Listiterator.next();
		 				if(keyValue.equals(productSpecificationList.get(j))){
		 	 %>
		 	 <a href='javascript:void(0)' class='attributeItem' style='border: 1px solid #e22;'><%=keyValue %></a>
		 	 <% }
		 	 if(!(keyValue.equals(productSpecificationList.get(j)))){ %>
		 	   <a href='javascript:void(0)' class='attributeItem' ><%=keyValue %></a>
		 	   <%} }%>
		 	  </div>
		 <% break;}}%>
		<form action='orderSettlement.do?productId=<%=product.getProduct_id() %>' method='post'>
		<div class='count'><lable class='originalLabel'>购买数量：</lable><input type='button' id='reduce' value='-'>
		<input type='text' value='1' id='count' name='count'>
		<input type='button' id='add' value='+'></div>
		<input type='submit' id='buyNow' value='立即购买'>
		</form>
		<a href='javascript:void(0)' class='addBuycar' id='<%=product.getProduct_id() %>' onclick="addToBuycar(id)" class='addBuyBtn'>加入购物车</a>
	</div>
	
	
	
	
	
	
	<div class='otherDetail'>
		<ul>
			<li onclick='showDetail()' id='productDetail'>商品介绍</li>
			<li onclick='showSpecification()' id='productSpecification'>规格与包装</li>
			<li onclick='showComment()' id='productComment'>商品评价</li>
		</ul>
	</div>
	<div class='productDetail'>
		<%-- <p id='describe'><%=product.getProduct_describe() %></p> --%>
		<div>
			<table class='describeTable'>
				<%
					List<String> describeList=(List<String>)request.getAttribute("describeList");
					int describeListSize=(int)request.getAttribute("describeListSize");
					for(int i=0;i<3;i++){
				 %>		
				 <tr>
					<%
						int size=i*3+3;
						if(size>describeListSize){
							size=describeListSize;
						}
						for(int j=i*3;j<size;j++){
					%>
				 	<td title='<%=describeList.get(j) %>'><%=describeList.get(j) %></td>
				 	<%}%>
				 </tr>
				 <%} %>
			</table>
			<a href='javascript:void(0)' onclick="showSpecification()" id='showdescribe'>查看详细</a>
		</div>
		<div class='showImage'>
			<%
				ArrayList<String> introductionImageUrlList=(ArrayList<String>)request.getAttribute("introductionImageUrlList");
				int introductionImageUrlListSize=(int)request.getAttribute("introductionImageUrlListSize");
				for(int i=0;i<introductionImageUrlListSize;i++){
			 %>
			<div><img src="<%=introductionImageUrlList.get(i)%>"></div>
			<%} %>
		</div>
	</div>
	<div class='specificationBox'>
				<%
					for(int i=0;i<(int)describeListSize;i++){
				 %>		
				 	<p><%=describeList.get(i) %></p>
				 <%} %>
	</div>
	<div class='comment'>
		<div class='commentLevel'>
			<%
				Map<String,Integer> commentLevelMap=(Map<String,Integer>)request.getAttribute("commentLevelMap");
				Iterator<String> iterator=commentLevelMap.keySet().iterator();
				while(iterator.hasNext()){
					String key=(String)iterator.next();
			 %>
			 	<a href='javascript:void(0)' class='commentLevelButton' id='<%=key%>' onclick='findComment(id)'><%=key%>&nbsp;<%=commentLevelMap.get(key) %></a>
			 <%} %>
		</div>
		<div class='commentDiv'>
		<%@page import="javabean.Comment" %>
				<%-- <%
					ArrayList<Comment> commentList=(ArrayList<Comment>)request.getAttribute("commentList");
					ArrayList<ArrayList<String>> commentImageList=(ArrayList<ArrayList<String>>)request.getAttribute("commentImageList");
					int commentListSize=(int)request.getAttribute("commentListSize");
					for(int i=0;i<commentListSize;i++){
				 %>
				 <div class='simpleComment'>
				 <label id="customerId"><%=commentList.get(i).getCustomerName() %></label>
				 <span id="createTime"><%=commentList.get(i).getCreateTime() %></span><br>
				 <p id='commentContent'><%=commentList.get(i).getCommentValue() %><%=commentImageList.get(i).get(i) %></p><br>
				 </div>
				 <%} %> --%>
		</div>
		<div class='totalPages'>
			<%
				int totalPage=(int)request.getAttribute("totalPage");
				for(int i=1;i<=totalPage;i++){		
			 %>
			 	<a href='javascript:void(0)' id='<%=i %>' onclick='changePage(id)'  class='pages'><%=i %></a>
			 
			  <%} %>
		</div>
		<div class='sendComment'>
				<label><input type="radio" value='好评' name='level'>好评</label>
				<label><input type="radio" value='中评' name='level'>中评</label>
				<label><input type="radio" value='差评' name='level'>差评</label>
				<script id="ueditor" charset="utf-8" type="text/plain" name='content'></script>
				<input type='button' value='提交' id='submit' onclick='sendSubmit()'>
		</div>
	</div>
  </body>
  	<script src='js/jquery-3.3.1.min.js'></script>
	<script>
		$(function(){
			$(".attributeItem").bind("click",function(){
				var attributeValue="";
				$(this).css("border","1px solid #e22");
				$(this).siblings(".attributeItem").css("border","none");
				$(".attributeItem").each(function(){
					if($(this).css("border")=="1px solid rgb(238, 34, 34)"){
						attributeValue+=($(this).html())+",";
					}
				});
				var productId=$("#productName").attr("productID");
				attributeValue=attributeValue.substr(0, attributeValue.length-1);
				var attributeItem=$(this).html();
				/* $.ajax({
					type:'post',
					url:'judgmentAttributeItem.do',
					data:{
						"productId":productId,
						"attributeItem":attributeItem
					},
					success:function(message){
						var attributeObj=eval(message);
						for(i in attributeObj){
						alert(attributeObj[i]);
							$(".attributeItem").each(function(){
								if($(this).html()==attributeObj[i]){
									$(this).css("background-color","#666");
									$(this).attr("class","");
								}	
							});
						} */
						$.ajax({
							type:'post',
							url:'productAttributeChoose.do',
							data:{
								"attributeValue":attributeValue,
								"product_id":productId
							},
							success:function(data){
								var productId=data;
								window.location.href="productDetail.do?product_id="+productId;
							},
							error:function(){
							
							}
						});
					/* },
					error:function(){
						alert("Wrong!!!");
					}
				}); */
			});
			
		})
		function changePage(id){
			var currentPage=id;
			var productId=$("#productName").attr("productID");
			$.ajax({
				type:'post',
				url:'findComment.do',
				data:{
					"currentPage":currentPage,
					"productId":productId
				},
				success:function(data){
					var obj=eval(data);
					var tagContent="";
					for(i in obj){
						commentId=obj[i].commentId;
						tagContent+="<div class='simpleComment'><label id='customerId'>"+obj[i].customerName+"</label>"+
					 			"<span id='createTime'>"+obj[i].createTime+"</span><br>"+
					 			"<p id='commentContent'>"+obj[i].commentValue+"</p><br></div>";
			 			$.ajax({
			 				cache: false,
          					async: false,
			 				type:'post',
			 				url:'findCommentImage.do',
			 				data:{
			 					"commentId":commentId
			 				},
			 				success:function(data){
				 				var imgTag="";
			 					var imgobj=eval(data);
								for(j in imgobj){
									imgTag+="<img src='"+imgobj[j]+"' width='50' height='50'>";
								}
								tagContent=tagContent+imgTag;
								
			 				},
			 				error:function(){
			 				
			 				}
			 			});
			 			$(".commentDiv").html(tagContent);	
			 			
					}
				},
				error:function(){
				
				}
			})
		} 
		function findComment(id){
		var commentLevel=id;
		var productId=$("#productName").attr("productID");
			$.ajax({
				type:'post',
				url:'findCommentByLevel.do',
				data:{
					"commentLevel":commentLevel,
					"productId":productId
				},
				success:function(data){
					var obj=eval(data);
					if(data.length>2){
					var tagContent="";
					for(i in obj){
						commentId=obj[i].commentId;
						tagContent+="<div class='simpleComment'><label id='customerId'>"+obj[i].customerName+"</label>"+
					 			"<span id='createTime'>"+obj[i].createTime+"</span><br>"+
					 			"<p id='commentContent'>"+obj[i].commentValue+"</p><br></div>";
			 			
			 			$.ajax({
			 				cache: false,
          					async: false,
			 				type:'post',
			 				url:'findCommentImage.do',
			 				data:{
			 					"commentId":commentId
			 				},
			 				success:function(data){
				 				var imgTag="";
			 					var imgobj=eval(data);
								for(j in imgobj){
									imgTag+="<img src='"+imgobj[j]+"' width='50' height='50'>";
								}
								tagContent=tagContent+imgTag;
								
			 				},
			 				error:function(){
			 				
			 				}
			 			});
			 			$(".commentDiv").html(tagContent);	
			 			
					}
					$.ajax({
							type:'post',
							url:'getTotalByCommentLevel.do',
							data:{
								"commentLevel":commentLevel,
								"productId":productId
							},
							success:function(data){
								var tagContent="";
								for(i=1;i<=data;i++){
									tagContent+="<a href='javascript:void(0)' class='pages' id='"+i+"a' level="+commentLevel+" onclick='findCommentByCommentLevel(id)'>"+i+"</a>";
								}
								$(".totalPages").html(tagContent);
							},
							error:function(){
							
							}
					});
				}else{
					$(".commentDiv").html("");	
				}},
				error:function(){
					alert("Wrong!!!");
				}
				
			});
		}
		function findCommentByCommentLevel(id){
			var commentLevel=id;
			var currentPage=$("#"+id).html();
			var commentLevel=$("#"+id).attr("level");
			var productId=$("#productName").attr("productID");
			$.ajax({
				type:'post',
				url:'findCommentByLevel.do',
				data:{
					"commentLevel":commentLevel,
					"productId":productId,
					"currentPage":currentPage
				},
				success:function(data){
					var obj=eval(data);
					var tagContent="";
					for(i in obj){
						commentId=obj[i].commentId;
						tagContent+="<div class='simpleComment'><label id='customerId'>"+obj[i].customerName+"</label>"+
					 			"<span id='createTime'>"+obj[i].createTime+"</span><br>"+
					 			"<p id='commentContent'>"+obj[i].commentValue+"</p><br></div>";
			 			$.ajax({
			 				cache: false,
          					async: false,
			 				type:'post',
			 				url:'findCommentImage.do',
			 				data:{
			 					"commentId":commentId
			 				},
			 				success:function(data){
				 				var imgTag="";
			 					var imgobj=eval(data);
								for(j in imgobj){
									imgTag+="<img src='"+imgobj[j]+"' width='50' height='50'>";
								}
								tagContent=tagContent+imgTag;
								
			 				},
			 				error:function(){
			 				
			 				}
			 			});
			 			$(".commentDiv").html(tagContent);	
			 			
					}
				},
				error:function(){
				
				}
			});
		}
		function sendSubmit(){
			var content=UE.getEditor('ueditor').getContent();
			var contenttext=UE.getEditor('ueditor').getContentTxt();
			var productId=$("#productName").attr("productID");
			var commentLevel=$('input:radio:checked').val();;
			alert(contenttext);
			if(contenttext==""){
				alert("评价不能为空");
			}
			else if(commentLevel==null){
				alert("请选择级别");
			}else{
			$.ajax({
				type:'post',
				url:'judgmentIsComment.do',
				data:{
					"productId":productId
				},
				success:function(message){
					if(message=='存在'){
							$.ajax({
								type:'post',
								url:'sendComment.do',
								data:{
									"content":content,
									"productId":productId,
									"commentLevel":commentLevel,
									"contenttext":contenttext
								},
								success:function(data){
									if(data.indexOf("[")==-1)
									{
										alert(data);
									}
									var obj=eval(data);
									var tagContent="";
									
									tagContent="<div class='simpleComment'><label id='customerId'>"+obj[0]+"</label>"+
							 			"<span id='createTime'>"+obj[1]+"</span><br>"+
							 			"<p id='commentContent'>"+obj[2]+"</p><br></div>";
									$(".commentDiv").append(tagContent);					
								},
								error:function(){
									alert("Wrong!!!");
								}
							});
					}else if(message=='不存在'){
						alert("您还没够购买该商品");
					}else{
						alert(message);
					}
				},
				error:function(){
					alert("Wrong!!!");
				}
			});
			}
		}
		function showDetail(){
			$(".productDetail").show();
			$(".comment").hide();
			$(".specificationBox").hide();
			$("#productDetail").css("border","1px solid red");
			$("#productComment").css("border","none");
			$("#productSpecification").css("border","none");
		}
		function showSpecification(){
			$(".productDetail").hide();
			$(".comment").hide();
			$(".specificationBox").show();
			$("#productDetail").css("border","none");
			$("#productComment").css("border","none");
			$("#productSpecification").css("border","1px solid red");
		}
		function showComment(){
			$(".productDetail").hide();
			$(".specificationBox").hide();
			$(".comment").show();
			$("#productComment").css("border","1px solid red");
			$("#productDetail").css("border","none");
			$("#productSpecification").css("border","none");
			var productId=$("#productName").attr("productID");
			$.ajax({
				cache: false,
          		async: false,
				type:'post',
				url:'findComment.do',
				data:{
					"productId":productId
				},
				success:function(data){
					var obj=eval(data);
					var commentId=0;
					var tagContent="";
					
					for(i in obj){
						
						commentId=obj[i].commentId;
						tagContent+="<div class='simpleComment'><label id='customerId'>"+obj[i].customerName+"</label>"+
					 			"<span id='createTime'>"+obj[i].createTime+"</span><br>"+
					 			"<p id='commentContent'>"+obj[i].commentValue+"</p><br></div>";
			 			$.ajax({
			 				cache: false,
          					async: false,
			 				type:'post',
			 				url:'findCommentImage.do',
			 				data:{
			 					"commentId":commentId
			 				},
			 				success:function(data){
				 				var imgTag="";
			 					var imgobj=eval(data);
								for(j in imgobj){
									imgTag+="<img src='"+imgobj[j]+"' width='50' height='50'>";
								}
								tagContent=tagContent+imgTag;
								
			 				},
			 				error:function(){
			 				
			 				}
			 			});
			 			$(".commentDiv").html(tagContent);	
			 			
					}
					$.ajax({
							type:'post',
							url:'getTotalByCommentLevel.do',
							data:{
								"commentLevel":commentLevel,
								"productId":productId
							},
							success:function(data){
								var tagContent="";
								for(i=1;i<=data;i++){
									tagContent+="<a href='javascript:void(0)' class='pages' id='"+i+"a' level="+commentLevel+" onclick='findCommentByCommentLevel(id)'>"+i+"</a>";
								}
								$(".totalPages").html(tagContent);
							},
							error:function(){
							
							}
					});
				},
				error:function(){
				
				}
			});
		}
		function searchproduct(){
			$("#form").submit();
		}
		$(function(){
		var productId=$("#productName").attr("productID");
			$.ajax({
				type:'post',
				url:'showAllProduct.do',
				data:{
					"productId":productId
				},
				success:function(data){
					var obj=eval(data);
					var tagContent="";
					for(i in obj){
						var newid=obj[i].category_id;
						$.ajax({
						type:'post',
						url:'showProduct.do',
						async: false,
						data:{
							"categoryid":newid
						},
						success:function(data){
							var childObj=eval(data);
							var childTagContent="";
							for(j in childObj){
								childTagContent+="<a href='productDetail.do?product_id="+childObj[j].product_id+"' id='"+childObj[j].product_id+"' class='productName'>"+childObj[j].product_name+"</a>";
							}
								tagContent+="<table class='producttable'><tr><td><a href='searchResult.do?categoryid="+obj[i].category_id+"' id='"+obj[i].category_id+"' onclick='searchcategoryresult("+obj[i].category_id+")'>"+obj[i].category_name+"</a></td><td id='"+obj[i].category_id+"'>"+
								childTagContent+"</td></tr></table>";
							}
								});
												
					}
					$(".allProductList").html(tagContent);
				},
				error:function(){
					alert("Wrong!!!");
				}
			})
		});
		$(function(){
			$("#productThumbnail").bind("mouseover",function(){
				var thumbnailsrc=$("#productThumbnail").attr("src");
				$("#bigImage").attr("src",thumbnailsrc);
				$(".bigImageBox").show();
			
			});
			$("#productThumbnail").bind("mouseleave",function(){
				$(".bigImageBox").hide();
			
			});
			$(".imageshow").bind("mouseover",function(){
				var thumbnailsrc=$(this).attr("src");
				$("#productThumbnail").attr("src",thumbnailsrc);
			})
			$("#show").bind("mouseover",function(){
				$(".allProductList").show();		
			});
			$("#show").bind("mouseleave",function(){
				$(".allProductList").hide();		
			});
			$(".allProductList").bind("mouseover",function(){
				$(".allProductList").show();		
			});
			$(".allProductList").bind("mouseleave",function(){
				$(".allProductList").hide();		
			});
			$("#reduce").attr("disabled",true);
			$("#add").bind("click",function(){
				var count=$("#count").val();
				if(count<=200){
					$("#reduce").attr("disabled",false);
				}
				
				if(count>=200){
					$("#add").attr("disabled",true);
				}else{
					count++;
					$("#count").val(count);
				}
			});
			$("#count").bind("change",function(){
				var count=$("#count").val();
				if(count>200){
					$("#count").val("200");
				}
				if(count<1){
					$("#count").val("1");
				}
			});
			$("#reduce").bind("click",function(){
				var count=$("#count").val();
				if(count>=1){
					$("#add").attr("disabled",false);
				}
				if(count<=1){
					$("#reduce").attr("disabled",true);
				}else{
					count--;
					$("#count").val(count);
				}
			
			});
			$("#addBuycar").bind("click",function(){
			var productId=$("#productName").attr("productId");
				$.ajax({
					type:'post',
					url:'addBuycar.do',
					data:{
						"product_id":productId
					},
					success:function(data){
						alert(data);
					},
					error:function(){
						alert("Wrong!!!");
					}
					
				});
			
			});
			
			
			
			
		})
		function addToBuycar(id){
			$.ajax({
				type:"post",
				url:"addBuycar.do",
				data:{
					"product_id":id,
					"product_number":$("#count").val()
				},
				success:function(data){
					alert(data);
				},
				error:function(){
					alert("wrong");
				}
			})
		}
		
	</script>
</html>
