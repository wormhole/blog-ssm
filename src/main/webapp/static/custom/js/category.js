var viewModel;
var loading;

$(function () {

    viewModel = new viewModel();
    ko.applyBindings(viewModel);

    loadSideInfo();
    loadCategory();
});

function viewModel() {
    var self = this;

    self.initData = {
        sideInfo: {
            headUrl: '',
            nickname: '',
            signature: ''
        }
    }

    self.sideInfo = ko.observable(self.initData.sideInfo);
    self.categoryList = ko.observableArray([]);
}

function loadSideInfo() {
    $.ajax({
        url: "/api/sideinfo",
        type: "get",
        dataType: "json",
        success: function (data) {
            if (data.status == 0) {
                var sideInfo = data.data;
                viewModel.sideInfo(sideInfo);
            } else {
            }
        },
        error: function (data) {
        }
    });
}

function loadCategory(){
    $.ajax({
        url: "/api/category",
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
                viewModel.categoryList(data.data);
            } else {
            }
        },
        error: function (data) {
        }
    });
}