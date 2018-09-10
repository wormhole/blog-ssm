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
        $("#checkedPassword").attr('data-content','两次密码不一致');
        $("[data-toggle='popover']").popover('show');
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
                $("#vcode").attr('data-content',data.data);
                $("[data-toggle='popover']").popover('show');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }else if(data.status == 2){
                $("#email").attr('data-content',data.data.emailErrorInfo);
                $("#password").attr('data-content',data.data.passwordErrorInfo);
                $('#nickname').attr('data-content',data.data.nicknameErrorInfo);
                $("[data-toggle='popover']").popover('show');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }else{
                $('.alert-danger span').html(data.data);
                $('.alert-danger').removeClass('hidden');
                $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
            }
        },
        error: function () {
            console.log("未知错误");
            $('#verifyImg').attr('src', '/vcode' + '?' + Math.random());
        }
    });

});

$('#email').focus(function(){
    $("#email").attr('data-content','');
    $("[data-toggle='popover']").popover('toggle');
});

$('#nickname').focus(function(){
    $("#nickname").attr('data-content','');
    $("[data-toggle='popover']").popover('toggle');
});

$('#password').focus(function(){
    $("#password").attr('data-content','');
    $("[data-toggle='popover']").popover('toggle');
});

$('#checkedPassword').focus(function() {
    $("#checkedPassword").attr('data-content','');
    $("[data-toggle='popover']").popover('toggle');
});

$('#vcode').focus(function(){
    $("#vcode").attr('data-content','');
    $("[data-toggle='popover']").popover('toggle');
});