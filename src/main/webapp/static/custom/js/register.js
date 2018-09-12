$('#verifyImg').click(function () {
    $(this).attr('src', $(this).attr('src') + '?' + Math.random());
});

$('#registerBtn').click(function () {
    var email = $('#email').val();
    var nickname = $('#nickname').val();
    var password = $('#password').val();
    var checkedPassword = $('#checkedPassword').val();
    var vcode = $('#vcode').val();

    if(!(email.length && nickname.length && password.length && checkedPassword.length && vcode.length)){
        return;
    }

    if(password != checkedPassword){
        $('#checkedPassword').val('');
        $('.alert-danger span').html('两次密码不一致');
        $('.alert-danger').removeClass('hidden');
        $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
        return;
    }

    var data = {};
    data['email'] = email;
    data['nickname'] = nickname;
    data['password'] = password;
    data['vcode'] = vcode;

    $.ajax({
        url: "/admin/register",
        type: "post",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if(data.status == 0){
                $('.alert-success').removeClass('hidden');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }else if(data.status == 1){
                $('.alert-danger span').html(data.data);
                $('.alert-danger').removeClass('hidden');
                $('#email').val('');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }else if(data.status == 2){
                $('.alert-danger span').html(data.data);
                $('.alert-danger').removeClass('hidden');
                $('#nickname').val('');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }else if(data.status == 3){
                $('.alert-danger span').html(data.data);
                $('.alert-danger').removeClass('hidden');
                $("#password").val('');
                $('#checkedPassword').val('');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }else if(data.status == 4){
                $('.alert-danger span').html(data.data);
                $('.alert-danger').removeClass('hidden');
                $("#vcode").val('');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }else if(data.status == 6){
                $('.alert-danger span').html(data.data);
                $('.alert-danger').removeClass('hidden');
                $('#email').val('');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }else{
                $('.alert-danger span').html(data.data);
                $('.alert-danger').removeClass('hidden');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }
        },
        error: function () {
            $('.alert-danger span').html('请求错误');
            $('.alert-danger').removeClass('hidden');
            $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
        }
    });

});