layui.use(['form', 'layer'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var mdEditor;
    var id = getQueryVariable('id');

    $(function () {
        mdEditor = editormd({
            id: "editormd",
            width: "95%",
            height: "675px",
            path: "/static/plugins/editor.md/lib/",
            placeholder: "请用markdown写博客",
            saveHTMLToTextarea: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "/api/admin/article/image",
        });
    });

    function getQueryVariable(variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] === variable) {
                return pair[1];
            }
        }
        return null;
    }

    function saveArticleAjax(param) {
        $.ajax({
            url: "/api/admin/article/insert",
            type: "post",
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.status === 0) {
                    layer.open({
                        type: 0,
                        content: response.message
                    });
                } else {
                    layer.open({
                        type: 0,
                        content: response.message
                    });
                }
            },
            error: function (response) {
                layer.open({
                    type: 0,
                    content: "服务器错误",
                });
            }
        });
    }

    function updateArticleAjax(param) {
        $.ajax({
            url: "/api/admin/article/update",
            type: "post",
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.status === 0) {
                    layer.open({
                        type: 0,
                        content: response.message
                    });
                } else {
                    layer.open({
                        type: 0,
                        content: response.message
                    });
                }
            },
            error: function (response) {
                layer.open({
                    type: 0,
                    content: "服务器错误",
                });
            }
        });
    }

    $('#save-btn').click(function () {

        var title = $('#title').val();
        var articleMd = mdEditor.getMarkdown();
        var articleHtml = mdEditor.getPreviewedHTML();
        var articleCode = $('#article-code').val();
        var categoryId = $('#category-select').val();

        if (title.length === 0) {
            layer.open({
                type: 0,
                content: "标题不能为空"
            });
            return;
        }

        if (articleCode.length === 0) {
            layer.open({
                type: 0,
                content: "博客编码不能为空"
            });
        }

        if (articleMd.length === 0) {
            layer.open({
                type: 0,
                content: "内容不能为空"
            });
            return;
        }

        var data = {
            title: title,
            articleMd: articleMd,
            articleHtml: articleHtml,
            articleCode: articleCode,
            categoryId: categoryId,
        };

        if (id == null) {
            var param = {
                data:{
                    article:[data]
                }
            };
            saveArticleAjax(param);
        }
        else {
            data['id'] = id;
            var param = {
                data:{
                    article:[data]
                }
            };
            updateArticleAjax(param);
        }
    });
});