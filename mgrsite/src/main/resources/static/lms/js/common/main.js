$(function () {

    //菜单点击事件
    $("a").click(function () {
        var url = $(this).data("url");
        if (url) {
            $("#iframe").prop("src", url);
        }
    });

    //高级查询
    $(".query-btn").click(function () {
        var queryDate = getQueryParams(".query-btn");
        var url = getURL(".query-btn");

        console.debug(queryDate)

        if (queryDate != null && queryDate != '') {
            url = '/' + url + '?currentPage=1' + queryDate;
        } else {
            url = '/' + url + '?currentPage=1';
        }

        reqURL(url);
    });
});

//获取查询条件
function getQueryParams(selector){
    if(selector == null || selector ==''){
        selector = ".query-btn";
    }
    var child = $(selector).parent().children("input");
    var queryDate = '';
    $.map(child, function (item) {
        if (item.value != null && item.value != '') {
            queryDate += '&' + item.name + '=' + item.value;
        }
    });
    return queryDate;
}

//获取url
function getURL(selector) {
    if(selector == null || selector ==''){
        selector = ".query-btn";
    }
    var url = $(selector).parent().attr("name");
    return url;
}

//跳转页面
function reqURL(url) {
    if(url){
        //获取父窗口分iframe
        window.parent.$("#iframe").prop("src", url);
    }
}



