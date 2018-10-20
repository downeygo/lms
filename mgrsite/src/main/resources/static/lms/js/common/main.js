$(function () {

    //菜单点击事件
    $("a").click(function () {
        var url = $(this).data("url");
        if (url) {
            $("#iframe").prop("src", url);
        }
    });
});



