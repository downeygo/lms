$(function () {
    var selectValPre = $("select option:selected").val();
    var changeFlag = false;
    $("input").change(function () {
        changeFlag = true;
    });

    $(".save-btn").click(function () {
        var selectValNow = $("select option:selected").val();
        if (selectValPre == selectValNow && !changeFlag) {
            bootbox.alert("请修改后再保存");
            return;
        }
        var param = '';
        $.map($("input"), function (item) {
            if (item.value != null && item.value != '')
            param += '&' + item.name + '=' + item.value;
        });
        var selectName = $("select").prop("name");
        param = '?' + selectName + '=' + selectValNow + param;
        $.ajax({
            url:'/user/personal' + param,
            type:'put',
            success:function(data){
                if(data.success){
                    window.location.reload();
                }else{
                    bootbox.alert(data.msg);
                }
            }
        });
    });
});