layui.use(['layer', 'jquery'], function () {

    var layer = layui.layer;
    var $ = layui.$;

    initFlow('flow');

    function initFlow(id){
        var flow = echarts.init(document.getElementById(id));
        var option = {
            itemStyle: {
                color: '#3FA7DC'
            },
            title: {
                text: '30日内流量分析'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            legend: {
                data: ['访问量','访客量']
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: []
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: '访问量',
                type: 'line',
                data: [],
                areaStyle: {}
            },{
                name: '访客量',
                type: 'line',
                data: [],
                areaStyle: {}
            }]
        };
        flowAjax(option,flow);
    }

    function flowAjax(option,chart){
        $.ajax({
            url: "/admin/flow",
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.status == 0) {
                    option.xAxis.data = data.data.dateList;
                    option.series[0].data = data.data.visitList;
                    option.series[1].data = data.data.visitorList;
                    chart.setOption(option);
                } else {
                    layer.open({
                        type: 0,
                        content: data.message
                    });
                }
            },
            error: function (data) {
                layer.open({
                    type: 0,
                    content: "服务器错误"
                });
            }
        });
    }

});