var viewModel;
var loading;

$(function () {

    viewModel = new viewModel();
    ko.applyBindings(viewModel);

    loadSideInfo();
    loadArticle(1);
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
    var aTemplate = '<a class="page-link" onclick=""></a >';

    var pageCount = Math.ceil(count / 5);

    if(pageCount == 1){
        return;
    }

    var ul = $(ulTemplate);

    var start = page - 2 < 1 ? 1 : page - 2;

    var end = start + 4 > pageCount ? pageCount : start + 4;

    if (end - start < 4) {
        start = end - 4 < 1 ? 1 : end - 4;
    }

    for (var i = start; i <= end; i++) {
        var li = $(liTemplate);
        var a = $(aTemplate);
        a.text(i);
        a.attr('onclick', "loadArticle(" + i + ")");
        li.append(a);
        if (page == i) {
            li.addClass("active");
        }
        ul.append(li);
    }
    if (1 < page) {
        var li = $(liTemplate);
        var a = $(aTemplate);
        var index = page - 1;
        a.text('上一页');
        a.attr('onclick', "loadArticle(" + index + ")");
        li.append(a);
        ul.prepend(li);
    }
    if (page < pageCount) {
        var li = $(liTemplate);
        var a = $(aTemplate);
        var index = page + 1;
        a.text('下一页');
        a.attr('onclick', "loadArticle(" + index + ")");
        li.append(a);
        ul.append(li);
    }
    return ul;
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

function loadArticle(page) {
    $.ajax({
        url: "/api/article?page=" + page,
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
                var count = data.data.count;
                var page = data.data.page;
                var items = data.data.items;
                var ul = getPagination(count, page);
                $('.buttom').html('');
                $('.buttom').append(ul);
                for (var i = 0; i < items.length; i++) {
                    items[i].articleHtml = $(items[i].articleHtml).text();
                }
                viewModel.articleList(items);
            } else {

            }
        },
        error: function (data) {

        }
    });
}