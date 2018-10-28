layui.use(['layer', 'jquery'], function () {

    var layer = layui.layer;
    var $ = layui.$;

    function updateSEOAjax(data) {
        $.ajax({
            url: "/admin/setting/update",
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
                    if (data.data['title'] != undefined) {
                        layer.open({
                            type: 0,
                            content: data.data['title']
                        });
                    } else if (data.data['keywords'] != undefined) {
                        layer.open({
                            type: 0,
                            content: data.data['keywords']
                        });
                    } else if (data.data['description'] != undefined) {
                        layer.open({
                            type: 0,
                            content: data.data['description']
                        });
                    } else if (data.data['copyright'] != undefined) {
                        layer.open({
                            type: 0,
                            content: data.data['copyright']
                        });
                    }
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

    function updateBaseAjax(data) {
        $.ajax({
            url: "/admin/setting/update",
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
                    if (data.data['nickname'] != undefined) {
                        layer.open({
                            type: 0,
                            content: data.data['nickname']
                        });
                    } else if (data.data['signature'] != undefined) {
                        layer.open({
                            type: 0,
                            content: data.data['signature']
                        });
                    }
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
        $.ajax({
            url: "/admin/setting/update",
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
                    if (data.data['items'] != undefined) {
                        layer.open({
                            type: 0,
                            content: data.data['items']
                        });
                    }
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

    function updateHeadAjax(formData) {
        $.ajax({
            url: "/admin/setting/update/head",
            type: 'POST',
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (data) {
                if (data.status == 0) {
                    $('#head').attr('src', data.data.head);
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

    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

    $('#head').click(function () {
        $('input[type="file"]').click();
    });

    $('input[type="file"]').change(function () {
        var file = this.files[0];
        $('#head').attr('src', getObjectURL(file));
    });

    $('#seo-btn').click(function () {
        var title = $('#title').val();
        var keywords = $('#keywords').val();
        var description = $('#description').val();
        var copyright = $('#copyright').val();

        var data = [];
        data.push({key: 'title', value: title});
        data.push({key: 'keywords', value: keywords});
        data.push({key: 'description', value: description});
        data.push({key: 'copyright', value: copyright});

        updateSEOAjax(data);
    });

    $('#base-btn').click(function () {
        var nickname = $('#nickname').val();
        var signature = $('#signature').val();

        var data = [];
        data.push({key: 'nickname', value: nickname});
        data.push({key: 'signature', value: signature});

        updateBaseAjax(data);
    });

    $('#article-btn').click(function () {
        var items = $('#items').val();

        var data = [];
        data.push({key: 'items', value: items});

        updateArticleAjax(data);
    });

    $('#head-btn').click(function () {
        var formData = new FormData();
        formData.append('headImg', $('#head-img').get(0).files[0]);
        updateHeadAjax(formData);
    });

});