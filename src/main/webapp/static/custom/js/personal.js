function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) {
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) {
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) {
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

$('#headImg').click(function () {
    $('input[type="file"]').click();
});

$('input[type="file"]').change(function () {
    var file = this.files[0];
    $('#headImg').attr('src', getObjectURL(file));
});