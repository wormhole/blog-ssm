var viewModel;
var loading;

$(function () {

    viewModel = new viewModel();
    ko.applyBindings(viewModel);

    loadSideInfo();

    $('#tree').treeview({
        data: getTree(),
        onNodeSelected: function (event, data) {
            console.log(event,data);
        },
        onNodeUnselected: function (event, data) {
            console.log(event,data);
        }
    });
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
}

function getTree() {
    //节点上的数据遵循如下的格式：
    var tree = [{
        text: "父节点1",
        color: "#000000",
        backColor: "#FFFFFF",
        selectable: true,
        state: {
            expanded: false,
            selected: false
        },
        nodes: [{
            text: "子节点1",
        }, {
            text: "子节点2"
        }]
    }, {
        text: "父节点2",
        color: "#000000",
        backColor: "#FFFFFF",
        selectable: true,
        state: {
            expanded: false,
            selected: false
        },
        nodes: [{
            text: "子节点1",
        }, {
            text: "子节点2"
        }]
    }];

    return tree;
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