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
        deleteOne(this,"/permission")
    });
});
