<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>凉衫薄</title>
    <link rel="stylesheet" href="/static/custom/css/index.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.css"/>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/editor.md/lib/marked.min.js"></script>
    <script src="/static/editor.md/editormd.min.js"></script>
</head>
<body>
<div id="layout">
    <header>
        <h1>${title}</h1>
    </header>
    <div id="sidebar">
        <h1>目录</h1>
        <div class="markdown-body editormd-preview-container" id="custom-toc-container"></div>
    </div>
    <div id="editormd-view">
    </div>
</div>

<script type="text/javascript">
    $(function () {
        editormd.markdownToHTML("editormd-view", {
            markdown: "[TOC]\n" +
            "\n" +
            "# aaa\n" +
            "\n" +
            "## bbb\n" +
            "\n" +
            "### ccc\n" +
            "\n" +
            "#### ddd",
            tocContainer: "#custom-toc-container",
        });
    });
</script>
</body>
</html>