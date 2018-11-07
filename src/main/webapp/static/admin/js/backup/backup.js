layui.use(['jquery'], function () {
    var $ = layui.$;

    $('#backup').click(function(){
        window.location.href = "/admin/backup/sql";
    })
});