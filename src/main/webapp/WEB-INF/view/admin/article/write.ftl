<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>写博客</title>
    <link rel="stylesheet" href="/static/editor.md/css/style.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.css"/>
    <script src="/static/jquery/jquery.min.js"></script>
</head>
<body>
<div id="layout">
    <header>
        <h1>写博客</h1>
    </header>
    <div id="editormd">
        <textarea style="display:none;"></textarea>
    </div>
</div>
<script src="/static/editor.md/editormd.min.js"></script>
<script type="text/javascript">
    var testEditor;

    $(function () {
        testEditor = editormd({
            id      : "editormd",
            width   : "90%",
            height  :  650,
            path    : "/static/editor.md/lib/"
        });
    });
</script>
</body>
</html>