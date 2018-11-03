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

        if (queryDate != null && queryDate != '') {
            url = '/' + url + '?currentPage=1' + queryDate;
        } else {
            url = '/' + url + '?currentPage=1';
        }
        reqURL(url);
    });

});

//获取查询条件
function getQueryParams(selector) {
    if (selector == null || selector == '') {
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
    if (selector == null || selector == '') {
        selector = ".query-btn";
    }
    var url = $(selector).parent().attr("name");
    return url;
}

//跳转页面
function reqURL(url) {
    if (url) {
        //获取父窗口分iframe
        window.parent.$("#iframe").prop("src", url);
    }
}


/**
 * 显示添加窗口
 * @param selectURL 下拉框URL
 */
function showAddModal(selectURL) {
    var select = $("#select");
    $("input[name='id']").val(null);//清空id
    $("#addFrom").clearForm();//清空表单
    if (selectURL) {
        displaySelect(selectURL);
    }
    $("#input").modal("show");
}

/**
 * 显示编辑窗口
 * @param button 编辑按钮
 * @param url 需要请求编辑的URL
 * @param selectName 获取的json对象名称
 * @param selectURL 下拉框需要请求的URL
 */
function showUpdateModal(button, url, selectName, selectURL) {
    var select = $("#select");
    if (selectName && selectURL && select) {
        select.children().remove();//清除下拉框信息
    }
    if (!url || !button) {
        return;
    }
    var id = $(button).parent().parent().children(":first-child").val();
    $("#id").val(id);
    if (id) {
        $.ajax({
            url: url + "/" + id,
            type: 'get',
            success: function (data) {
                if (data.success) {
                    setInputVal(data.obj);
                    if (selectName && selectURL && select) {
                        var selectData = eval("data.obj." + selectName);
                        var ids = $.map(selectData, function (item) {
                            return item.id;
                        });
                        displaySelect(selectURL, ids);
                    }
                } else {
                    $.messager.popup(data.msg);
                }
            }
        });
        $("#input").modal("show");
    }
}


/**
 * 添加或更新
 * @param url 需要请求的url
 */
function addOrUpdate(url) {
    if (!url) {
        return;
    }
    var input = $("#addFrom input");//文本框
    var select = $("#select").children();//下拉框
    var id = input[0].value;//获取隐藏域id
    var params = "";
    var type = "";
    $.map(input, function (item) {
        if (item.value) {
            params += "&" + item.name + "=" + item.value;
        }
    });
    //如果有下拉框，获取被选中的下拉框
    if (select) {
        var ids = $.map(select, function (item) {
            var selected = $(item).prop("selected");
            if (selected) {
                return item.value;
            }
        });
        if (ids) {
            params += "&ids=" + ids;
        }
    }
    params = params.substring(1, params.length);
    url += "?" + params;
    //更新
    if (id) {
        type = "put";
        //添加
    } else {
        type = "post";
    }
    $.ajax({
        url: url,
        type: type,
        success: function (data) {
            if (data.success) {
                window.location.reload();//刷新页面
            } else {
                $.messager.popup(data.msg);
            }
        }
    });
}

/**
 * 删除
 * @param button
 * @param url
 */
function deleteOne(button, url) {
    if (!button) {
        return;
    }
    id = $(button).parent().parent().children(":first-child").val();
    if (!id || !url) {
        return;
    }
    bootbox.confirm("你确定要删除吗？", function (yes) {
        if (yes) {
            $.ajax({
                url: url + '/' + id,
                type: 'delete',
                success: function (data) {
                    if (data.success) {
                        window.location.reload();
                    } else {
                        $.messager.popup(data.msg);
                    }
                }
            });
        }
    });
}

/**
 * 将查询结果设置给表单input
 * @param obj
 */
function setInputVal(obj) {
    if (!obj) {
        return;
    }
    $.each(obj, function (i) {
        var input = $("#addFrom input[name='" + i + "']");
        if (input) {
            input.val(obj[i]);
        }
    })
}

/**
 * 将查询结果设置给表单select
 * @param ids 选项值
 */
function displaySelect(selectURL, ids) {
    if (!selectURL) {
        return;
    }
    var selected = "";
    $.ajax({
        url: selectURL,
        type: 'get',
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var html = '';
                for (var i = 0; i < data.objList.length; i++) {
                    //判断参数是否有 ：有就回显，没有则是添加按钮
                    if (ids) {
                        for (var j = 0; j < ids.length; j++) {
                            if (data.objList[i].id == ids[j]) {
                                selected = 'selected';
                                break;
                            }
                        }
                    }
                    html += '<option value="' + data.objList[i].id + '" ' + selected + '>' + data.objList[i].name + '</option>';
                    selected = "";
                }
                $("#select").append(html).selectpicker({
                    noneSelectedText: '请选择',
                    width: 300,
                    height: 100
                }).selectpicker("refresh").selectpicker("render");
                $("#input").modal("show");
            }
        }
    });
}