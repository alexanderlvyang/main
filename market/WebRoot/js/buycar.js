var total=0;
		$(function(){
			$("input[type='checkbox']").prop("checked",true);
			$(".price").each(function(){
				var value = parseInt($(this).html());
				total=total+value;
				
			});
			$("#totalprice").html(total);
		})
		
		$("#choose").bind("click",function(){
		
			if($("input[name='productid']").prop("checked")){
				$("input[name='productid']").prop("checked",false);
				$("#choose").prop("checked",false);
				$("#totalprice").html("0");
			}else{
			$("input[name='productid']").prop("checked",true);
			$("#choose").prop("checked",true);
			$("#totalprice").html(total);
			}
		});
		