$(function(){
		$("#addBrand").click(function(){
			$(".addDiv").fadeIn();
		});
		$("#cancelButton").click(function(){
			$(".addDiv").fadeOut();
		});
		$("#confirmButton").click(function(){
			var brandName=$("#brandName").val();
			var brandEnglishName=$("#brandEnglishName").val();
			var brandCompany=$("#brandCompany").val();
			$.ajax({
				type:"post",
				url:"addBrand.do",
				data:{
					"brand_name":brandName,
					"brand_englishName":brandEnglishName,
					"brand_company":brandCompany
				},
				success:function(data){
					alert(data);
					$(".addDiv").fadeOut();
					window.location.reload();
				},
				error:function(){
					
				}
			});
		});
		$(".delete").click(function(){
			var brandId=$(this).parent().siblings().eq(0).html();
			$.ajax({
				type:"post",
				url:"deleteBrand.do",
				data:{
					"brand_id":brandId
				},
				success:function(data){
					alert(data);
					window.location.reload();
				},
				error:function(){
					alert("请求出错");
				}
			});
		});
	});