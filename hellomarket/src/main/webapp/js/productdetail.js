	$(function(){
		$(".skuSpecification").click(function(){
			$(this).css("background-color","#e22");
			$(this).siblings().css("background-color","white");
			$(".skuDescribeDiv").hide();
			$(".skuCommentDiv").hide();
			$(".skuSpecificationDiv").show();
		});
		$(".skuDescribe").click(function(){
			$(this).css("background-color","#e22");
			$(this).siblings().css("background-color","white");
			$(".skuDescribeDiv").show();
			$(".skuCommentDiv").hide();
			$(".skuSpecificationDiv").hide();
		});
		$(".skuComment").click(function(){
			$(this).css("background-color","#e22");
			$(this).siblings().css("background-color","white");
			$(".skuDescribeDiv").hide();
			$(".skuCommentDiv").show();
			$(".skuSpecificationDiv").hide();
		});
		var comment=UE.getEditor('comment',{
			toolbars: [[
				'fullscreen', 'source', '|', 'undo', 'redo', '|',
				'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
				'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
				'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
				'directionalityltr', 'directionalityrtl', 'indent', '|',
				'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
				'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
				'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
				'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
				'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
				'print', 'preview', 'searchreplace', 'drafts', 'help'
			]]
		});
		$("#sendButton").click(function(){
			var commentContent=comment.getContent();
			var commentTxt=comment.getContentTxt();
			var username=$("#username").html();
			var productId=$(".header").attr("productId");
			var grade="";
			$(".grade").each(function(){
				if($(this).prop("checked")){
					grade=$(this).val();
				}
			});
			if(typeof(username)=="undefined"){
				alert("请登陆后评论")
			}else
			if(commentContent==""){
				alert("请输入评论内容");
			}else
			if(grade!=""){
				$.ajax({
					type:"post",
					url:"sendComment",
					data:{
						"commentContent":commentContent,
						"commentTxt":commentTxt,
						"grade":grade,
						"productId":productId,
						"username":username
					},
					success:function(data){
						alert(data);
						window.location="productDetail?productId="+productId;
					},
					error:function(){
						alert("请求出错");
					}
				});
			}else{
					alert("请选择评论级别");
			}



		});
	
		$(document).on("click", ".page", function(){
			var currentPage=$(this).html();
			var productId=$(".header").attr("productId");
			var grade="";
			$(".commentGrade").each(function(){
				if($(this).css("background-color")=='rgb(255, 255, 0)'){
					grade=$(this).html();
				}
			});
			$.ajax({
				type:"post",
				url:"getComment",
				data:{
					"currentPage":currentPage,
					"productId":productId,
					"grade":grade
				},
				success:function(data){
					$.ajax({
						type:"post",
						url:"getCommentImage",
						data:{
							"productId":productId
						},
						success:function(message){
							var json=eval(message);
							var result="";
							var commentJson=eval(data);
							for(i in commentJson){
								result+="<label>"+commentJson[i].username+
								":</label><p class='commentContent'>"+commentJson[i].comment_content+"</p>";
								for(j in json){
									if(json[j].comment_id==commentJson[i].comment_id){
										result+="<img src='files/"+json[j].comment_thumbnail+"' width='100px' height='100px' class='commentimage'>";
									}
								}
								result+="<p class='time'>"+commentJson[i].createTime+"</p>";
							}
							$(".commentContent").html(result);
						},
						error:function(){
							alert("请求出错");
						}
					});
					
				},
				error:function(){
					alert("请求出错");
				}
			});
		});
		$(".commentGrade").click(function(){
			$(this).css("background-color","yellow");
			$(this).siblings().css("background-color","#e22");
			var grade=$(this).html();
			var productId=$(".header").attr("productId");
			$.ajax({
				type:"post",
				url:"getComment",
				data:{
					"grade":grade,
					"productId":productId
				},
				success:function(data){
					$.ajax({
						type:"post",
						url:"getPage",
						data:{
							"productId":productId,
							"grade":grade
						},
						success:function(data){
							var pageResult="";
							for(var i=1;i<=data;i++){
								pageResult+="<a href='javascript:void(0)' class='page'>"+i+"</a>"
							}
							$(".pagesDiv").html(pageResult);
						}
					});
					$.ajax({
						type:"post",
						url:"getCommentImage",
						data:{
							"productId":productId,
						},
						success:function(message){
							var json=eval(message);
							var result="";
							var commentJson=eval(data);
							for(i in commentJson){
								result+="<label>"+commentJson[i].username+
								":</label><p class='commentContent'>"+commentJson[i].comment_content+"</p>";
								for(j in json){
									if(json[j].comment_id==commentJson[i].comment_id){
										result+="<img src='files/"+json[j].comment_thumbnail+"' width='100px' height='100px' class='commentimage'>";
									}
								}
								result+="<p class='time'>"+commentJson[i].createTime+"</p>";
							}
							$(".commentContent").html(result);
						},
						error:function(){
							alert("请求出错");
						}
					});
				}
			});
		});
		$(".color").click(function(){
			$(this).css("border","1px solid red");
			$(this).siblings().css("border","none");
		});
		$("#add").click(function(){
			$("#count").val(parseInt($("#count").val())+1);
			if(parseInt($("#count").val())>200){
				$("#count").val(200);
			}
		});
		$("#reduce").click(function(){
			$("#count").val(parseInt($("#count").val())-1);
			if(parseInt($("#count").val())<=0){
				$("#count").val(1);
			}
		});
		$("#count").change(function(){
			if(parseInt($("#count").val())<=0){
				$("#count").val(1);
			}
			if(parseInt($("#count").val())>200){
				$("#count").val(200);
			}
		});
		$(".color").click(function(){
			var color=$(this).html();
			var productId=$(".header").attr("productId");
			var specification="";
			$(".specification").each(function(){
				if($(this).css("border")=="1px solid rgb(255, 0, 0)"){
					specification=$(this).html();
				}
			});
			color=window.encodeURIComponent(window.encodeURIComponent(color));
			specification=window.encodeURIComponent(window.encodeURIComponent(specification));
			$(this).attr("href","productDetail?color="+color+"&specification="+specification+"&productId="+productId);
		});
		$(".specification").click(function(){
			var specification=$(this).html();
			var productId=$(".header").attr("productId");
			var color="";
			$(".color").each(function(){
				if($(this).css("border")=="1px solid rgb(255, 0, 0)"){
					color=$(this).html();
				}
			});
			color=window.encodeURIComponent(window.encodeURIComponent(color));
			specification=window.encodeURIComponent(window.encodeURIComponent(specification));
			$(this).attr("href","productDetail?color="+color+"&specification="+specification+"&productId="+productId);
		});
		$("#shoppingCart").click(function(){
			var productId=$(".header").attr("productId");
			var color="";
			var specification="";
			var count=$("#count").val();
			$(".color").each(function(){
				if($(this).css("border")=="1px solid rgb(255, 0, 0)"){
					color=$(this).html();
				}
			})
			$(".specification").each(function(){
				if($(this).css("border")=="1px solid rgb(255, 0, 0)"){
					specification=$(this).html();
				}
			})
			$.ajax({
				type:"post",
				url:"addShoppingCart",
				data:{
					"productId":productId,
					"color":color,
					"specification":specification,
					"count":count
				},
				success:function(data){
					alert(data);
				},
				error:function(){
					alert("添加出错");
				}
			});
		});
		$("#buy").click(function(){
			var count=$("#count").val();
			var specificationId=$(this).attr("specificationId")+",";
			var username=$("#username").html();
			if(typeof(username)=="undefined"){
				alert("请登录");
				window.location="loginfront";
			}else{
				specificationId=window.encodeURIComponent(specificationId);
				$("#buy").attr("href","confirmPay?specificationId="+specificationId+"&indentification=detail&count="+count);
			}
		});
	});
