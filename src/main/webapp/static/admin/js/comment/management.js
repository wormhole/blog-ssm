layui.use(['table', 'jquery', 'layer'], function () {
    var table = layui.table;
    var $ = layui.$;
    var layer = layui.layer;

    var parameter = {
        id: 'comment-table',
        elem: '#comment-table',
        url: '/admin/comment/list',
        method: 'get',
        width: 1560,
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
            {field: 'nickname', width: 150, title: '昵称'},
            {field: 'email', width: 150, title: '邮箱'},
            {field: 'website', width: 150, title: '个人主页'},
            {field: 'dateString', width: 170, title: '日期'},
            {field: 'articleTitle', width: 150, title: '文章标题'},
            {field: 'replyTo', width: 150, title: '回复'},
            {field: 'content', width: 300, title: '内容'},
            {field: 'reviewTag', width: 100, title: '是否审核'},
            {fixed: 'right', width: 230, title: '操作', toolbar: '#toolbar-col'}
        ]]
    };

    var tableIns = table.render(parameter);

    table.on('tool(comment-table-1)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'del') {
            layer.confirm('确认删除该评论吗', function (index) {
                var param = {
                    id: data.id
                };
                deleteAjax(param);
                layer.close(index);
            });
        } else if (layEvent === 'review') {
            var param = {
                id: data.id,
                review: 1
            }
            reviewAjax(param);
        } else if (layEvent === 'unreview') {
            var param = {
                id: data.id,
                review: 0
            }
            reviewAjax(param);
        }
    });

    function deleteAjax(data) {
        $.ajax({
            url: "/admin/comment/delete",
            type: "post",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.status == 0) {
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
                tableIns.reload(parameter);
            },
            error: function (data) {
                layer.open({
                    type: 0,
                    content: "服务器错误"
                });
            }
        });
    }

    function reviewAjax(data){
        $.ajax({
            url: "/admin/comment/review",
            type: "post",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.status == 0) {
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
                tableIns.reload(parameter);
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