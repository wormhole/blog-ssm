layui.use('form','jquery', function () {
    var form = layui.form;
    var $ = layui.$;

    $('#verify-img').click(function () {
        $(this).attr('src', $(this).attr('src') + '?' + Math.random());
    });
});

