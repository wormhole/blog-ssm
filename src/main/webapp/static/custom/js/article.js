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
});

function ViewModel(){
    var self = this;

    self.initDate = {

    };

    self.commentList = ko.observableArray([]);
    self.reply = ko.observable(false);
}

function likeAjax(url){
    var data = {
        url:url
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
            }else{
                $(this).css("background-position", "left");
                $(this).removeClass("heartAnimation");
            }
        },
        error: function (data) {

        }
    });
}

