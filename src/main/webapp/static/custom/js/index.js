var viewModel;

$(function () {

    viewModel = new viewModel();
    ko.applyBindings(viewModel);

    $.ajax({
        url: "/api/sideinfo",
        type: "get",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
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

    $.ajax({
        url: "/api/article?page=1",
        type: "get",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.status == 0) {
                var count = data.data.count;
                var page = data.data.page;
                var items = data.data.items;
                var ul = getPagination(count, page);
                $('.buttom').append(ul);
                viewModel.articleList(items);
            } else {

            }
        },
        error: function (data) {

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

    self.articleList = ko.observableArray([]);
    self.sideInfo = ko.observable(self.initData.sideInfo);
}

function getPagination(count, page) {
    var ulTemplate = '<ul class="pagination justify-content-center"></ul>';
    var liTemplate = '<li class="page-item"></li>';
    var aTemplate = '<a class="page-link" href=""></a >';

    var pageCount = Math.ceil(count / 5);
    var ul = $(ulTemplate);
    var start;
    if (page < 3) {
        start = 1;
    } else if (page <= (pageCount - 2)) {
        start = page - 2;
    } else if (page > (pageCount - 2)) {
        start = (pageCount - 4) > 0 ? pageCount - 4 : 1;
    }
    var currentIndex = start;

    for (var i = 0; i < 5; i++) {
        if (currentIndex > pageCount) {
            break;
        }
        var li = $(liTemplate);
        var a = $(aTemplate);
        a.text(currentIndex);
        a.attr('href', "/api/article?page=" + currentIndex);
        li.append(a);
        if (page == currentIndex) {
            li.addClass("active");
        }
        ul.append(li);
        currentIndex++;
    }
    if (1 < page) {
        var li = $(liTemplate);
        var a = $(aTemplate);
        a.text('上一页');
        a.attr('href', '/api/article?page=' + (page - 1));
        li.append(a);
        ul.prepend(li);
    }
    if (page < pageCount) {
        var li = $(liTemplate);
        var a = $(aTemplate);
        a.text('下一页');
        a.attr('href', '/api/article?page=' + (page + 1));
        li.append(a);
        ul.append(li);
    }
    return ul;
}