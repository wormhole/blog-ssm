layui.use(['jquery'], function () {
    var $ = layui.$;

    $('#verify-img').click(function () {
        $(this).attr('src', $(this).attr('src') + '?' + Math.random());
    });
});

