$(function() {
	require.config({
		paths : {
			echarts : './build/dist'
		}
	});

	// 作为入口
	require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie',
			'echarts/chart/line' ], function(ec) {
		var pie = ec.init(document.getElementById('pieChart'));
		var line = ec.init(document.getElementById('lineChart'));
		pieOption = {
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎' ]
			},
			toolbox : {
				show : false,
			},
			calculable : false,
			xAxis : [ {
				type : 'category',
				boundaryGap : false,
				data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '邮件营销',
				type : 'line',
				stack : '总量',
				data : [ 120, 132, 101, 134, 90, 230, 210 ]
			}, {
				name : '联盟广告',
				type : 'line',
				stack : '总量',
				data : [ 220, 182, 191, 234, 290, 330, 310 ]
			}, {
				name : '视频广告',
				type : 'line',
				stack : '总量',
				data : [ 150, 232, 201, 154, 190, 330, 410 ]
			}, {
				name : '直接访问',
				type : 'line',
				stack : '总量',
				data : [ 320, 332, 301, 334, 390, 330, 320 ]
			}, {
				name : '搜索引擎',
				type : 'line',
				stack : '总量',
				data : [ 820, 932, 901, 934, 1290, 1330, 1320 ]
			} ]
		};

		lineOption = {
			title : {
				text : '某站点用户访问来源',
				subtext : '纯属虚构',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'left',
				data : [ '直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎' ]
			},
			toolbox : {
				show : false,
			},
			calculable : false,
			series : [ {
				name : '访问来源',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [ {
					value : 335,
					name : '直接访问'
				}, {
					value : 310,
					name : '邮件营销'
				}, {
					value : 234,
					name : '联盟广告'
				}, {
					value : 135,
					name : '视频广告'
				}, {
					value : 1548,
					name : '搜索引擎'
				} ]
			} ]
		};

		pie.setOption(pieOption);
		line.setOption(lineOption);
	});
});