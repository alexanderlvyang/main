var t;
		var index = 0;
t = setInterval(play, 3000)

function play() {
    index++;
    if (index > 4) {
        index = 0
    }

    $(".lunbobox ul li").eq(index).css({
        "background-color": "red",
        "border": "1px solid red"
    }).siblings().css({
        "background-color": "green",
        "border": ""
    })

    $(".lunbo a ").eq(index).fadeIn(1000).siblings().fadeOut(1000);
};

$(".lunbobox ul li").click(function() {

    $(this).css({
        "background": "#999",
        "border": "1px solid #ffffff"
    }).siblings().css({
        "background": "#cccccc"
    })
    var index = $(this).index(); 
    $(".lunbo a ").eq(index).fadeIn(1000).siblings().fadeOut(1000);
});
	$(function(){
		var minute=$(".minutediv span").html();
		var seconds=$(".secondsdiv span").html();
		var hour=$(".hourdiv span").html();
			setInterval(function(){
				seconds--;
				if(seconds<0){
					minute--;
					$(".minutediv span").html(minute);
					if(minute<0){
						hour--;
						$(".hourdiv span").html(hour);
						minute=59;
						$(".minutediv span").html(minute);
					}
					seconds=59;
					$(".secondsdiv span").html(seconds);
				}else
				$(".secondsdiv span").html(seconds);
			},1000);
		

	})
	$(function(){

		$(".chooseaddress").bind("mouseover",function(){
			$(".address").css("display","block");
			$(".chooseaddress").css("border","1px solid #999");
			$(".chooseaddress").css("border-bottom","none");
			$(".chooseaddress").css("background-color","white");
			$(".address").css("border-top","none");

		});
		$(".chooseaddress").bind("mouseleave",function(){
			$(".address").css("display","none");
			$(".chooseaddress").css("border","none");
			$(".chooseaddress").css("background-color","#e3e4e5");
		});
		$(".address").bind("mouseover",function(){
			$(".address").css("display","block");
			$(".chooseaddress").css("border","1px solid #999");
			$(".chooseaddress").css("border-bottom","none");
			$(".chooseaddress").css("background-color","white");
			$(".address").css("border-top","none");

		});
		$(".address").bind("mouseleave",function(){
			$(".address").css("display","none");
			$(".chooseaddress").css("border","none");
			$(".chooseaddress").css("background-color","#e3e4e5");
		});
	
		$(".myjddiv").bind("mouseover",function(){
			$(".jd").css("display","block");
			$(".myjddiv").css("border","1px solid #999");
			$(".myjddiv").css("border-bottom","none");
			$(".myjddiv").css("background-color","white");
			$(".jd").css("border-top","none");
			$(".myjddiv").css("color","red");

		});
		$(".myjddiv").bind("mouseleave",function(){
			$(".jd").css("display","none");
			$(".myjddiv").css("border","none");
			$(".myjddiv").css("background-color","#e3e4e5");
			$(".myjddiv").css("color","#666");
		});
			$(".jd").bind("mouseover",function(){
			$(".jd").css("display","block");
			$(".myjddiv").css("border","1px solid #999");
			$(".myjddiv").css("border-bottom","none");
			$(".myjddiv").css("background-color","white");
			$(".jd").css("border-top","none");
			$(".myjddiv").css("color","red");

		});
		$(".jd").bind("mouseleave",function(){
			$(".jd").css("display","none");
			$(".myjddiv").css("border","none");
			$(".myjddiv").css("background-color","#e3e4e5");
			$(".myjddiv").css("color","#666");
		});
		$(".qycgdiv").bind("mouseover",function(){
			$(".enterprisebuy").css("display","block");
			$(".qycgdiv").css("border","1px solid #999");
			$(".qycgdiv").css("border-bottom","none");
			$(".qycgdiv").css("background-color","white");
			$(".qycgdiv").css("border-top","none");
			$(".qycgdiv").css("color","red");

		});
		$(".qycgdiv").bind("mouseleave",function(){
			$(".enterprisebuy").css("display","none");
			$(".qycgdiv").css("border","none");
			$(".qycgdiv").css("background-color","#e3e4e5");
			$(".qycgdiv").css("color","#666");
		});
		$(".enterprisebuy").bind("mouseover",function(){
			$(".enterprisebuy").css("display","block");
			$(".qycgdiv").css("border","1px solid #999");
			$(".qycgdiv").css("border-bottom","none");
			$(".qycgdiv").css("background-color","white");
			$(".qycgdiv").css("border-top","none");
			$(".qycgdiv").css("color","red");

		});
		$(".enterprisebuy").bind("mouseleave",function(){
			$(".enterprisebuy").css("display","none");
			$(".qycgdiv").css("border","none");
			$(".qycgdiv").css("background-color","#e3e4e5");
			$(".qycgdiv").css("color","#666");
		});	
		
		$("#cuxiao").bind("mouseover",function(){
			$(".gonggaodiv").hide();
			$(".cuxiaodiv").show();
			$("#cuxiao").css("border-bottom","2px solid red");
			$("#gonggao").css("border-bottom","");
		});
		$("#gonggao").bind("mouseover",function(){
			$(".gonggaodiv").show();
			$(".cuxiaodiv").hide();
			$("#gonggao").css("border-bottom","2px solid red");
			$("#cuxiao").css("border","none");
		});
		$("#searchbtn").bind("click",function(){
			$("#form").submit();
		});
		$(".detaildiv").bind("mouseover",function(){
			$("#"+this.id).css("display","block");
		});
		$(".detaildiv").bind("mouseleave",function(){
			$("#"+this.id).css("display","none");
		});
		$(".address a").bind("click",function(){
			var position=$("#"+this.id).html();
			$("#position").html(position);
		});
	})