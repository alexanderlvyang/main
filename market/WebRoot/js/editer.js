

		$("#addbtn").bind("click",function(){
			var product_state= $("input[name='state']:checked").val();
			var product_name=$("#product_name").val();
			var product_realprice=$("#product_price").val();
			var product_originalprice=$("#product_originalprice").val();
			var product_describe=$("#product_describe").val();
			var product_introduction=$("#product_introduction").val();
			var product_sort=$("#product_sort").val();
			var product_thumbnail=$("#thumbnail").attr("src");
			var product_category=$("#product_category").val();
			var product_number=$("#product_number").val();
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
					"product_thumbnail":product_thumbnail,
					"product_category":product_category,
					"product_number":product_number,
					"product_id":id
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
                	alert("选择完成");
                },
                error:function(e){
                    alert("错误！！");
                }
            });        
        }
		});
		