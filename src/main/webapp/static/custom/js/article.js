var viewModel;

$(function () {
    editormd.markdownToHTML("editormd-view", {});
    viewModel = new ViewModel();
    ko.applyBindings(viewModel);

    $('.heart').click(function () {
        if ($(this).attr("rel") == "true") {
            return;
        } else {
            $(this).css("background-position", "right");
            $(this).addClass("heartAnimation");
            likeAjax(window.location.pathname);
        }
    });

    $('.reply').click(function(){
        viewModel.reply(true);
        console.log($(this).html());
        var replyRef = $(this).closest('li').find('.name').text();
        console.log(replyRef);
        viewModel.replyRef(replyRef);
    });

    $('.cancel').click(function(){
        viewModel.reply(false);
    });

    $('#comment-btn').click(function () {
        var data = {
            nickname: $('#nickname').val(),
            email: $('#email').val(),
            content: $('#content').val(),
            url: window.location.pathname
        };

        if ($('#website').val() != '') {
            data['website'] = $('#website').val();
        }

        if (viewModel.reply() == true) {
            data['replyTo'] = viewModel.replyRef();
        }

        if (!data['nickname'].length) {
            layer.open({
                type: 0,
                content: '昵称不能为空'
            });
            return;
        }

        if (!data['email'].length) {
            layer.open({
                type: 0,
                content: '邮箱不能为空'
            });
            return;
        }

        if (!data['content'].length) {
            layer.open({
                type: 0,
                content: '评论内容不能为空'
            });
            return;
        }

        commentAjax(data);
    });
});

function ViewModel() {
    var self = this;

    self.reply = ko.observable(false);
    self.replyRef = ko.observable('');
}

function commentAjax(data) {
    $.ajax({
        url: "/article/comment",
        type: "post",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.status == 0) {
                layer.open({
                    type: 0,
                    content: '评论成功,待管理员审核后显示'
                });
                $('#content').val('');
                $('#email').val('');
                $('#nickname').val('');
                $('#website').val('');
            } else {
                if (data.data['email'] != undefined) {
                    layer.open({
                        type: 0,
                        content: data.data['email']
                    });
                } else if (data.data['nickname'] != undefined) {
                    layer.open({
                        type: 0,
                        content: data.data['nickname']
                    });
                } else if (data.data['website'] != undefined) {
                    layer.open({
                        type: 0,
                        content: data.data['website']
                    });
                } else if (data.data['content'] != undefined) {
                    layer.open({
                        type: 0,
                        content: data.data['content']
                    });
                } else if (data.data['replyTo'] != undefined) {
                    layer.open({
                        type: 0,
                        content: data.data['replyTo']
                    });
                }
            }
        },
        error: function (data) {
            layer.open({
                type: 0,
                content: '服务器错误'
            });
        }
    });
}

function likeAjax(url) {
    var data = {
        url: url
    };
    $.ajax({
        url: "/article/like",
        type: "post",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.status == 0) {
                $('#likes').text(data.data);
            } else {
                $(this).css("background-position", "left");
                $(this).removeClass("heartAnimation");
            }
        },
        error: function (data) {

        }
    });
}



