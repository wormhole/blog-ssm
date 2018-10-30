layui.use(['element', 'jquery', 'layer'], function () {
    var element = layui.element;
    var $ = layui.$;
    var layer = layui.layer;

    $('img').click(function () {
        $('img').removeClass('select');
        $(this).addClass('select');
    });

    $('button').click(function () {
        var url = $('.select').attr('src');
        if (url != undefined) {
            deleteImage(url);
        }
    });

    function deleteImage(url) {
        $.ajax({
            url: "/admin/media/image/delete?url=" + url,
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.status == 0) {
                    layer.open({
                        type: 0,
                        content: data.message
                    });
                    $('.select').remove();
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
});