<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@page import="javabean.productCategory"%>
<!DOCTYPE html>
<html>
<head>
<title>京东(JD.COM)</title>
<link rel="stylesheet" type="text/css" href="css/homepage.css">
<script type="text/javascript" src='js/jquery-3.3.1.min.js'></script>
<script type="text/javascript" src="js/homepage.js"></script>
<script>
function showmenu(id){
			$.ajax({
				type:'post',
				url:'showCategory.do',
				data:{
					"categoryid":id
				},
				success:function(data){
					var obj=eval(data);
					var tagContent="";
					for(i in obj){
						var newid=obj[i].category_id;
						$.ajax({
						type:'post',
						url:'showCategory.do',
						async: false,
						data:{
							"categoryid":newid
						},
						success:function(data){
							var childObj=eval(data);
							var childTagContent="";
							for(j in childObj){
								childTagContent+="<a href='searchResult.do?categoryid="+childObj[j].category_id+"' id='"+childObj[j].category_id+"' onclick='searchcategoryresult("+childObj[j].category_id+")'>"+childObj[j].category_name+"</a>";
							}
								tagContent+="<table><tr><td><a href='searchResult.do?categoryid="+obj[i].category_id+"' id='"+obj[i].category_id+"' onclick='searchcategoryresult("+obj[i].category_id+")'>"+obj[i].category_name+"</a></td><td id='"+obj[i].category_id+"'>"+
								childTagContent+"</td></tr></table>";
							}
							});
												
					}
					$("#"+id+"div").html(tagContent);
				},
				error:function(){
					alert("wrong");
				}
			})
			$("#"+id+"div").css("display","block");
		}
	
		 function searchcategoryresult(id){
			$.ajax({
				type:"post",
				url:"searchResult.do",
				data:{
					"categoryid":id
				},
				success:function(data){
				
				},
				error:function(){
					alert("wrong");
					}
			})
			
		} 
		function hidemenu(id){
			$("#"+id+"div").css("display","none");
		} 
/* 		function showmenudiv(id){
	
			$("#"+id).css("display","block");
			
		}
		function hidemenudiv(id){
			$("#"+id).css("display","none");
		} */
		/* function chooseposition(id){
			var position=$("#"+id).html();
			$("#position").html(position);
		} */
</script>
	<%-- $(function(){
	
		<%String customername = (String) request.getAttribute("customername");
			if (customername != "") {
				if (request.getSession().getAttribute("customerID") == null) {%>
			$("#customername").html("<%=customername%>"+"请登录");
			$("#register").html("免费注册");
			$("#register").attr("href","register.do");
		<%}
		 else if (session.getAttribute("customerID") != "")
		 {%>
		$("#customername").html("<%=customername%>");
		$("#register").html("退出");
		$("#register").attr("href","javascript:void(0)");
		$("#register").click(function(){
			$.ajax({
				type:"post",
				url:"customerlogout.do",
				success:function(data){
					alert(data);
					$("#customername").html("<%=customername%>"+"请登录");
					$("#register").html("免费注册");
					$("#register").attr("href","register.do");
				},
				error:function(){
					alert("退出失败");
				}
				
			})
			
		})
		
		
		<%}%>
		<%} else {%>
			$("#customername").html("您好,请登录");
			<%}%>
			}) --%>
		
	
