<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>栈溢出</title>
    <link rel="stylesheet" href="/static/custom/css/write.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.css"/>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/layui/layui.js"></script>
    <script src="/static/editor.md/editormd.min.js"></script>
</head>
<body>
<div class="row">
    <div class="offset-3 col-5">
        <input type="text" class="form-control" id="title" placeholder="请输入标题">
    </div>
    <div class="col-2">
        <button type="button" class="btn btn-success" id="saveBlog">发布博客</button>
    </div>
</div>
<div id="layout">
    <div id="editormd">
    </div>
</div>
<script src="/static/custom/js/write.js"></script>
</body>
</html>