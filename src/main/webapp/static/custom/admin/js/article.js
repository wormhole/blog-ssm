layui.use(['table', 'jquery', 'layer'], function () {
    var table = layui.table;
    var $ = layui.$;
    var layer = layui.layer;

    var parameter = {
        id: 'article-table',
        elem: '#article-table',
        url: '/admin/article/article/list',
        method: 'get',
        width: 1256,
        cellMinWidth: 100,
        page: true,
        toolbar: 'default',
        parseData: function (response) {
            return {
                code: response.status,
                message: response.message,
                count: response.data.count,
                data: response.data.items
            }
        },
        cols: [[
            {type: 'checkbox'},
            {field: 'id', width: 350, title: 'ID'},
            {field: 'title', width: 150, title: '标题', sort: true},
            {field: 'nickname', width: 150, title: '作者', sort: true},
            {field: 'categoryName', width: 150, title: '分类', sort: true},
            {field: 'url', width: 200, title: 'URL', sort: true},
            {field: 'date', width: 200, title: '日期', sort: true}
        ]]
    };

    var tableIns = table.render(parameter);

});