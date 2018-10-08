layui.use(['table', 'jquery', 'layer'], function () {
    var table = layui.table;
    var $ = layui.$;
    var layer = layui.layer;

    var parameter = {
        id: 'category-table',
        elem: '#category-table',
        url: '/admin/article/category/list',
        method: 'get',
        width: 775,
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
            {field: 'id', width: 350, title: 'ID'},
            {field: 'categoryName', width: 150, title: '分类名', sort: true, edit: 'text'},
            {field: 'categoryCode', width: 150, title: '编码', sort: true, edit: 'text'},
            {fixed: 'right', width: 120, title: '操作', toolbar: '#toolbar-col'}
        ]]
    };

    var tableIns = table.render(parameter);

    table.on('toolbar(category-table-1)', function (obj) {
        if (obj.event == 'add') {
            layer.open({
                type: 1,
                title: '新增',
                skin: 'layui-layer-rim',
                area: ['420px', '240px'],
                btn: ['新增'],
                content: $('#add-edit').text(),
                yes: function (index, layero) {
                    var categoryName = $('#categoryName').val();
                    var categoryCode = $('#categoryCode').val();
                    if (!(categoryCode.length && categoryName.length)) {
                        layer.open({
                            type: 0,
                            content: "不能为空"
                        });
                    } else {
                        var data = {};
                        data['categoryCode'] = categoryCode;
                        data['categoryName'] = categoryName;
                        insertCategory(data, tableIns);
                        layer.close(index);
                    }
                }
            });
        }
    });

    table.on('tool(category-table-1)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'del') {
            layer.confirm('确认删除该分类吗', function (index) {
                deleteCategory(data,obj);
                layer.close(index);
            });
        }
    });

    table.on('edit(category-table-1)', function (obj) {
        if (!obj.value.length) {
            layer.open({
                type: 0,
                content: "不能为空"
            });
            tableIns.reload(parameter);
        } else {
            updateCategory(obj.data);
        }
    });

    function updateCategory(data) {
        $.ajax({
            url: "/admin/article/category/update",
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
            },
            error: function (data) {
                layer.open({
                    type: 0,
                    content: "请求失败"
                });
            }
        });
    }

    function deleteCategory(data,obj) {
        $.ajax({
            url: "/admin/article/category/delete",
            type: "post",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.status == 0) {
                    obj.del();
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

    function insertCategory(data, tableIns) {
        $.ajax({
            url: "/admin/article/category/insert",
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
                    tableIns.reload(parameter);
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
                    title: "错误",
                    content: "请求失败"
                });
            }
        });
    }
});