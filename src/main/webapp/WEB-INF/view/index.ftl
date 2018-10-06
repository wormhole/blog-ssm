<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/custom/css/index.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.preview.min.css"/>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/editor.md/lib/marked.min.js"></script>
    <script src="/static/editor.md/lib/prettify.min.js"></script>
    <script src="/static/editor.md/editormd.min.js"></script>
    <title>栈溢出</title>
</head>
<body>
<div id="layout">
    <header>
        <h1></h1>
    </header>
    <div id="sidebar">
        <h1>目录</h1>
        <div class="markdown-body editormd-preview-container" id="custom-toc-container"></div>
    </div>
    <div id="editormd-view">
        <textarea style="display:none;"></textarea>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $.ajax({
            url: "/test",
            type: "get",
            dataType: "json",
            success: function (data) {
                editormd.markdownToHTML("editormd-view", {
                    markdown:data.data.md,
                    tocContainer: "#custom-toc-container",
                });
            },
            error: function (data) {

            }
        });

    });
</script>
</body>
</html>