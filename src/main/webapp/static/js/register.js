layui.use(['jquery'], function () {
    var $ = layui.$;

    $('#verify-img').click(function () {
        $(this).attr('src', $(this).attr('src') + '?' + Math.random());
    });

    $('#register-btn').click(function () {
        var email = $('#email').val();
        var nickname = $('#nickname').val();
        var password = $('#password').val();
        var checkedPassword = $('#checked-password').val();
        var vcode = $('#vcode').val();

        if (!(email.length && nickname.length && password.length && checkedPassword.length && vcode.length)) {
            return;
        }

        if (password != checkedPassword) {
            $('#checked-password').val('');
            $('blockquote').html('两次密码不一致');
            $('blockquote').removeClass('hidden');
            $('#verify-img').attr('src', '/api/vcode' + '?' + Math.random());
            return;
        }

        var user = {};
        user['email'] = email;
        user['nickname'] = nickname;
        user['password'] = password;
        user['vcode'] = vcode;
        var param = {'data': {"user": [user]}};

        registerAjax(param);
    });

    function registerAjax(param) {
        $.ajax({
            url: "/api/register",
            type: "post",
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.status === 0) {
                    $('blockquote').html('<a href="/login">点击前往登陆页面</a>');
                } else {
                    if (response.data !== null) {
                        if (response.data['vcode'] !== undefined) {
                            $('blockquote').html(response.data['vcode']);
                            $('#vcode').val('');
                        } else if (response.data['email'] !== undefined) {
                            $('blockquote').html(response.data['email']);
                            $('#email').val('');
                        } else if (response.data['nickname'] !== undefined) {
                            $('blockquote').html(response.data['nickname']);
                            $('#nickname').val('');
                        } else if (response.data['password'] !== undefined) {
                            $('blockquote').html(response.data['password']);
                            $("#password").val('');
                            $('#checked-password').val('');
                        }
                    } else {
                        $('blockquote').html(data.message);
                    }
                }
                $('blockquote').removeClass('hidden');
                $('#verify-img').attr('src', '/api/vcode' + '?' + Math.random());
            },
            error: function (response) {
                $('blockquote').html('服务器错误');
                $('blockquote').removeClass('hidden');
                $('#verif-img').attr('src', '/api/vcode' + '?' + Math.random());
            }
        });
    }
});