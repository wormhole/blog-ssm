layui.use(['form', 'layer'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var mdEditor;
    var categoryList;
    var article;
    var id = getQueryVariable('id');

    $(function () {
        if (id != null) {
            loadArticleAjax(id);
        } else {
            mdEditor = editormd({
                id: "editormd",
                width: "95%",
                height: "675px",
                path: "/static/plugins/editor.md/lib/",
                placeholder: "请用markdown写博客",
                saveHTMLToTextarea: true,
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL: "/admin/article/image",
            });
            loadCategoryAjax();
        }
    });

    function getQueryVariable(variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
                return pair[1];
            }
        }
        return null;
    }

    function loadArticleAjax(id) {
        var data = {
            id: id
        };
        $.ajax({
            url: "/admin/article/get",
            type: "post",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (data) {
                if (data.status == 0) {
                    article = data.data;
                    mdEditor = editormd({
                        id: "editormd",
                        width: "95%",
                        height: "675px",
                        path: "/static/plugins/editor.md/lib/",
                        placeholder: "请用markdown写博客",
                        saveHTMLToTextarea: true,
                        imageUpload: true,
                        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                        imageUploadURL: "/admin/article/image",
                        markdown: article.articleMd
                    });
                    loadCategoryAjax();
                    renderTitle(article.title);
                    renderCode(article.url);
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
                    content: "服务器错误"
                });
            }
        });
    }

    function loadCategoryAjax() {
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
                    content: "服务器错误"
                });
            }
        });
    }

    function saveArticleAjax(data) {
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
                    content: "服务器错误",
                });
            }
        });
    }

    function updateArticleAjax(data) {
        data.id = article.id;
        $.ajax({
            url: "/admin/article/update",
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
                    content: "服务器错误",
                });
            }
        });
    }

    function renderCode(url) {
        var arr = url.split('/');
        $('#article-code').val(arr[arr.length - 1]);
    }

    function renderTitle(title) {
        $('#title').val(title);
    }

    function renderSelect(categoryList) {
        var optionTemplate = '<option></option>';
        var select = $('#category-select');
        for (var i = 0; i < categoryList.length; i++) {
            var option = $(optionTemplate);
            option.val(categoryList[i].id);
            option.text(categoryList[i].categoryName);
            if (id != null) {
                if (categoryList[i].id == article.categoryId) {
                    option.attr("selected", true);
                }
            } else {
                if (categoryList[i].categoryCode == 'uncategory') {
                    option.attr("selected", true);
                }
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
        var url;

        if (id == null) {
            url = getDateUrl(articleCode);
        } else {
            var arr = article.url.split('/');
            arr[arr.length - 1] = articleCode;
            url = arr.join('/');
        }

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

        if (id == null)
            saveArticleAjax(data);
        else
            updateArticleAjax(data);
    });
});