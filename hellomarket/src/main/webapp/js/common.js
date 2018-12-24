/*
 **功能:图表
 **作者:loho
 **日期:2017-04-07
 */
$(function() {
/*	var columnChartKey=["5000", "2000", "36000", "2000", "1000", "200"];
	alert(columnChartKey);*/
	var columnChartKey="";
	var columnChartKeyArr;
	var columnChartValue="";
	var columnChartValueArr;
	var income="";
	var incomeArr;
	var VIPCount="";
	var NormalCount="";
	var BlockadeCount=""
	$.ajax({
		type:'post',
		url:'getColumnChartData',
		async:false,
		success:function(data){
			var Obj=eval(data);
			for(var key in Obj){
				columnChartKey+=key+",";
				columnChartValue+=Obj[key]+",";
			}
			columnChartValue=columnChartValue.substring(0,columnChartKey.length-1);
			columnChartKey=columnChartKey.substring(0,columnChartKey.length-1);
			columnChartKeyArr=columnChartKey.split(",");
			columnChartValueArr=columnChartValue.split(",");
		}
	});
	$.ajax({
		type:'post',
		url:'getIncome',
		async:false,
		success:function(data){
			var Obj=eval(data);
			for(i in Obj){
				income+=Obj[i]+",";
			}
			incomeArr=income.split(",");
		}
	});
	$.ajax({
		type:'post',
		url:'getUsersStatusCount',
		async:false,
		success:function(data){
			var Obj=eval(data);
			VIPCount=Obj[0];
			NormalCount=Obj[1];
			BlockadeCount=Obj[2];
		}
	});
	
	setOption("echarts-1", {
		//标题
		title: {
			text: 'ECharts基础'
		},
		//工具栏
		tooltip: {},
		//图表图注
		legend: {
			data: ['数量']
		},
		//x轴
		xAxis: {
			data:columnChartKeyArr
		/*	data: ["用户数", "评论数", "分类数", "品牌数", "订单数", "商品数"]*/
			/*data: ["电脑", "手机", "服务器", "平板电脑", "电视机", "MP3"]*/
		},
		//y轴
		yAxis: {},
		//系列
		series: [{
			name: '数量',
			//图表类型设置
			type: 'bar',
			/*data:columnChartKey*/
			data:columnChartValueArr
			/*data: [5000, 2000, 36000, 2000, 1000, 200]*/
		}]
	});
	setOption("echarts-2", {
		backgroundColor: 'green',
		visualMap: {
			show: true,
			min: 80,
			max: 600,
			inRange: {
				colorLightness: [2, 5.5]
			}
		},
		series: [{
			name: '访问来源',
			type: 'pie',
			radius: '55%',
			data: [{
				value: VIPCount,
				name: 'VIP'
			}, {
				value: NormalCount,
				name: '普通用户 '
			}, {
				value: BlockadeCount,
				name: '封号用户'
			}/*, {
				value: 335,
				name: '直接访问'
			}, {
				value: 400,
				name: '搜索引擎'
			}*/],
			roseType: 'angle',
			label: {
				normal: {
					textStyle: {
						color: 'rgba(255, 255, 255, 0.3)'
					}
				}
			},
			labelLine: {
				normal: {
					lineStyle: {
						color: 'rgba(255, 255, 255, 0.3)'
					}
				}
			},
			itemStyle: {
				normal: {
					color: '#c23531',
					shadowBlur: 200,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	});
	setOption("echarts-3",{
	   title: {
        text: '折线图堆叠'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['收益']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'收益',
            type:'line',
            stack: '花费',
            data:incomeArr
        },
        /*{
            name:'QQ',
            type:'line',
            stack: '花费',
            data:[220, 182, 191, 234, 290, 330, 310]
        },
        {
            name:'游戏',
            type:'line',
            stack: '花费',
            data:[150, 232, 201, 154, 190, 330, 410]
        },
        {
            name:'音乐',
            type:'line',
            stack: '花费',
            data:[320, 332, 301, 334, 390, 330, 320]
        },
        {
            name:'看电影',
            type:'line',
            stack: '花费',
            data:[820, 932, 901, 934, 1290, 1330, 1320]
        },
				{
						name:'打篮球',
						type:'line',
						stack: '花费',
						data:[20, 632, 401, 734, 990, 1230, 320]
				}*/
    ]
	});
});
/*
 **功能:柱状、折线图
 **参数1：元素ID
 **参数2：配置项设置
 */
function setOption(ident, opt) {
	//基于准备好的dom,初始化echarts实例
	let myChart = echarts.init($('#' + ident)[0]);
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(opt);
}
/*
 **功能:南丁格尔图
 **参数1：元素ID
 **参数2：配置项设置
 */
function setOption(ident, opt) {
	//基于准备好的dom,初始化echarts实例
	let myChart = echarts.init($('#' + ident)[0]);
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(opt);
}