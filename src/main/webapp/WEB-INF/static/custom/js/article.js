var viewModel;
var loading;

$(function () {

    viewModel = new ViewModel();
    ko.applyBindings(viewModel);

    var url = window.location.pathname;

    loadUserAjax();
    loadArticleAjax(url);
});

function ViewModel() {
    var self = this;

    self.initData = {
        user: {
            headUrl: '',
            nickname: '',
            signature: ''
        },
        article: {
            title: '',
            dateString: '',
            nickname: '',
            categoryName: ''
        }
    };

    self.article = ko.observable(self.initData.article);
    self.user = ko.observable(self.initData.user);
}

function loadUserAjax() {
    $.ajax({
        url: "/api/user",
        type: "get",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.status == 0) {
                viewModel.user(data.data);
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
                content: "服务器错误"
            });
        }
    });
}

function loadArticleAjax(url) {
    $.ajax({
        url: "/api" + url,
        type: "get",
        dataType: "json",
        beforeSend: function () {
            loading = layer.open({
                type: 3
            });
        },
        complete: function () {
            layer.close(loading);
        },
        success: function (data) {
            if (data.status == 0) {
                viewModel.article(data.data);
                editormd.markdownToHTML("editormd-view", {
                    markdown: viewModel.article().articleMd,
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
                content: "服务器错误"
            })
        }
    });
}