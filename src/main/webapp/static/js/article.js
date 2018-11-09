var viewModel;

$(function () {
    editormd.markdownToHTML("editormd-view", {});
    viewModel = new ViewModel();
    ko.applyBindings(viewModel);

    $('.heart').click(function () {
        if ($(this).attr("rel") === "true") {
            return;
        } else {
            likeAjax(window.location.pathname);
        }
    });

    $('.reply').click(function () {
        viewModel.reply(true);
        var replyRef = $(this).closest('li').find('.name').text();
        viewModel.replyRef(replyRef);
    });

    $('.cancel').click(function () {
        viewModel.reply(false);
    });

    $('#comment-btn').click(function () {
        var data = {
            nickname: $('#nickname').val(),
            email: $('#email').val(),
            content: $('#content').val(),
            url: window.location.pathname
        };

        if ($('#website').val() !== '') {
            data['website'] = $('#website').val();
        }

        if (viewModel.reply() === true) {
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

        var param = {
            data: {
                comment: [data]
            }
        };

        commentAjax(param);
    });
});

function ViewModel() {
    var self = this;

    self.reply = ko.observable(false);
    self.replyRef = ko.observable('');
}

function commentAjax(param) {
    $.ajax({
        url: "/api/comment",
        type: "post",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.status === 0) {
                layer.open({
                    type: 0,
                    content: '评论成功,待管理员审核后显示'
                });
                $('#content').val('');
                $('#email').val('');
                $('#nickname').val('');
                $('#website').val('');
            } else {
                if (response.data !== undefined) {
                    if (response.data['email'] !== undefined) {
                        layer.open({
                            type: 0,
                            content: response.data['email']
                        });
                    } else if (response.data['nickname'] !== undefined) {
                        layer.open({
                            type: 0,
                            content: response.data['nickname']
                        });
                    } else if (response.data['website'] !== undefined) {
                        layer.open({
                            type: 0,
                            content: response.data['website']
                        });
                    } else if (response.data['content'] !== undefined) {
                        layer.open({
                            type: 0,
                            content: response.data['content']
                        });
                    } else if (response.data['replyTo'] !== undefined) {
                        layer.open({
                            type: 0,
                            content: response.data['replyTo']
                        });
                    }
                }
            }
        },
        error: function (response) {
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
    var param = {
        data: {
            article: [data]
        }
    };
    $.ajax({
        url: "/api/like",
        type: "post",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.status === 0) {
                $('#likes').text(response.data);
                $('.heart').css("background-position", "right");
                $('.heart').addClass("heartAnimation");
                $('.heart').attr('rel', 'true');
            }
        },
        error: function (response) {

        }
    });
}



