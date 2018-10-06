<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/custom/css/category.css">
    <script src="/static/layui/layui.js"></script>
    <title>栈溢出</title>
</head>
<body>
<div class="layui-card" id="baseConf">
    <div class="layui-card-header">分类</div>
    <div class="layui-card-body">
        <table class="layui-hide" id="category-table"></table>
    </div>
</div>
</body>
<script>
    layui.use(['table', 'jquery', 'layer'], function () {
        var table = layui.table;
        var $ = layui.$;
        var layer = layui.layer;

        table.render({
            elem: '#category-table',
            url: '/admin/article/category/list',
            method: 'get',
            width: 650,
            cellMinWidth: 100,
            page: true,
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
                {field: 'categoryCode', width: 150, title: '编码', sort: true, edit: 'text'}
            ]]
        });
    });
</script>
</html>