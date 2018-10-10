$(function () {
    $("#leftMenu li a").click(function () {
        var url = $(this).data("url");
        if (url) {
            $("#rightMain").prop("src", url);
        }
    });
});

/**
 * 清空表单
 */
function clearForm(form) {
    $("#"+form).clearForm();
}
