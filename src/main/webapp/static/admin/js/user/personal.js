layui.use(['layer', 'jquery'], function () {

    var layer = layui.layer;
    var $ = layui.$;

    function updateBaseInfoAjax(data) {
        $.ajax({
            url: "/admin/user/update/baseinfo",
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
                    if (data.data['email'] != undefined) {
                        $('#email-error').html(data.data['email']);
                        $('#email-error').removeClass('hidden');
                    } else if (data.data['nickname'] != undefined) {
                        $('#nickname-error').html(data.data['nickname']);
                        $('#nickname-error').removeClass('hidden');
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

    function updatePasswordAjax(data){
        $.ajax({
            url: "/admin/user/update/password",
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
                    if (data.data['oldPassword'] != undefined) {
                        $('#old-password-error').html(data.data['oldPassword']);
                        $('#old-password-error').removeClass('hidden');
                    } else if (data.data['password'] != undefined) {
                        $('#new-password-error').html(data.data['password']);
                        $('#new-password-error').removeClass('hidden');
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

    $('#savebase-btn').click(function () {

        $('#email-error').addClass('hidden');
        $('#nickname-error').addClass('hidden');

        var email = $('#email').val();
        var nickname = $('#nickname').val();

        var data = {};
        data['email'] = email;
        data['nickname'] = nickname;

        updateBaseInfoAjax(data);
    });

    $('#savepwd-btn').click(function () {
        $('#checked-password-error').addClass('hidden');
        $('#old-password-error').addClass('hidden');
        $('#new-password-error').addClass('hidden');

        var oldPassword = $('#old-password').val();
        var newPassword = $('#new-password').val();
        var checkedPassword = $('#checked-password').val();

        if (newPassword != checkedPassword) {
            $('#checked-password').val('');
            $('#checked-password-error').html("两次密码不匹配");
            $('#checked-password-error').removeClass('hidden');
            return;
        }

        var data = {};
        data['oldPassword'] = oldPassword;
        data['password'] = newPassword;

        updatePasswordAjax(data);
    });

});