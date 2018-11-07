layui.use(['layer', 'jquery', 'table'], function () {

    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;

    var parameter1 = {
        id: 'today-table',
        elem: '#today-table',
        url: '/admin/today',
        method: 'get',
        width: 1300,
        cellMinWidth: 100,
        page: true,
        toolbar: '#toolbar-head',
        parseData: function (response) {
            return {
                code: response.status,
                message: response.message,
                count: response.data.count,
                data: response.data.items
            }
        },
        cols: [[
            {field: 'ip', width: 150, title: 'IP'},
            {field: 'url', width: 300, title: 'URL'},
            {field: 'status', width: 100, title: '状态码'},
            {field: 'agent', width: 600, title: '客户端'},
            {fixed: 'right', field: 'dateString', width: 200, title: '日期'},
        ]]
    };

    var parameter2 = {
        id: 'error-table',
        elem: '#error-table',
        url: '/admin/error',
        method: 'get',
        width: 1300,
        cellMinWidth: 100,
        page: true,
        toolbar: '#toolbar-head',
        parseData: function (response) {
            return {
                code: response.status,
                message: response.message,
                count: response.data.count,
                data: response.data.items
            }
        },
        cols: [[
            {field: 'ip', width: 150, title: 'IP'},
            {field: 'url', width: 300, title: 'URL'},
            {field: 'status', width: 100, title: '状态码'},
            {field: 'agent', width: 600, title: '客户端'},
            {fixed: 'right', field: 'dateString', width: 200, title: '日期'},
        ]]
    };

    initFlow('flow');

    initCountTable();

    table.render(parameter1);

    table.render(parameter2);

    function initFlow(id) {
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
                data: ['访问量', '访客量']
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
            }, {
                name: '访客量',
                type: 'line',
                data: [],
                areaStyle: {}
            }]
        };
        flowAjax(option, flow);
    }

    function initCountTable() {
        countAjax();
    }

    function flowAjax(option, chart) {
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

    function countAjax() {
        $.ajax({
            url: "/admin/count",
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.status == 0) {
                    $('#todayVisit').text(data.data.todayVisit);
                    $('#todayVisitor').text(data.data.todayVisitor);
                    $('#totalVisit').text(data.data.totalVisit);
                    $('#totalVisitor').text(data.data.totalVisitor);
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