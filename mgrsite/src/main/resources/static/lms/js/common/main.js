$(function () {
    //按钮变小
    $(".btn").addClass("btn-sm");

    //菜单点击时间
    $("a").click(function () {
        var url = $(this).data("url");
        if (url) {
            $("#iframe").prop("src", url);
        }
    });
});



