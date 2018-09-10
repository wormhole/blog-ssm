layui.use('form', function () {
    var form = layui.form;
});

$('#verifyImg').click(function () {
    $(this).attr('src', $(this).attr('src') + '?' + Math.random());
});