$(function () {
    //加载权限
    $(".reload-btn").click(function () {
        $.ajax({
            url: '/reload',
            type: 'get',
            success: function (data) {
                if (data.success) {
                    window.location.reload();
                } else {
                    $.messager.popup(data.msg);
                }
            }
        });
    });

    //删除
    $(".delete-btn").click(function () {
        var id = $(this).parent().parent().children(":first-child").val();
        bootbox.confirm("你确定要删除吗？", function (yes) {
            if (yes) {
                if (id) {
                    $.ajax({
                        url: '/permission/' + id,
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
});
