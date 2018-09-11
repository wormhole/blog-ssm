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

$('#headImg').click(function () {
    $('input[type="file"]').click();
});

$('input[type="file"]').change(function () {
    var file = this.files[0];
    $('#headImg').attr('src', getObjectURL(file));
});

$('#saveBaseBtn').click(function(){

    $('#email-error').addClass('hidden');
    $('#nickname-error').addClass('hidden');

    var email = $('#email').val();
    var nickname = $('#nickname').val();

    var data = {};
    data['email'] = email;
    data['nickname'] = nickname;

    $.ajax({
        url: "/user/update/baseinfo",
        type: "post",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if(data.status == 0){

            }else if(data.status == 1){
                $('#email-error').html(data.data);
                $('#email-error').removeClass('hidden');
            }else if(data.status == 2){
                $('#nickname-error').html(data.data);
                $('#nickname-error').removeClass('hidden');
            }else if(data.status == 3){
                $('#email-error').html(data.data);
                $('#email-error').removeClass('hidden');
            }

        },
        error: function (data) {
            console.log(data);
        }
    });
});