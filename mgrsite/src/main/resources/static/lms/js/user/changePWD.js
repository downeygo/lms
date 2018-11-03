$(function () {
    $(".change-btn").click(function () {
        var input = $("input");
        var msg = "";
        var oldPWD = "";
        var newPWD = "";
        var confirmPWD = "";

        $.map(input, function (item) {
            if (item.value == null || item.value == "") {
                if (item.name == "oldPWD") {
                    msg += "旧密码不能为空哦</br>"
                } else if (item.name == "newPWD") {
                    msg += "新密码不能为空哦</br>"
                } else if (item.name == "confirmPWD") {
                    msg += "确认密码不能为空哦</br>"
                }
            } else {
                if (item.name == "oldPWD") {
                    oldPWD = item.value;
                } else if (item.name == "newPWD") {
                    newPWD = item.value;
                } else if (item.name == "confirmPWD") {
                    confirmPWD = item.value;
                }
            }
        });

        if (msg) {
            $.messager.popup(msg);
            return;
        } else if (newPWD != confirmPWD) {
            $.messager.popup("两次输入的新密码不一致");
            return;
        }

        $.ajax({
            url: '/user/changePWD',
            type: 'put',
            data: {'oldPWD': oldPWD, 'newPWD': newPWD},
            success: function (data) {
                if (data.success) {
                    bootbox.confirm(data.msg+",请重新登录", function (yes) {
                        window.parent.location.href = '/logout';
                    })
                } else {
                    bootbox.alert(data.msg);
                }
            }
        });
    });
});
