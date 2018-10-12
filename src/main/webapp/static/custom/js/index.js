$(function () {
    $.ajax({
        url: "/api/sideinfo",
        type: "get",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.status == 0) {
                var sideinfo = data.data;
                $('#head').attr('src', sideinfo.headUrl);
                $('#nickname').text(sideinfo.nickname);
                $('#signature').text(sideinfo.signature);
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
            } else {

            }
        },
        error: function (data) {

        }
    });
});

function getPagination(count, page) {
    var ulTemplate = '<ul class="pagination"></ul>';
    var liTemplate = '<li class="page-item"></li>';
    var aTemplate = '<a class="page-link" href=""></a>';

    var pageCount = Math.ceil(count / 5);

    var ul = $(ulTemplate);

    var start = page >= 3 ? page - 2 : page;


}