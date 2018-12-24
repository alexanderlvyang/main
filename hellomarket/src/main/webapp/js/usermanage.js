	$(function(){
		$(".selectStatus").change(function(){
			var status=$(this).val();
			var id= $(this).parent().prevAll("td:eq(4)").html();
			$.ajax({
				type:"post",
				url:"updateStatus.do",
				data:{
					"id":id,
					"status":status
				},
				success:function(data){
					alert(data);
				},
				error:function(){
					alert("请求出错");
				}
			});
		});
	});