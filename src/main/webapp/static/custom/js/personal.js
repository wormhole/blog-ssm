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

$('#saveBaseBtn').click(function () {

    $('#email-error').addClass('hidden');
    $('#nickname-error').addClass('hidden');
    $('#signatrue-error').addClass('hidden');

    var email = $('#email').val();
    var nickname = $('#nickname').val();
    var signature = $('#signature').val();

    var data = {};
    data['email'] = email;
    data['nickname'] = nickname;
    data['signature'] = signature;

    $.ajax({
        url: "/admin/user/update/baseinfo",
        type: "post",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.status == 0) {
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.open({
                        type: 0,
                        content: '修改成功',
                    });
                });
            } else if (data.status == 1) {
                $('#email-error').html(data.data);
                $('#email-error').removeClass('hidden');
            } else if (data.status == 2) {
                $('#nickname-error').html(data.data);
                $('#nickname-error').removeClass('hidden');
            } else if (data.status == 6) {
                $('#email-error').html(data.data);
                $('#email-error').removeClass('hidden');
            } else if (data.status == 8) {
                $('#signatrue-error').html(data.data);
                $('#signatrue-error').removeClass('hidden');
            }

        },
        error: function (data) {
            layui.use('layer', function() {
                var layer = layui.layer;
                layer.open({
                    type: 0,
                    content: "请求失败",
                });
            });
        }
    });
});

$('#savePwdBtn').click(function () {
    $('#checkedPassword-error').addClass('hidden');
    $('#oldPassword-error').addClass('hidden');
    $('#newPassword-error').addClass('hidden');

    var oldPassword = $('#oldPassword').val();
    var newPassword = $('#newPassword').val();
    var checkedPassword = $('#checkedPassword').val();

    if (newPassword != checkedPassword) {
        $('#checkedPassword').val('');
        $('#checkedPassword-error').html("两次密码不匹配");
        $('#checkedPassword-error').removeClass('hidden');
        return;
    }

    var data = {};
    data['oldPassword'] = oldPassword;
    data['newPassword'] = newPassword;

    $.ajax({
        url: "/admin/user/update/password",
        type: "post",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.status == 0) {
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.open({
                        type: 0,
                        content: "密码修改成功",
                    });
                });
            } else if (data.status == 5) {
                $('#oldPassword-error').html(data.data);
                $('#oldPassword-error').removeClass('hidden');
            } else if (data.status == 3) {
                $('#newPassword-error').html(data.data);
                $('#newPassword-error').removeClass('hidden');
            }
        },
        error: function (data) {
            layui.use('layer', function() {
                var layer = layui.layer;
                layer.open({
                    type: 0,
                    content: "请求失败",
                });
            });
        }
    });
});

$('#saveHeadBtn').click(function(){
    var formData = new FormData();
    formData.append('headImg', $('#headImg').get(0).files[0]);
    $.ajax({
        url: "/admin/user/update/head",
        type: 'POST',
        data: formData,
        cache : false,
        processData: false,
        contentType: false,
        dataType:"json",
        success : function(data) {
            if (data.status == 0) {
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.open({
                        type: 0,
                        content: "头像上传成功",
                    });
                });
            } else if(data.status == 7){
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.open({
                        type: 0,
                        content: "头像上传失败",
                    });
                });
            }
        }
    });
});