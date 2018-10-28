layui.use(['table', 'jquery', 'layer'], function () {
    var table = layui.table;
    var $ = layui.$;
    var layer = layui.layer;

    var parameter = {
        id: 'article-table',
        elem: '#article-table',
        url: '/admin/article/list',
        method: 'get',
        width: 1456,
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
            {field: 'createDateString', width: 200, title: '创建日期', sort: true},
            {field: 'modifyDateString', width: 200, title: '修改日期', sort: true}
        ]]
    };

    var tableIns = table.render(parameter);

    table.on('toolbar(category-table-1)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        if (obj.event == 'add') {
            layer.open({
                type: 2,
                title: '写文章',
                shadeClose: true,
                shade: 0.8,
                area: ['90%', '90%'],
                maxmin: true,
                content: '/admin/article/article',
                cancel: function (index, layero) {
                    tableIns.reload(parameter);
                }
            });
        } else if (obj.event == 'update') {
            if (checkStatus.data.length == 1) {
                layer.open({
                    type: 2,
                    title: '写文章',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['90%', '90%'],
                    maxmin: true,
                    content: '/admin/article/article?id=' + checkStatus.data[0].id,
                    cancel: function (index, layero) {
                        tableIns.reload(parameter);
                    }
                });
            } else {
                layer.open({
                    type: 0,
                    content: "只能选中一篇进行编辑"
                });
            }
        } else if (obj.event == 'delete') {
            if (checkStatus.data.length == 0) {
                layer.open({
                    type: 0,
                    content: "至少选中一条"
                });
            } else {
                layer.confirm('确认删除该文章吗', function (index) {
                    var data = [];
                    for (var i = 0; i < checkStatus.data.length; i++) {
                        var item = {
                            id: checkStatus.data[i].id
                        };
                        data.push(item);
                    }
                    deleteArticleAjax(data);
                    layer.close(index);
                });
            }
        }
    });

    function deleteArticleAjax(data) {
        $.ajax({
            url: "/admin/article/delete",
            type: "post",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.status == 0) {
                    tableIns.reload(parameter);
                    layer.open({
                        type: 0,
                        content: data.message
                    });
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
                    content: "请求失败"
                });
            }
        });
    }

});