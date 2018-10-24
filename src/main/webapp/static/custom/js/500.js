var viewModel;

function ViewModel(){
    var self = this;
    self.initDate = {
        user : {
            headUrl:'',
            signature:'',
            nickname:''
        }
    };
    self.user = ko.observable(self.initDate.user);
}

$(function(){
    viewModel = new ViewModel();
    ko.applyBindings(viewModel);

    $.ajax({
        url: "/api/user/admin",
        type: 'get',
        dataType: "json",
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
                content: "服务器错误",
            });
        }
    });
});