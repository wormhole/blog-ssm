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

        var data = {};
        data['email'] = email;
        data['nickname'] = nickname;
        data['password'] = password;
        data['vcode'] = vcode;

        registerAjax(data);
    });

    function registerAjax(data) {
        $.ajax({
            url: "/admin/register",
            type: "post",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.status == 0) {
                    $('blockquote').html('<a href="/admin/login">点击前往登陆页面</a>');
                } else if (data.status == 1) {
                    if (data.data != undefined) {
                        if (data.data['vcode'] != undefined) {
                            $('blockquote').html(data.data['vcode']);
                            $('#vcode').val('');
                        } else if (data.data['email'] != undefined) {
                            $('blockquote').html(data.data['email']);
                            $('#email').val('');
                        } else if (data.data['nickname'] != undefined) {
                            $('blockquote').html(data.data['nickname']);
                            $('#nickname').val('');
                        } else if (data.data['password'] != undefined) {
                            $('blockquote').html(data.data['password']);
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
            error: function (data) {
                $('blockquote').html('服务器错误');
                $('blockquote').removeClass('hidden');
                $('#verif-img').attr('src', '/api/vcode' + '?' + Math.random());
            }
        });
    }
});