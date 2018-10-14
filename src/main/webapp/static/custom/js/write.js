layui.use(['form', 'layer'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var mdEditor;
    var categoryList;

    $(function () {
        mdEditor = editormd({
            id: "editormd",
            width: "95%",
            height: "675px",
            path: "/static/editor.md/lib/",
            placeholder: "请用markdown写博客",
            saveHTMLToTextarea: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "/admin/article/image"
        });

        $.ajax({
            url: "/admin/article/category/list",
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.status == 0) {
                    categoryList = data.data.items;
                    renderSelect(categoryList);
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
    });

    function renderSelect(categoryList) {
        var optionTemplate = '<option></option>';
        var select = $('#category-select');
        for (var i = 0; i < categoryList.length; i++) {
            var option = $(optionTemplate);
            option.val(categoryList[i].id);
            option.text(categoryList[i].categoryName);
            if (categoryList[i].categoryCode == 'uncategory') {
                option.attr("selected", true);
            }
            select.append(option);
        }
        form.render('select');
    }

    function getDateUrl(code) {
        var date = new Date();

        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();

        month = month < 10 ? "0" + month : month;
        day = day < 10 ? "0" + day : day;

        var result = "/article/" + year + "/" + month + "/" + day + "/" + code;
        return result;
    }

    $('#save-btn').click(function () {

        var title = $('#title').val();
        var articleMd = mdEditor.getMarkdown();
        var articleHtml = mdEditor.getPreviewedHTML();
        var articleCode = $('#article-code').val();
        var categoryId = $('#category-select').val();
        var url = getDateUrl(articleCode);

        if (title.length == 0) {
            layer.open({
                type: 0,
                content: "标题不能为空"
            });
            return;
        }

        if (articleCode.length == 0) {
            layer.open({
                type: 0,
                content: "博客编码不能为空"
            });
        }

        if (articleMd.length == 0) {
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
            url: url
        };

        $.ajax({
            url: "/admin/article/insert",
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
                } else if (data.status == 1) {
                    layer.open({
                        type: 0,
                        content: data.message
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
});