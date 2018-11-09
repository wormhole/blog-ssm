layui.use(['layer', 'jquery'], function () {

    var layer = layui.layer;
    var $ = layui.$;

    function updateBaseInfoAjax(param) {
        $.ajax({
            url: "/admin/user/update?type=base",
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
                    if (response.data['email'] !== undefined) {
                        $('#email-error').html(response.data['email']);
                        $('#email-error').removeClass('hidden');
                    } else if (response.data['nickname'] !== undefined) {
                        $('#nickname-error').html(response.data['nickname']);
                        $('#nickname-error').removeClass('hidden');
                    }
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

    function updatePasswordAjax(param) {
        $.ajax({
            url: "/admin/user/update?type=password",
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
                    if (response.data['oldPassword'] !== undefined) {
                        $('#old-password-error').html(response.data['oldPassword']);
                        $('#old-password-error').removeClass('hidden');
                    } else if (response.data['password'] !== undefined) {
                        $('#new-password-error').html(response.data['password']);
                        $('#new-password-error').removeClass('hidden');
                    }
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

    $('#savebase-btn').click(function () {

        $('#email-error').addClass('hidden');
        $('#nickname-error').addClass('hidden');

        var email = $('#email').val();
        var nickname = $('#nickname').val();

        var data = {};
        data['email'] = email;
        data['nickname'] = nickname;

        var param = {
            data: {
                user: [data]
            }
        };
        updateBaseInfoAjax(param);
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

        var param = {
            data: {
                user: [data]
            }
        };

        updatePasswordAjax(param);
    });

});