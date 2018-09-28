var mdEditor;

$(function () {
    mdEditor = editormd({
        id: "editormd",
        width: "90%",
        height: 680,
        path: "/static/editor.md/lib/",
        placeholder: "请用markdown写博客",
        saveHTMLToTextarea: true,
        imageUpload: true,
        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL: "/admin/article/image",
    });
});

$('#saveBlog').click(function () {

    var title = $('#title').val();
    var blogMd = mdEditor.getMarkdown();
    var blogHtml = mdEditor.getHTML();

    if(title.length == 0){
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                type: 0,
                content: "标题不能为空",
            });
        });
        return;
    }

    if(blogMd.length == 0){
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                type: 0,
                content: "内容不能为空",
            });
        });
        return;
    }

    var data = {
        title: title,
        blogMd: blogMd,
        blogHtml: blogHtml,
    };

    $.ajax({
        url: "/admin/article/insert",
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
                        title: "保存成功",
                        content: data.message
                    });
                });
            }else{
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.open({
                        type: 0,
                        title: "保存失败",
                        content: data.message
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