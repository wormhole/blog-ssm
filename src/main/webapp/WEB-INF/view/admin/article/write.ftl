<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>写博客</title>
    <link rel="stylesheet" href="/static/custom/css/write.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/style.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.css"/>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/layui/layui.js"></script>
</head>
<body>
<div class="row">
    <div class="offset-3 col-5">
        <input type="text" class="form-control" id="title" placeholder="请输入标题">
    </div>
    <div class="col-2">
        <button type="button" class="btn btn-primary" id="saveBlog">发布博客</button>
    </div>
</div>
<div id="layout">
    <div id="editormd">
        <textarea></textarea>
        <textarea></textarea>
    </div>
</div>
<script src="/static/editor.md/editormd.min.js"></script>
<script type="text/javascript">
    var mdEditor;

    $(function () {
        mdEditor = editormd({
            id: "editormd",
            width: "90%",
            height: 650,
            path: "/static/editor.md/lib/",
            placeholder: "请用markdown写博客",
            syncScrolling: "single",
            emoji: true,
            saveHTMLToTextarea: true,
            tocm: true,
            tex: true,
            flowChart: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "/admin/article/image",
        });
    });

    $('#saveBlog').click(function () {

        var title = $('#title').val();
        var blogMd = mdEditor.getMarkdown();
        var blogHtml = mdEditor.getHTML();

        var data = {
            title: title,
            blogMd: blogMd,
            blogHtml: blogHtml,
        };

        $.ajax({
            url: "/admin/article/save",
            type: "post",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.status == 0) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.open({
                            type: 0,
                            content: "保存成功",
                        });
                    });
                }
            },
            error: function (data) {
                layer.open({
                    type: 0,
                    content: "请求失败",
                });
            }
        });
    });
</script>
</body>
</html>