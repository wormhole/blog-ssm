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
                    viewModel.user(data.data);
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
                    } else if (data.data['signature'] != undefined) {
                        $('#signature-error').html(data.data['signature']);
                        $('#signature-error').removeClass('hidden');
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

    function updateHeadAjax(formData){
        $.ajax({
            url: "/admin/user/update/head",
            type: 'POST',
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (data) {
                if (data.status == 0) {
                    $('#head').attr('src', data.data.headUrl);
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

    $('#savebase-btn').click(function () {

        $('#email-error').addClass('hidden');
        $('#nickname-error').addClass('hidden');
        $('#signature-error').addClass('hidden');

        var email = $('#email').val();
        var nickname = $('#nickname').val();
        var signature = $('#signature').val();

        var data = {};
        data['email'] = email;
        data['nickname'] = nickname;
        data['signature'] = signature;

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

    $('#savehead-btn').click(function () {
        var formData = new FormData();
        formData.append('headImg', $('#head-img').get(0).files[0]);
        updateHeadAjax(formData);
    });

});