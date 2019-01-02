$(function(){
		$("#scanlogin").bind("click",function(){
				$("#scanlogin").css("color","red");
				$("#accountlogin").css("color","black");
				$(".scanlogindiv").css("display","block");
				$(".accountlogindiv").css("display","none");
			});
			$("#accountlogin").bind("click",function(){
				$("#accountlogin").css("color","red");
				$("#scanlogin").css("color","black");
				$(".accountlogindiv").css("display","block");
				$(".scanlogindiv").css("display","none");
			});
})
			
		