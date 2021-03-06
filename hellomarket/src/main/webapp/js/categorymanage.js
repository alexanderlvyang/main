$(function(){
		$(".parent").click(function(){
			$(this).parent().next().slideToggle();
			$(this).parent().next().children(".addChildCategory").slideToggle();
			if($(this).parent().parent().children("a:last-child").prev().is(":visible")){
				$(this).parent().parent().children("a:last-child").prev().hide();
			}
			if($(this).parent().parent().find(".grandChildDiv").is(":hidden")){
				$(this).parent().parent().find(".grandChildDiv").hide();	
			}
			if($(this).parent().next().children(".grandChildDiv").find(".addChild").is(":hidden")){
				$(this).parent().next().children(".grandChildDiv").find(".addChild").hide();
			}
			if($(this).parent().next().children(".grandChildDiv").find(".addChild").next().is(":hidden")){
				$(this).parent().next().children(".grandChildDiv").find(".addChild").next().hide();
			}
		});
		$(".child").click(function(){
			$(this).parent().next().slideToggle();
			$(this).parent().next().children(".addChildCategory").slideToggle();
			
			if($(this).parent().parent().children("a:last-child").prev().is(":hidden")){
				$(this).parent().parent().children("a:last-child").prev().hide();
			}
		});
		$(".delete").click(function(){
			var categoryName=$(this).prev().html();
			$(".warnDiv").show();
			$("#categoryName").html(categoryName);
		});
		$("#cancel").click(function(){
			$(".warnDiv").hide();
		});
		$("#confirm").click(function(){
			var categoryName=$("#categoryName").html();
			$(this).parent().hide();
			$.ajax({
				type:"post",
				url:"deleteCategory.do",
				data:{
					"name":categoryName
				},
				success:function(data){
					if(data=="删除成功"){
						alert(data);
						window.location.reload();
					}else{
						alert(data);
					}
				},
				error:function(){
					alert("请求出错");
				}
			});
		});
		$(".addChildCategory").click(function(){
			$(this).prev().slideDown();
		});
		$(".addCategory").click(function(){
			$(this).prev().slideDown();
		});
		$(".cancelButton").click(function(){
			$(this).parent().slideUp();
		});
		$(".confirmButton").click(function(){
			var categoryName=$(this).prev().val();
			var parentName=$(this).parent().parent().prev().children().html();
			$(this).prev().val("");
			$.ajax({
				type:"post",
				url:"addCategory.do",
				data:{
					"name":categoryName,
					"parentName":parentName
				},
				success:function(data){
					if(data=="添加成功"){
						alert(data);
						window.location.reload();
					}else{
						alert(data);
						window.location.reload();
					}
				},
				error:function(){
					alert("请求出错");
				}
			});
		});
		$(".confirmParentButton").click(function(){
			var categoryName=$(this).prev().val();
			$(this).prev().val("");
			$.ajax({
				type:"post",
				url:"addCategory.do",
				data:{
					"name":categoryName
				},
				success:function(data){
					if(data=="添加成功"){
						alert(data);
						window.location.reload();
					}else{
						alert(data);
						window.location.reload();
					}
				},
				error:function(){
					alert("请求出错");
				}
			});
		});
	});	
