var mdEditor;

$(function () {
    mdEditor = editormd({
        id: "editormd",
        width: "90%",
        height: 650,
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