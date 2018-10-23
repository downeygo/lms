$(function () {
    //点击添加显示该窗口
    $(".add-btn").click(function () {
        //清空表单数据
        $("#username").prop("readonly", false);
        $("input[name='id']").val(null);
        $("#addFrom").clearForm();
        $("#select").children().remove();
        //加载下拉框框
        displaySelect();
        $("#input").modal("show");
    });

    //保存
    $(".save-btn").click(function () {
        var id = $("input[name='id']").val();
        var url;
        var type;

        //获得被选中的权限ID
        var child = $("#select").children();
        var ids = $.map(child, function (item) {
            var selected = $(item).prop("selected");
            if (selected) {
                return item.value;
            }
        });

        if (id) {
            url = '/user?' + $("#addFrom").serialize() + '&ids=' + ids;
            type = 'put';
        } else {
            url = '/user?' + $("#addFrom").serialize() + '&ids=' + ids;
            type = 'post';
        }
        $.ajax({
            url: url,
            type: type,
            success: function (data) {
                if (data.success) {
                    window.location.reload();
                } else {
                    $.messager.popup(data.msg);
                }
            },
        });
    });

    //编辑
    $(".edit-btn").click(function () {
        $("#select").children().remove();
        //清空表单数据
        $("#addFrom").clearForm();
        //获取要编辑的id
        var id = $(this).parent().parent().children(":first-child").val();
        //为隐藏域设置值
        $("input[name='id']").val(id);

        //查询出用户已有权限的ID
        $.ajax({
            url: '/user/' + id,
            type: 'get',
            async: 'false',
            success: function (data) {
                if (data.success) {
                    $("input[name='id']").val(data.obj.id);
                    $("#username").prop("readonly", true).val(data.obj.loginInfor.username);
                    var ids = $.map(data.obj.roles, function (item) {
                        return item.id;
                    });

                    //查询所有权限列表再回显数据
                    displaySelect(ids);
                }
            },
        });
        $("#input").modal("show");
    });

    //删除
    $(".delete-btn").click(function () {
        var id = $(this).parent().parent().children(":first-child").val();
        bootbox.confirm("你确定要删除吗？", function (yes) {
            if (yes) {
                $.ajax({
                    url: '/user/' + id,
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
    });

});

function displaySelect(ids) {
    var selected = "";
    $.ajax({
        url: '/user_getRole',
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