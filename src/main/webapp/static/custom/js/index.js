$(function () {
    editormd.markdownToHTML("editormd-view", {});
    $('.heart').click(function () {
        $(this).css("background-position", "");
        $(this).addClass("heartAnimation");
    });
});