</head>
<body>
	<div class="header">
		<ul>
			<li>
				<div class="chooseaddress" onmousemove="showaddress()"
					onmouseleave="hidenaddress()">
					<img src="image/address.png" width="20px" height="20px"> <span
						id="position">北京</span>
				</div>
			</li>
			<li class="login"><a href="customerLogin.do"><span
					id="customername">您好，请登录</span></a></li>
			<li class="register"><a href="register.do" id="register">免费注册</a></li>
			<li class="line">|</li>
			<li class="order"><a href="myOrder.do">我的订单</a></li>
			<li class="line">|</li>
			<li class="myjd">
				<div class="myjddiv" onmousemove="showmyjd()"
					onmouseleave="hidenmyjd()">
					<a href="#">我的京东</a> <img src="image/bottomarrow.png" width="18px"
						height="18px">
				</div>
			</li>
			<li class="line">|</li>
			<li class="jdvip"><a href="#">京东会员</a></li>
			<li class="line">|</li>
			<li class="qycg">
				<div class="qycgdiv" onmousemove="showqycg()"
					onmouseleave="hidenqycg()">
					<a href="#">企业采购</a> <img src="image/bottomarrow.png" width="18px"
						height="18px">
				</div>
			</li>
		</ul>
	</div>
	<div class="address" onmousemove="showaddress()"
		onmouseleave="hidenaddress()">
		<a href="#" id="bj" onclick="chooseposition(id)">北京</a> <a href="#"
			id="sh" onclick="chooseposition(id)">上海</a> <a href="#" id="sz"
			onclick="chooseposition(id)">深圳</a> <a href="#" id="gz"
			onclick="chooseposition(id)">广州</a> <a href="#" id="xm"
			onclick="chooseposition(id)">厦门</a> <a href="#" id="hz"
			onclick="chooseposition(id)">杭州</a> <a href="#" id="cq"
			onclick="chooseposition(id)">重庆</a> <a href="#" id="cd"
			onclick="chooseposition(id)">成都</a> <a href="#" id="jl"
			onclick="chooseposition(id)">吉林</a>
	</div>
	<div class="jd" onmousemove="showmyjd()" onmouseleave="hidenmyjd()">
		<a href="#" id="dcldd">待处理订单</a> <a href="#" id="message">消息</a> <a
			href="#" id="backgoods">返修退换货</a> <a href="#" id="myqueation">我的问答</a>
		<a href="#" id="depreciate">降价商品</a> <a href="#" id="myfollow">我的关注</a>
		<a href="#" id="myjdbean">我的京豆</a> <a href="#" id="mycoupon">我的优惠券</a>
		<a href="#" id="mybt">我的白条</a> <a href="#" id="mylc">我的理财</a>
	</div>
	<div class="enterprisebuy">
		<a href="#" id="qyg">企业购</a> <a href="#" id="sycjg">商用场景馆</a> <a
			href="#" id="gyp">工业品</a> <a href="#" id="lpk">礼品卡</a>
	</div>
	<div class="logo">
		<a href="javascript:void(0)" class="logo_image_a"></a>
	</div>
	<div class="searchdiv">
		<form action="searchResult.do" method="post" id="form">
			<input type="text" name="product_name" id="product_name"> <a
				href="#"><img src="image/photo.png" id="search_photo" width="19"
				height="15"></a> <a href="javascript:void(0)" id="searchbtn"
				><img src="image/searchbtn.png"
				width="20" height="21"></a>
		</form>
	</div>
	<a href="car.do" class='buycar'>购物车</a>
	<div class="searchchoose">
		<a href="#" id="changing">家具满减券</a> <a href="#">卫浴满减</a> <a href="#">颜值潮电</a>
		<a href="#">投影仪</a> <a href="#">空有福利</a> <a href="#">下午茶</a> <a
			href="#">奶爸盛典</a> <a href="#">199减100</a> <a href="#">轻薄本</a>
	</div>
	<div class="navitems-group">
		<ul>
			<li><a href="">秒杀</a></li>
			<li><a href="">优惠券</a></li>
			<li><a href="">PLUS会员</a></li>
			<li><a href="">闪购</a></li>
			<li class="line">|</li>
			<li><a href="">拍卖</a></li>
			<li><a href="">京东服饰</a></li>
			<li><a href="">京东超市</a></li>
			<li><a href="">生鲜</a></li>
			<li class="line">|</li>
			<li><a href="">全球购</a></li>
			<li><a href="">京东金融</a></li>
		</ul>
	</div>
	<div class="category">
		<%
			Map<Integer, String> categorynamemap = (Map<Integer, String>) request.getSession().getAttribute("categorynamemap");
			Iterator<Integer> iterator = categorynamemap.keySet().iterator();
			int key = 0;
			int count = 0;
			String categoryname = "";
			ArrayList<Integer> keylist=new ArrayList<Integer>();
			while (iterator.hasNext()) {
				count++;
				key = (int) iterator.next();
				keylist.add(key);
				categoryname = categorynamemap.get(key);
		%>



		<p id="<%=key%>" onmouseover="showmenu(id)" onmouseout="hidemenu(id)" class='category_p'>
			<a href="searchResult.do?categoryid=<%=key%>"><%=categoryname%></a>
		</p>
		<%
			}
		%>

	</div>
	<%
		for (int i = 0; i < count; i++) {
	%>
	<div id="<%=keylist.get(i) %>div" onmouseover="showmenudiv(id)" onmouseout="hidemenudiv(id)" class="detaildiv"></div>


	<%
		}
	%>

	<div class="lunbobox">
		<div class="lunbo">
			<a href="#"><img src="image/5b63ab58N5de6212d.jpg"></a> <a
				href="#"><img src="image/5b63cb35N5f79fefa.jpg"></a> <a
				href="#"><img src="image/5b63f965N90efa019.jpg"></a> <a
				href="#"><img src="image/5b6260feN913bb330.jpg"></a> <a
				href="#"><img src="image/5b5ea8e3N50b598a2.jpg"></a>
		</div>
		<ul>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	<div class="advertisement">
		<img src="image/5b6530caN467ce6c4.jpg"> <img
			src="image/5b63f42bNeeb386da.jpg"> <img
			src="image/5b518334Ne685c136.jpg">
	</div>
	<div class="right">
		<div>
			<img src="image/no_login.jpg" id= "headimg">
			<p>Hi~欢迎来到京东！</p>
			<p>
				<a href="customerLogin.do" id="log">登陆</a><a href="register.do" id="regist">注册</a>
			</p>
			<a href="#" id="fuli" onmouseover="changenewpeoplestyle(id)">新人福利</a><a
				href="#" id="plusvip" onmouseover="changeplusstyle(id)">PLUS会员</a>

		</div>
		<div class="notice">
			<ul>
				<li><a href="#" id="cuxiao" onmouseover="showcxdiv()">促销</a></li>
				<li>|</li>
				<li><a href="#" id="gonggao" onmouseover="showggdiv()">公告</a></li>
				<li><a href="#" class="more">更多</a></li>
			</ul>

		</div>
		<div class="cuxiaodiv">
			<ul>
				<li><a href="#">百万阶梯券企业专享</a></li>
				<li><a href="#">1元享流量不限量</a></li>
				<li><a href="#">抢299减100沃尔玛神券</a></li>
				<li><a href="#">赛格威平衡轮W1 即踩即走</a></li>
			</ul>
		</div>
		<div class="gonggaodiv">
			<ul>
				<li><a href="#">邯郸大件运营中心开仓公告</a></li>
				<li><a href="#">京东PLUS会员权益更新及会</a></li>
				<li><a href="#">京东启用全新客服电话“9506</a></li>
				<li><a href="#">关于召回普利司通（天津）</a></li>
			</ul>
		</div>
		<div class="img-group">
			<div>
				<img src="image/company.png"><br> <span>企业购</span>
			</div>
			<div>
				<img src="image/game.png"><br> <span>游戏</span>
			</div>
			<div>
				<img src="image/gift.png"><br> <span>礼品卡</span>
			</div>
			<div>
				<img src="image/huoche.png"><br> <span>火车票</span>
			</div>
			<div>
				<img src="image/movie.png"><br> <span>电影票</span>
			</div>
			<div>
				<img src="image/oil.png"><br> <span>加油卡</span>
			</div>
			<div>
				<img src="image/money.png"><br> <span>理财</span>
			</div>
			<div>
				<img src="image/white-paper.png"><br> <span>白条</span>
			</div>
			<div>
				<img src="image/zhongchou.png"><br> <span>众筹</span>
			</div>
			<div>
				<img src="image/plane.png"><br> <span>机票</span>
			</div>
			<div>
				<img src="image/jiudian.png"><br> <span>酒店</span>
			</div>
			<div>
				<img src="image/phone-money.png"><br> <span>话费</span>
			</div>
		</div>
	</div>
	<div class="right-line"></div>
	<!-- <div class="miaosha">
		<div class="miaosha-first">
			<h2 id="h2">京东秒杀</h2>
			<h3>FLASH DEALS</h3>
			<img src="image/shandian.png" width="30" height="33"><br>
			<p>本场距离结束还剩</p>
			<div class="hourdiv">
				<span>1</span>
			</div>
			<div class="minutediv">
				<span>2</span>
			</div>
			<div class="secondsdiv">
				<span>3</span>
			</div>
		</div>
		<div class="miaosha-second">
			<span id="left-sign">&lt;</span>
			<div>
				<img src="image/5b62b695Nb4022bd3.jpg!q90.webp"> <img
					src="image/5ac354caN842a7415.jpg!q90.webp"> <img
					src="image/5b5c2554Na35d7fcd.jpg!q90.webp"> <img
					src="image/5b5ea73fN32e421a0.jpg!q90.webp">
			</div>

			<span id="right-sign">&gt;</span>
		</div> -->
	</div>
</body>
</html>
