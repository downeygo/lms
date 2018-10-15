$(function () {
    //添加
    $(".add-btn").click(function () {

        $("input[name='id']").val(null);
        $("#addFrom").clearForm();
        displaySelect();

        /*$.ajax({
            url: '/role_getPermission',
            type: 'get',
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    var html = '';
                    for (var i = 0; i < data.objList.length; i++) {
                        html += '<option value="' + data.objList[i].id + '">' + data.objList[i].name + '</option>';
                    }
                    $("#select").append(html).selectpicker({
                        noneSelectedText: '请选择',
                        width: 300,
                        height: 100
                    }).selectpicker("refresh").selectpicker("render");
                    $("#input").modal("show");
                }
            }
        });*/
    });

    //删除
    $(".delete-btn").click(function () {
        var id = $(this).parent().parent().children(":first-child").val();
        bootbox.confirm("你确定要删除吗？", function (yes) {
            if (yes) {
                if (id) {
                    $.ajax({
                        url: '/role/' + id,
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
            }
        });
    });

    //保存
    $(".save-btn").click(function () {

        var id = $("input[name='id']").val();
        var name = $("#name").val();
        var code = $("#code").val();
        var url = "";
        var type = "";

        //获得被选中的权限ID
        var child = $("#select").children();
        var ids = $.map(child, function (item) {
            var selected = $(item).prop("selected");
            if (selected) {
                return item.value;
            }
        });

        //id存在则是编辑，不存在则是保存
        if (id) {
            url = '/role?ids=' + ids + '&name=' + name + '&code=' + code + '&id=' + id;
            type = "put";//更新
        } else {
            url = '/role?ids=' + ids + '&name=' + name + '&code=' + code;
            type = "post"//添加
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
            }
        });
    });

    //编辑
    $(".edit-btn").click(function () {
        //清空上次编辑的内容
        $("#select").children().remove();

        var id = $(this).parent().parent().children(":first-child").val();
        if (id) {

            //查询出用户已有权限的ID
            $.ajax({
                url: '/role/' + id,
                type: 'get',
                async: 'false',
                success: function (data) {
                    if (data.success) {
                        $("input[name='id']").val(data.obj.id);
                        $("#name").val(data.obj.name);
                        $("#code").val(data.obj.code);
                        var ids = $.map(data.obj.permissions, function (item) {
                            return item.id;
                        });

                        //查询所有权限列表再回显数据

                        displaySelect(ids);

                        /* var selected = "";
                         $.ajax({
                             url: '/role_getPermission',
                             type: 'get',
                             dataType: "json",
                             success: function (data) {
                                 if (data.success) {
                                     var html = '';
                                     for (var i = 0; i < data.objList.length; i++) {
                                         for (var j = 0; j < ids.length; j++) {
                                             if (data.objList[i].id == ids[j]) {
                                                 console.debug(data.objList[i].id + "--" + ids[j]);
                                                 selected = 'selected';
                                                 break;
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
                         });*/
                    }
                },
            });
        }
        ;
    });
});

function displaySelect(ids) {
    var selected = "";
    $.ajax({
        url: '/role_getPermission',
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