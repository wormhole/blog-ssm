var viewModel;
var loading;

$(function () {

    viewModel = new ViewModel();
    ko.applyBindings(viewModel);

    loadUserAjax();
});

function ViewModel() {
    var self = this;

    self.initData = {
        user: {
            headUrl: '',
            nickname: '',
            signature: ''
        }
    };

    self.user = ko.observable(self.initData.user);
}

function loadUserAjax() {
    $.ajax({
        url: "/api/user",
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